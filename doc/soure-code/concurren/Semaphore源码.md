# Semaphore源码

## Semaphore

**构造函数和字段**

```java
public class Semaphore implements java.io.Serializable {
    //Semaphore也是AQS的共享同步器实现的
    private final Sync sync;
    // 默认为非公平锁，初始化信号量
    public Semaphore(int permits) {
        sync = new NonfairSync(permits);
    }
    //公平锁，初始化信号量
    public Semaphore(int permits, boolean fair) {
        sync = fair ? new FairSync(permits) : new NonfairSync(permits);
    }
}
```


```java
public class Semaphore implements java.io.Serializable {
    //获取锁
   public void acquire() throws InterruptedException {
       //调用的是AbstractQueuedSynchronizer的模板方法
        sync.acquireSharedInterruptibly(1);
    }
    //释放锁
     public void release() {
        sync.releaseShared(1);
    }

}
public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements java.io.Serializable {
    public final void acquireSharedInterruptibly(int arg)
            throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        //主要实现在子类中，返回剩余信号量(锁)，如果没有锁了就执行下面的挂起操作,非公平锁的实现最终是 nonfairTryAcquireShared方法
        //公平锁是只有当前节点是head的下一个节点才会获取锁
        if (tryAcquireShared(arg) < 0)
            //1. 把该节点添加到队列中，获取该队列的前一个节点，如果前一个节点为head节点则进行再次获取锁，如果获取成功把当前节点修改成head节点，unpark离head节点最近的有效后续的共享节点节点
            //2.0 如果当前节点的前一个节点不是head节点，则进行判断前面节点是否在等待，如果前面节点再等待则挂起当前节点线程
            //2.1 如果前一个节点是无效，则进行过滤无效节点，接着第一步1.进行重新判断
            //2.2 否则把前一个节点的状态改成等待状态
            doAcquireSharedInterruptibly(arg);
    }

    //释放锁
    public final boolean releaseShared(int arg) {
        //尝试释放锁，具体实现是
        if (tryReleaseShared(arg)) {
            //释放成功锁，要进行head后续节点unpark，让他们进行一个共享传播unpark
            doReleaseShared();
            return true;
        }
        return false;
    }
}

abstract static class Sync extends AbstractQueuedSynchronizer {
    //非公平锁获取锁的实现方式
   final int nonfairTryAcquireShared(int acquires) {
        for (;;) {
            //获取当前state
            int available = getState();
            //state -1 = remaining 剩余的锁
            int remaining = available - acquires;
            //返回剩余的锁
            if (remaining < 0 ||
                compareAndSetState(available, remaining))
                return remaining;
        }
    }
    //释放锁
    protected final boolean tryReleaseShared(int releases) {
        for (;;) {
            //锁恢复
            int current = getState();
            int next = current + releases;
            if (next < current) // overflow
                throw new Error("Maximum permit count exceeded");
            //进行一个设置state释放锁，成功则返回true，
            if (compareAndSetState(current, next))
                return true;
        }
    }
}

```

