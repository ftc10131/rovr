package rovr.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MineralDumper extends Mechanism {

    Servo dumper;
    public MineralDumper(String n,HardwareMap hardwareMap){
        super(n, hardwareMap);
        dumper = getHwServo(n);
    }

    public void init(){

    }

    public void start(){

    }

    public void stop(){

    }
}
