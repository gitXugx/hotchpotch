# CountDownLatch源码



## CountDownLatch

**构造函数和字段**

```java
public class CountDownLatch {
    //同步器
    private final Sync sync;
    //初始化共享变量state的值，同步器是以共享的模式
    public CountDownLatch(int count) {
        if (count < 0) throw new IllegalArgumentException("count < 0");
        this.sync = new Sync(count);
    }
}
```



**Sync 同步器的实现**
```java
private static final class Sync extends AbstractQueuedSynchronizer {
    private static final long serialVersionUID = 4982264981922014374L;

    Sync(int count) {
        setState(count);
    }

    int getCount() {
        return getState();
    }

    protected int tryAcquireShared(int acquires) {
        return (getState() == 0) ? 1 : -1;
    }

    protected boolean tryReleaseShared(int releases) {
        // 进行锁的释放，只有锁释放完才会返回true
        for (;;) {
            int c = getState();
            if (c == 0)
                return false;
            int nextc = c-1;
            if (compareAndSetState(c, nextc))
                return nextc == 0;
        }
    }
}
```


```java
public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements java.io.Serializable {
   public final boolean releaseShared(int arg) {
        //如果全部锁释放完了就为true
        if (tryReleaseShared(arg)) {
            //进行一个队列中线程unpark
            doReleaseShared();
            return true;
        }
        return false;
    }
     public void await() throws InterruptedException {
         //1.判断共享锁是不是释放完，如果队列中前节点是头节点，进行一个再次尝试获取，如果没有成功，则添加到等待队列中，前面的节点如果等于0 则设置前面节点为等待中，如果为无效则忽略前面无效节点，如果前面节点已为等待，就park该线程，因为前面的线程都在等待中。
        sync.acquireSharedInterruptibly(1);
    }

    private void doReleaseShared() {
        for (;;) {
            Node h = head;
            if (h != null && h != tail) {
                int ws = h.waitStatus;
                //如果头节点为等待状态，则重置状态
                if (ws == Node.SIGNAL) {
                    if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                        continue;            // loop to recheck cases
                    //然后unpark离头节点最近的有效节点，因为头节点要么是当前节点，要么是当时初始化的节点，如果是有效head节点，肯定是已经被unpark的
                    unparkSuccessor(h);
                }
                //否则把当前节点设置成PROPAGATE 具有传播性的节点
                else if (ws == 0 &&
                         !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                    continue;                // loop on failed CAS
            }
            if (h == head)                   // loop if head changed
                break;
        }
    }
}
```







