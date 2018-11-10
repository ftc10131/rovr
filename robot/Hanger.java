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
        lock = getHwServo("lock");
        winch = getHwMotor("winch");
    }
    //////////////////////////////////////////////////
    //Hanger difference between up and down is 4238 encoder values
    //Winch in is negative value
    //////////////////////////////////////////////////

    public void init() {
        hmp.put(mName("LockVal"), new Param(0.5));
        hmp.put(mName("UnlockVal"), new Param(0.2));
        hmp.get(mName("LockVal")).setStandardServo();
        hmp.get(mName("UnlockVal")).setStandardServo();
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
        lock.setPosition(hmp.get(mName("LockVal")).getValue());
    }

    public void unlock() {
        lock.setPosition(hmp.get(mName("UnlockVal")).getValue());
    }

    public void hangerUp(){

    }

    public void hangerDown(){

    }
}
