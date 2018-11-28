package rovr.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import rovr.util.Param;

public class Sorter extends Mechanism{

    Servo sorter;
    public Sorter(String n,HardwareMap hardwareMap){
        super(n, hardwareMap);
        sorter = getHwServo("sorter");
        hmp.put(mName("DumpBall"), new Param(0.9));
        hmp.put(mName("DumpBlock"), new Param(0.0));
        hmp.put(mName("DumpCenter"), new Param(0.48));
        hmp.get(mName("DumpBall")).setStandardServo();
        hmp.get(mName("DumpBlock")).setStandardServo();
        hmp.get(mName("DumpCenter")).setStandardServo();
    }

    public void init(){
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
