# ThreadLocal源码

## ThreadLocal

**get**

```java
public class ThreadLocal<T> {
    public T get() {
        //获取当前线程
        Thread t = Thread.currentThread();
        //需要先获取，看看是否为空，不然会报null指针
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            //获取当前线程的Entry
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                //如果当前value ！= null 则返回
                T result = (T)e.value;
                return result;
            }
        }
        //证明map 为 null 需要初始化
        return setInitialValue();
    }
    private T setInitialValue() {
        //所以先初始化value为null，以防强引用value 有内存泄露的情况
        T value = initialValue();
        Thread t = Thread.currentThread();
        //需要先获取，看看是否为空，不然会报null指针
        ThreadLocalMap map = getMap(t);
        if (map != null)
            //直接设置当前线程的value为null
            map.set(this, value);
        else
            //否则先创建，然后设置value为null
            createMap(t, value);
        return value;
    }
}
```

**set**

```java
public class ThreadLocal<T> {
    
    public void set(T value) {
        Thread t = Thread.currentThread();
        //需要先获取，看看是否为空，不然会报null指针
        ThreadLocalMap map = getMap(t);
        if (map != null)
            //设置value
            map.set(this, value);
        else
            //创建ThreadLocalMap设置value
            createMap(t, value);
    }
}
```

**remove**


```java
public class ThreadLocal<T> {
     public void remove() {
         //先获取，防止null指针
         ThreadLocalMap m = getMap(Thread.currentThread());
         if (m != null)
             m.remove(this);
     }
    private void remove(ThreadLocal<?> key) {
        Entry[] tab = table;
        int len = tab.length;
        //定位到对应的hash槽
        int i = key.threadLocalHashCode & (len-1);
        //使用开放地址法(线性探测法，来进行remove)当数据量小的时候还是比较合适的，数据量大的时候，会产生堆积
        for (Entry e = tab[i];
                e != null;
                e = tab[i = nextIndex(i, len)]) {
            if (e.get() == key) {
                e.clear();
                expungeStaleEntry(i);
                return;
            }
        }
    }
}
```




