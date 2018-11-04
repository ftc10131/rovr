package rovr.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import rovr.util.Param;

public class Hanger extends Mechanism {

    DcMotor winch;
    Servo lock;

    public Hanger(String n, HardwareMap hardwareMap) {
        super(n, hardwareMap);
        winch = hardwareMap.get(DcMotor.class, "winch");
        lock = hardwareMap.get(Servo.class, "lock");
    }

    public void init() {
        hm.put(name + ",LockVal", new Param(0.3));
        hm.put(name + ",UnlockVal", new Param(0.7));
    }

    public void liftRobot() {
        winch.setPower(1);
    }

    public void dropRobot() {
        winch.setPower(-1);
    }

    public void stopWinch() {
        winch.setPower(0);
    }

    public void start() {

    }

    public void stop() {

    }

    public void lock() {
        lock.setPosition(hm.get(name + ",LockVal").getValue());
    }

    public void unlock() {
        lock.setPosition(hm.get(name + ",UnlockVal").getValue());
    }
}
