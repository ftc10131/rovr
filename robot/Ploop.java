package rovr.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import rovr.util.Param;

public class Ploop extends Mechanism {
    DcMotor ploop;

    public Ploop(String n, HardwareMap hardwareMap) {
        super(n, hardwareMap);
        ploop = hardwareMap.get(DcMotor.class, "ploop");
    }

    public void init() {

    }

    public void lower() {
        ploop.setPower(1);
    }

    public void raise() {
        ploop.setPower(-1);
    }

    public void stop() {
        ploop.setPower(0);
    }
}
