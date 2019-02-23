package design.mode.dm.behavior.ip;

/**
 * @author ：apple
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/31 下午10:01
 */
public class MyContainer implements MyList{

    private Object[] list;
    private int index;
    private int size;


    public MyContainer(int capacity) {
        index = 0;
        size = 0;
        this.list = new Object[capacity];
    }

    @Override
    public void add(Object o) {
        list[index++] = o;
        size++;
    }

    @Override
    public Object get(int index) {
        if(size >= index){
            return list[index];
        }
        throw new IndexOutOfBoundsException("index 超过了 list大小");
    }

    @Override
    public Iterator iterator() {
        return new ContainerIterator(this);
    }

    @Override
    public int size() {
        return size;
    }
}
