package rovr.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import rovr.util.Param;

public class MineralDumper extends Mechanism {

    Servo dumper;
    boolean isBall;
    public MineralDumper(String n,HardwareMap hardwareMap, boolean isThisBall){
        super(n, hardwareMap);
        dumper = getHwServo(n);
        isBall = isThisBall;
    }

    public void init(){
        if(isBall)
            hmp.put(mName("Dump"), new Param(0.8));
        else
            hmp.put(mName("Dump"), new Param(0.05));//0.8 for ball, 0.05 for block
        if(isBall)
            hmp.put(mName("Collect"), new Param(0.05)); //0.05 for ball, 0.825 for block
        else
            hmp.put(mName("Collect"), new Param(0.825));
        hmp.get(mName("Dump")).setStandardServo();
        hmp.get(mName("Collect")).setStandardServo();

    }

    public void start(){

    }

    public void stop(){

    }

    public void dump(){
        dumper.setPosition(hmp.get(mName("Dump")).getValue());
    }

    public void collect(){
        dumper.setPosition(hmp.get(mName("Collect")).getValue());
    }
}
