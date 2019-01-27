package rovr.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
        hmp.put(mName("LockVal"), new Param(0.55));
        hmp.put(mName("UnlockVal"), new Param(0.2));
        hmp.put(mName("EncToLand"),new Param(4700));
        hmp.get(mName("LockVal")).setStandardServo();
        hmp.get(mName("UnlockVal")).setStandardServo();
        hmp.get(mName("EncToLand")).setStandardEnc();
    }
    //////////////////////////////////////////////////
    //Hanger difference between up and down is 4238 encoder values
    //Winch in is negative value
    //////////////////////////////////////////////////

    public void init() {

    }

    public void liftRobot() {
        winch.setPower(-1);
    }

    public void dropRobot() {
        winch.setPower(1);
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

    public void land(LinearOpMode om){
        liftRobot();
        om.sleep(400);
        unlock();
        om.sleep(333);
        dropRobot();
        int startPos = winch.getCurrentPosition();
        double startTime = om.getRuntime();
        while(winch.getCurrentPosition()-startPos < getPVal("EncToLand") && om.opModeIsActive() && om.getRuntime()-startTime < 5){
            om.telemetry.addData("Hanger: ", "landing");
            om.telemetry.update();
        }
        stopWinch();
        if(winch.getCurrentPosition()-startPos < getPVal("EncToLand")){
            om.stop();
        }
    }

    public void pullDownHalfway(LinearOpMode om){
        //liftRobot();
        //om.sleep(333);
        //unlock();
        //om.sleep(333);
        int startPos = winch.getCurrentPosition();
        liftRobot();
        double startTime = om.getRuntime();
        while(winch.getCurrentPosition()-startPos > -getPVal("EncToLand")/2 && om.opModeIsActive() && om.getRuntime()-startTime < 5){
            om.telemetry.addData("Hanger: ", "unlanding");
            om.telemetry.update();
        }
        stopWinch();
        if(winch.getCurrentPosition()-startPos > -getPVal("EncToLand")/2){
            om.stop();
        }
    }
}
