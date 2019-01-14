package rovr.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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

    public void init(){
        collect();
    }

    public void start(){

    }

    public void stop(){

    }

    public void dump(){
        dumper.setPosition(hmp.get(mName("Dump")).getValue());
    }
    double currentTime = 0;
    double startTime = 0;
    public void slowDump(OpMode om){
        currentTime = om.getRuntime();
        if(currentTime-startTime < 0.1){
            startTime = currentTime;
            dumper.setPosition(dumper.getPosition());
        }else if (currentTime-startTime > 0.05){
            dump();
        }
    }

    public void collect(){
        dumper.setPosition(hmp.get(mName("Collect")).getValue());
    }
}
