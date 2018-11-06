package rovr.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import rovr.util.Param;



public class Ploop extends Mechanism {
    DcMotor ploop;
    boolean encMode = false;

    public Ploop(String n, HardwareMap hardwareMap) {
        super(n, hardwareMap);
        ploop = getHwMotor("ploop");
    }

    public void init() {
        ploop.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hmp.put(mName("FullInVal"), new Param(0));
        hmp.put(mName("DumpVal"), new Param(550));
        hmp.put(mName("FullDownVal"), new Param(2250));
    }

    public void lower() {
        ploop.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ploop.setPower(1);
        encMode = false;
    }

    public void raise() {
        ploop.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ploop.setPower(-1);
        encMode = false;
    }

    public void stop() {
        if(!encMode) {
            ploop.setPower(0);
        }
    }

    public void fullIn(){
        ploop.setPower(1);
        ploop.setTargetPosition((int)(hmp.get(mName("FullInVal")).getValue()));
        encMode = true;
        ploop.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void goToDump(){
        ploop.setPower(1);
        ploop.setTargetPosition((int)(hmp.get(mName("DumpVal")).getValue()));
        encMode = true;
        ploop.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void fullDown(){
        ploop.setPower(1);
        ploop.setTargetPosition((int)(hmp.get(mName("FullDownVal")).getValue()));
        encMode = true;
        ploop.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void stopAndReset(){
        ploop.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
