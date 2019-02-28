# BlockingQueue源码
## BlockingQueue

```java
public interface BlockingQueue<E> extends Queue<E> {
    //添加数据不赛
    boolean add(E e);

    boolean offer(E e);
    //添加数据，满的时候塞
    void put(E e) throws InterruptedException;

    boolean offer(E e, long timeout, TimeUnit unit)
        throws InterruptedException;
    //取数据，没有数据的时候塞
    E take() throws InterruptedException;
    //取数据，不塞
    E poll(long timeout, TimeUnit unit)
        throws InterruptedException;

    int remainingCapacity();

    boolean remove(Object o);

    public boolean contains(Object o);

    int drainTo(Collection<? super E> c);

    int drainTo(Collection<? super E> c, int maxElements);
}
```

BlockingQueue 接口定义了阻塞队列的基本方法具体的实现 `ArrayBlockingQueue` 、`LinkedBlockingQueue` 、`PriorityBlockingQueue` 这3中常规的实现。


## ArrayBlockingQueue

**构造方法和字段**
```java
public class ArrayBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, java.io.Serializable {
    //底层的数据结构是用数组实现的
    final Object[] items;

    /** items index for next take, poll, peek or remove */
    int takeIndex;

    /** items index for next put, offer, or add */
    int putIndex;
    //队列中有多少元素
    int count;
    //用来同步的锁
    final ReentrantLock lock;
    //取线程的等待队列
    /** Condition for waiting takes */
    private final Condition notEmpty;
    //写线程的等待队列
    /** Condition for waiting puts */
    private final Condition notFull;

    transient Itrs itrs = null;
    //初始化阻塞队列的容量
    public ArrayBlockingQueue(int capacity) {
        this(capacity, false);
    }
    //初始化阻塞队列的容量和锁的类型，默认为非公平锁
    public ArrayBlockingQueue(int capacity, boolean fair) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        this.items = new Object[capacity];
        lock = new ReentrantLock(fair);
        notEmpty = lock.newCondition();
        notFull =  lock.newCondition();
    }
}
```

初始化的时候创建 `ReentrantLock` 对象和两个 `Condition`等待队列。

**add**
```java
public class ArrayBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, java.io.Serializable {

    //实际上调用的是offer方法
    public boolean add(E e) {
        return super.add(e);
    }

    public boolean offer(E e) {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        //添加是枷锁
        lock.lock();
        try {
            //如果当前数组已满，直接返回false
            if (count == items.length)
                return false;
            else {
                //添加到数组中，然后通知poll消费任务
                enqueue(e);
                return true;
            }
        } finally {
            //解锁
            lock.unlock();
        }
    }
    //把任务添加到队列中
    private void enqueue(E x) {
        // assert lock.getHoldCount() == 1;
        // assert items[putIndex] == null;
        final Object[] items = this.items;
        items[putIndex] = x;
        if (++putIndex == items.length)
            putIndex = 0;
        count++;
        notEmpty.signal();
    }
}
```
可以看出该方法不是阻塞方法，只是枷锁，但是后面还是调用了 `notEmpty.signal()` 是为了通知阻塞的方法进行消费数据


**poll**


```java
public class ArrayBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, java.io.Serializable {
    public E poll() {
        final ReentrantLock lock = this.lock;
        //枷锁消费的时候让其添加阻塞
        lock.lock();
        try {
            //如果数组size为0 则返回null，这也是为什么，不让添加null元素的原因，是为了让poll的时候能区分出来是空数组
            return (count == 0) ? null : dequeue();
        } finally {
            lock.unlock();
        }
    }

    private E dequeue() {
        // assert lock.getHoldCount() == 1;
        // assert items[takeIndex] != null;
        final Object[] items = this.items;
        @SuppressWarnings("unchecked")
        E x = (E) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length)
            takeIndex = 0;
        count--;
        if (itrs != null)
            itrs.elementDequeued();
        notFull.signal();
        return x;
    }
}
```
这边同样和 `add` 方法一样是一个非阻塞方法，他是线程安全的方法，但是在随后进行一个 `notFull.signal()` 是为了消费完了通知阻塞的生产队列进行一个生产任务

**put**

```java
public class ArrayBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, java.io.Serializable {
    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        //尝试获取锁
        lock.lockInterruptibly();
        try {
            //如果队列满了，则把当前线程添加到notFull等待队列
            while (count == items.length)
                notFull.await();
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }
}
```
`put` 方法和 `add` 方法的区别是当该阻塞队列满的时候进行一个等待，如果消费端那边消费了的话，就可以进行通知到notFull队列把notFull头部节点添加到lock锁队列中，让其从新竞争锁

```java
public class ArrayBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, java.io.Serializable {
    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0)
                //如果队列中没有数据，则把当前取的线程添加到notEmpty等待队列中。
                notEmpty.await();
            return dequeue();
        } finally {
            lock.unlock();
        }
    }
}
```

`poll` 方法和 `take` 方法的区别是当该阻塞队列没有数据的时候进行一个等待，如果生产者那边生产了的话，就可以进行通知到notEmpty队列把notEmpty头部节点添加到lock锁队列中，让其从新竞争锁





