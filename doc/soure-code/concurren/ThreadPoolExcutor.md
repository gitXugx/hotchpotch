

```java

    public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
        //获取控制器
        int c = ctl.get();
        //获取线程中的工作线程和核心线程比较
        if (workerCountOf(c) < corePoolSize) {
            //如果工作线程小于核心线程，则创建新的工作线程，进行处理任务
            if (addWorker(command, true))
                return;
            c = ctl.get();
        }
        //当前线程池corepoolsize满了，或者创建线程任务失败时执行下面
        //当前线程池运行中，把该任务添加到队列尾部
        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            //如果是在运行中状态，则移除该任务
            if (! isRunning(recheck) && remove(command))
                //执行拒绝逻辑
                reject(command);
            //工作线程为空的情况下，重新创建工作线程    
            else if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        }
        //如果添加队列失败，则再次尝试直接执行，如果失败则执行拒绝逻辑
        else if (!addWorker(command, false))
            reject(command);
    }

```


```java
    //添加工作线程
    private boolean addWorker(Runnable firstTask, boolean core) {
        //判断当前线程池是否可以创建任务线程。
        retry:
        for (;;) {
            int c = ctl.get();
            int rs = runStateOf(c);

            // check当前还需不需要创建工作线程，当当前任务为空，而且队列也为空的时候就不需要创建工作线程
            if (rs >= SHUTDOWN &&
                ! (rs == SHUTDOWN &&
                   firstTask == null &&
                   ! workQueue.isEmpty()))
                return false;

            for (;;) {
                int wc = workerCountOf(c);
                //如果工作线程> 队列容量，或者大于最大值就直接返回false执行失败
                if (wc >= CAPACITY ||
                    wc >= (core ? corePoolSize : maximumPoolSize))
                    return false;
                //如果还能添加工作线程就进行下面的添加工作线程    
                if (compareAndIncrementWorkerCount(c))
                    break retry;
                c = ctl.get();  // Re-read ctl
                if (runStateOf(c) != rs)
                    continue retry;
                // else CAS failed due to workerCount change; retry inner loop
            }
        }

        boolean workerStarted = false;
        boolean workerAdded = false;
        Worker w = null;
        try {
            //创建一个工作线程
            w = new Worker(firstTask);
            final Thread t = w.thread;
            if (t != null) {
                final ReentrantLock mainLock = this.mainLock;
                //该锁主要在多线程下对阻塞队列的安全添加
                mainLock.lock();
                try {
                    // Recheck while holding lock.
                    // Back out on ThreadFactory failure or if
                    // shut down before lock acquired.
                    int rs = runStateOf(ctl.get());
                    //当前线程是运行的，或 SHUTDOWN 是支持创建线程但是不能接收任务
                    if (rs < SHUTDOWN ||
                        (rs == SHUTDOWN && firstTask == null)) {
                        if (t.isAlive()) // precheck that t is startable
                            throw new IllegalThreadStateException();
                        workers.add(w);
                        int s = workers.size();
                        if (s > largestPoolSize)
                            largestPoolSize = s;
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                //如果该线程添加成功就启动线程，执行任务
                if (workerAdded) {
                     //启动成功
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            if (! workerStarted)
                //移除当前工作线程
                addWorkerFailed(w);
        }
        return workerStarted;
    }


```


```java
    //实际上工作线程run方法执行的 任务的run方法，当执行完的时候根据心跳去阻塞时常去队列中取任务，当长时间获取不到任务，则进行销毁线程，移除工作队列中的线程
    final void runWorker(Worker w) {
        Thread wt = Thread.currentThread();
        Runnable task = w.firstTask;
        w.firstTask = null;
        w.unlock(); // allow interrupts
        boolean completedAbruptly = true;
        try {
            //执行任务，从队列中拿，或者当前工作线程的任务不为null
            while (task != null || (task = getTask()) != null) {
                w.lock();
                // If pool is stopping, ensure thread is interrupted;
                // if not, ensure thread is not interrupted.  This
                // requires a recheck in second case to deal with
                // shutdownNow race while clearing interrupt
                if ((runStateAtLeast(ctl.get(), STOP) ||
                     (Thread.interrupted() &&
                      runStateAtLeast(ctl.get(), STOP))) &&
                    !wt.isInterrupted())
                    wt.interrupt();
                try {
                    //放入的钩子，类似spring里面的一些实现
                    beforeExecute(wt, task);
                    Throwable thrown = null;
                    try {
                        //直接运行任务run方法
                        task.run();
                    } catch (RuntimeException x) {
                        thrown = x; throw x;
                    } catch (Error x) {
                        thrown = x; throw x;
                    } catch (Throwable x) {
                        thrown = x; throw new Error(x);
                    } finally {
                        //放入的钩子
                        afterExecute(task, thrown);
                    }
                } finally {
                    task = null;
                    w.completedTasks++;
                    w.unlock();
                }
            }
            completedAbruptly = false;
        } finally {
            //移除工作线程
            processWorkerExit(w, completedAbruptly);
        }
    }

```


```java

   private Runnable getTask() {
        boolean timedOut = false; // Did the last poll() time out?
        //循环获取队列中的任务
        for (;;) {
            int c = ctl.get();
            int rs = runStateOf(c);

            // Check if queue empty only if necessary.
            //如果队列为空，或者为停止或则清空的状态则进行释放该工作线程
            if (rs >= SHUTDOWN && (rs >= STOP || workQueue.isEmpty())) {
                decrementWorkerCount();
                return null;
            }

            int wc = workerCountOf(c);
            boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;
            if ((wc > maximumPoolSize || (timed && timedOut))
                && (wc > 1 || workQueue.isEmpty())) {
                if (compareAndDecrementWorkerCount(c))
                    return null;
                continue;
            }

            try {
                //当前重阻塞队列中获取数据
                Runnable r = timed ?
                    workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
                    workQueue.take();
                if (r != null)
                    return r;
                timedOut = true;
            } catch (InterruptedException retry) {
                timedOut = false;
            }
        }
    }

```
