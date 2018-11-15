package rovr.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import rovr.util.Param;



public class Ploop extends Mechanism {
    DcMotor ploop;
    //boolean encMode = false;
    int upPosEnc;
    private ElapsedTime runtime = new ElapsedTime();
    boolean isPowered;
    int lastMotorPos;
    double lastTimeNotStalled;

    public Ploop(String n, HardwareMap hardwareMap) {
        super(n, hardwareMap);
        ploop = getHwMotor("ploop");
        ploop.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ploop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hmp.put(mName("Speed"), new Param(1));
        hmp.get(mName("Speed")).setStandardServo();
        hmp.put(mName("FullInVal"), new Param(0));
        hmp.put(mName("DumpVal"), new Param(550));
        hmp.put(mName("FullDownVal"), new Param(2250));
        isPowered = false;
    }

    public void init() {
        stop();
        startingUp();
        runtime.reset();
        lastMotorPos = ploop.getCurrentPosition();
        lastTimeNotStalled = runtime.seconds();
    }

    public void lower() {
        isPowered = true;
        ploop.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ploop.setPower(getPVal("Speed"));
        //encMode = false;
    }

    public void raise() {
        isPowered = true;
        ploop.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ploop.setPower(-getPVal("Speed"));
        //encMode = false;
    }

    public void stop() {
        //if(!encMode) {
        ploop.setPower(0);
        //}
        isPowered = false;
    }

    public void fullIn(){
        isPowered = true;
        ploop.setPower(getPVal("Speed"));
        ploop.setTargetPosition(upPosEnc);
        //encMode = true;
        ploop.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void goToDump(){
        isPowered = true;
        ploop.setPower(getPVal("Speed"));
        ploop.setTargetPosition(upPosEnc + (int)(getPVal("DumpVal")));
        //encMode = true;
        ploop.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void fullDown(){
        isPowered = true;
        ploop.setPower(getPVal("Speed"));
        ploop.setTargetPosition(upPosEnc + (int)(getPVal("FullDownVal")));
        //encMode = true;
        ploop.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void startingUp(){
        upPosEnc = ploop.getCurrentPosition();
    }

    public void startingDown(){
        upPosEnc = ploop.getCurrentPosition()- (int)getPVal("FullDownVal");
    }

    public void stopIfStalled(){
        if(!isStalledNow())
            lastTimeNotStalled = runtime.seconds();
        if(isStalledNow()&&runtime.seconds()-lastTimeNotStalled > 0.3){
            stop();
        }
    }

    public boolean isStalledNow(){
        if(!isPowered) {
            lastMotorPos = ploop.getCurrentPosition();
            return false;
        }
        if(Math.abs(lastMotorPos - ploop.getCurrentPosition())<5 ){
            lastMotorPos = ploop.getCurrentPosition();
            return true;
        }
        lastMotorPos = ploop.getCurrentPosition();
        return false;
    }

    public void stopAndReset(){
        ploop.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
