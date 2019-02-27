# ConcurrentLinkedQueue

## ConcurrentLinkedQueue

**字段以及声明**

```java
public class ConcurrentLinkedQueue<E> extends AbstractQueue<E> implements Queue<E>, java.io.Serializable {
    //队列的头节点
    private transient volatile Node<E> head;
    //队列的尾节点，tail.next可能为空，也可能不为空
    private transient volatile Node<E> tail;
    public ConcurrentLinkedQueue() {
        head = tail = new Node<E>(null);
    }
}    
``
head队列的头，tail队列的尾部，但是他们都有写特殊，因为为了性能不去平凡的做cas写操作，采用延迟更新tail和head的方式来进行性能最佳。在初始化的时候一般会创建一个 `item = null` 节点，head和tail同时指向这个节点。

**add 方法**

`add` 方法最终调用的是 `offer` 

```java
public class ConcurrentLinkedQueue<E> extends AbstractQueue<E> implements Queue<E>, java.io.Serializable {
 public boolean offer(E e) {
        checkNotNull(e);//插入的元素不能为空
        //创建待添加元素
        final Node<E> newNode = new Node<E>(e);
        //进行cas+死循环进行添加元素
        for (Node<E> t = tail, p = t;;) {
            //获取到tail节点的下一个节点，来判断当前tail节点是不是真的tail节点
            Node<E> q = p.next;
            //如果tail节点的下一个节点为null，证明当前节点是tail节点
            if (q == null) {
                // 进行一个添加到tail节点的next
                if (p.casNext(null, newNode)) {
                    //只有能当前tail节点，不是真实的tail节点的时候才进行更新tail
                    if (p != t) // hop two nodes at a time
                        //更新成功失败都没事，因为后续的添加也会进行一个更新tail节点
                        casTail(t, newNode);  // Failure is OK.
                    return true;
                }
                // Lost CAS race to another thread; re-read next
            }
            //因为有个null节点的情况，所以tail和head可能都为null，这个时候出现上面cas失败的时候，再次循环p.next 是null tail也是null
            else if (p == q)
                //则从头开始遍历来进行添加
                p = (t != (t = tail)) ? t : head;
            else
                // 遍历下面P的下一个节点，直到添加成功为止
                p = (p != t && t != (t = tail)) ? t : q;
        }
    }

    public E poll() {
        restartFromHead:
        for (;;) {
            for (Node<E> h = head, p = h, q;;) {
                E item = p.item;
                //头节点不是null尝试修改null
                if (item != null && p.casItem(item, null)) {
                    //如果p！=h 则进行一个修改head指向下一个
                    if (p != h) // hop two nodes at a time
                        //如果下个节点为null 证明移除该节点后，队列中没有节点了，所以head设置为当前移除的节点，如果下一个节点不为null，则设置head为下一个节点
                        updateHead(h, ((q = p.next) != null) ? q : p);
                    return item;
                }
                //设置当前为新的head
                else if ((q = p.next) == null) {
                    updateHead(h, p);
                    return null;
                }
                //当前节点为null，而且没有后续节点
                else if (p == q)
                    continue restartFromHead;
                //接着循环下一个节点    
                else
                    p = q;
            }
        }
    }
    //该方法时间复制度是O(n),性能是比较低的，所以在使用的时候要注意
    public int size() {
        int count = 0;
        for (Node<E> p = first(); p != null; p = succ(p))
            if (p.item != null)
                // Collection.size() spec says to max out
                if (++count == Integer.MAX_VALUE)
                    break;
        return count;
    }
}
```



