package design.mode.dm.structural.flyweightp;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/28 17:52
 */
public class AppleX extends Apple {

    private Apple phone;

    public AppleX(Apple phone) {
        this.phone = phone;
    }

    private String cpu;
    private String color;
    private String resolutionRatio;


    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setResolutionRatio(String resolutionRatio) {
        this.resolutionRatio = resolutionRatio;
    }

    @Override
    public String toString() {
        return "ApplePhone{" +
                "phone=" + phone +
                ", cpu='" + cpu + '\'' +
                ", color='" + color + '\'' +
                ", resolutionRatio='" + resolutionRatio + '\'' +
                '}';
    }
}
