package rovr.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import rovr.util.Param;

public class Sorter extends Mechanism{

    Servo sorter;
    public Sorter(String n,HardwareMap hardwareMap){
        super(n, hardwareMap);
        sorter = getHwServo("sorter");
    }

    public void init(){
        hmp.put(mName("DumpBall"), new Param(0.4));
        hmp.put(mName("DumpBlock"), new Param(0.6));
        hmp.put(mName("DumpCenter"), new Param(0.5));
        center();
    }

    public void start(){

    }

    public void stop(){

    }

    public void center(){
        sorter.setPosition(hmp.get(mName("DumpCenter")).getValue());
    }
    public void dumpBlock(){
        sorter.setPosition(hmp.get(mName("DumpBlock")).getValue());
    }
    public void dumpBall(){
        sorter.setPosition(hmp.get(mName("DumpBall")).getValue());
    }

}
