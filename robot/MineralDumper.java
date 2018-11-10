package rovr.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import rovr.util.Param;

public class MineralDumper extends Mechanism {

    Servo dumper;
    public MineralDumper(String n,HardwareMap hardwareMap){
        super(n, hardwareMap);
        dumper = getHwServo(n);
    }

    public void init(){
        hmp.put(mName("Dump"), new Param(0.05)); //0.8 for ball, 0.05 for block
        hmp.put(mName("Collect"), new Param(0.825)); //0.05 for ball, 0.825 for block
        hmp.get(mName("Dump")).setStandardServo();
        hmp.get(mName("Collect")).setStandardServo();

    }

    public void start(){

    }

    public void stop(){

    }
}
