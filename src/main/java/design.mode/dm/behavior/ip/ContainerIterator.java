package design.mode.dm.behavior.ip;

/**
 * @author ：apple
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/31 下午10:10
 */
public class ContainerIterator implements Iterator{

    private MyList list;
    private int index;


    public ContainerIterator(MyList list) {
        index = 0;
        this.list = list;
    }

    @Override
    public Object next() {
        if(list.size() >= index){
            Object o = list.get(index);
            index++;
            return o;
        }
        return null;
    }

    @Override
    public boolean hashNext() {
        if(list.size() <= index){
            return false;
        }else {
            return true;
        }
    }
}
