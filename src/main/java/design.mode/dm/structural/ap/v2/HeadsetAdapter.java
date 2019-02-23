package design.mode.dm.structural.ap.v2;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/27 16:01
 */
public class HeadsetAdapter implements ICircular , ITypec{
    private ICircular iCircular;
    private ITypec iTypec;

    public HeadsetAdapter(ICircular iCircular, ITypec iTypec) {
        this.iCircular = iCircular;
        this.iTypec = iTypec;
    }

    @Override
    public void circularPlugInEarphones() {
        iTypec.typecPlugInEarphones();
    }

    @Override
    public void typecPlugInEarphones() {
        iCircular.circularPlugInEarphones();
    }
}
