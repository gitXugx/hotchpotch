# Condition源码


## Condition

**Condition 接口**
```java

public interface Condition {
   //把该线程添加到等待队列中并释放锁，park起来
    void await() throws InterruptedException;

    void awaitUninterruptibly();

    long awaitNanos(long nanosTimeout) throws InterruptedException;
 
    boolean await(long time, TimeUnit unit) throws InterruptedException;

    boolean awaitUntil(Date deadline) throws InterruptedException;
    //把该队列有效的头节点添加到lock队列中，并unpark其他线程节点
    void signal();
    //把该队列所有等待的节点添加到lock队列中，并unpark其他线程节点
    void signalAll();
}

```

## ConditionObject

**构造方法和字段**
```java
public class ConditionObject implements Condition, java.io.Serializable {
    private static final long serialVersionUID = 1173984872572414699L;
    //队列头节点
    private transient Node firstWaiter;
    //队列尾节点
    private transient Node lastWaiter;
    //默认构造方法
    public ConditionObject() { }
}
```



**await**

```java
public class ConditionObject implements Condition, java.io.Serializable {

    public final void await() throws InterruptedException {
        //证明该等待是不支持中断的
        if (Thread.interrupted())
            throw new InterruptedException();
        //添加该节点到队列中
        Node node = addConditionWaiter();
        //释放锁成功，并unpark头节点，如果释放失败，把该节点设置为无效节点
        int savedState = fullyRelease(node);
        int interruptMode = 0;
        //看node节点是否在lock队列中，如果在返回true
        while (!isOnSyncQueue(node)) {
            //不在syn挂起当前线程
            LockSupport.park(this);
            //是被unpark后的操作
            //看否中断，如果未中断，把当前线程添加到lock中，否则返回 0
            if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                break;
        }
        //尝试获取锁如果获取成功，等待结束时重新中断
        if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
            interruptMode = REINTERRUPT;
        //clean up if cancelled
        if (node.nextWaiter != null) 
            unlinkCancelledWaiters();
        //重新中断，或者抛出异常    
        if (interruptMode != 0)
            reportInterruptAfterWait(interruptMode);
    }
}
```

**addConditionWaiter**

```java
public class ConditionObject implements Condition, java.io.Serializable {
    private Node addConditionWaiter() {
        Node t = lastWaiter;
        // If lastWaiter is cancelled, clean out.
        if (t != null && t.waitStatus != Node.CONDITION) {
            //从头开始找，找到有效的然后赋值给lastWaiter
            unlinkCancelledWaiters();
            t = lastWaiter;
        }
        //设置当前节点，状态尾CONDITION
        Node node = new Node(Thread.currentThread(), Node.CONDITION);
        //把当前你节点直接设置成firstWaiter
        if (t == null)
            firstWaiter = node;
        else
            //否则证明该队列中有数据，设置成当前尾节点的下一个节点
            t.nextWaiter = node;
        //把当前节点设置成为最后一个等待节点    
        lastWaiter = node;
        return node;
    }
}
```

```java
public class ConditionObject implements Condition, java.io.Serializable {

   public final void signal() {
            if (!isHeldExclusively())
                throw new IllegalMonitorStateException();
            Node first = firstWaiter;
            if (first != null)
                doSignal(first);
        }
    private void doSignal(Node first) {
        do {
            if ( (firstWaiter = first.nextWaiter) == null)
                lastWaiter = null;
            first.nextWaiter = null;
        //把当前等待队列中的第一个节点，添加到syn队列的尾部，并unpark线程
        } while (!transferForSignal(first) &&
                    (first = firstWaiter) != null);
    }        
}
```