# FutureTask源码


## FutureTask


```java
//该类实现了runable和Future
public class FutureTask<V> implements RunnableFuture<V> {
    //传入一个Callable类型的参数，主要在FutureTask run方法中执行call方法，成功后设置到成员变量outcome里面
    public FutureTask(Callable<V> callable) {
        if (callable == null)
            throw new NullPointerException();
        this.callable = callable;
        this.state = NEW;       // ensure visibility of callable
    }
    //执行线程，当执行线程对result的改变，然后返回改变的对象。
    public FutureTask(Runnable runnable, V result) {
        this.callable = Executors.callable(runnable, result);
        this.state = NEW;       // ensure visibility of callable
    }
}
```
```java
@FunctionalInterface
public interface Callable<V> {
    //用来返回结果的调用。
    V call() throws Exception;
}
```



```java
    //FutureTask的线程主要调用的是call方法，然后把返回值设置到outcome
    public void run() {
        //证明当前有人在运行，则直接返回
        if (state != NEW ||
            !UNSAFE.compareAndSwapObject(this, runnerOffset,
                                         null, Thread.currentThread()))
            return;
        try {
            Callable<V> c = callable;
            //执行的call不为空，state == NEW
            if (c != null && state == NEW) {
                V result;
                boolean ran;
                try {
                    //运行call方法，返回result
                    result = c.call();
                    ran = true;
                } catch (Throwable ex) {
                    //执行发生异常，返回null
                    result = null;
                    ran = false;
                    setException(ex);
                }
                if (ran)
                    //设置outcome = result ，并unpark调用get()阻塞的线程
                    set(result);
            }
        } finally {
            runner = null;
            int s = state;
            if (s >= INTERRUPTING)
                handlePossibleCancellationInterrupt(s);
        }
    }

```



```java
    public V get() throws InterruptedException, ExecutionException {
        int s = state;
        if (s <= COMPLETING)
            //如果结果还未准备好则添加到等待节点，并park该线程
            s = awaitDone(false, 0L);
        //拿到返回的结果，如果状态是CANCELLED 则返回异常
        return report(s);
    }
```