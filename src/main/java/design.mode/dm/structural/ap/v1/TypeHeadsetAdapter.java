package design.mode.dm.structural.ap.v1;

/**
 * @author ：ex-xugaoxiang001
 * @description ：把typec适配成圆孔
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 15:50
 */
public class TypeHeadsetAdapter extends TypecHeadset implements IHeadsetPlug{

    @Override
    public void circularPlugInEarphones() {
        super.plugInEarphones();
    }

}
