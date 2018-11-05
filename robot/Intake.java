package rovr.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake extends Mechanism {


    DcMotor intake;

    public Intake(String n,HardwareMap hardwareMap){
        super(n, hardwareMap);
        intake = getHwMotor("intake");
    }


    public void init(){

    }

    public void start(){

    }

    public void stop(){

    }
}
