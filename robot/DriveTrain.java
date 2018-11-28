package rovr.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import rovr.robot.sensors.Gyro;

public class DriveTrain extends Mechanism {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    int headingOffset;


    public DriveTrain(String n, HardwareMap hardwareMap){
        //name = n;
        super(n, hardwareMap);
        frontLeft = getHwMotor("frontLeft");
        frontRight = getHwMotor("frontRight");
        backLeft = getHwMotor("backLeft");
        backRight = getHwMotor("backRight");
    }

    public void init(){
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        headingOffset = 0;

        stop();
    }

    public void holoDrive(double x, double y, double turny) {
        frontLeft.setPower(-y - x - turny);
        frontRight.setPower(-y + x + turny);
        backLeft.setPower(-y + x - turny);
        backRight.setPower(-y - x + turny);
    }

    public void holoGyro(double x, double y, double turny, int heading){
        heading += headingOffset;
        double headingX = x * Math.cos(((double)heading)/180.0*Math.PI)+ y * Math.sin(((double)heading)/180.0*Math.PI);
        double headingY = y * Math.cos(((double)heading)/180.0*Math.PI)- x * Math.sin(((double)heading)/180.0*Math.PI);
        holoDrive(headingX,headingY,turny);
    }

    public void turnDegrees(double degrees, Gyro gyro, LinearOpMode om){
        double startTime = om.getRuntime();
        double startingDegrees = -gyro.getHeading();
        double currentDegrees = startingDegrees;
        double turnedDegrees = currentDegrees - startingDegrees;
        if(turnedDegrees > 180)
            turnedDegrees -= 360;
        if(turnedDegrees < -180)
            turnedDegrees += 360;
        if(degrees > 1){
            holoDrive(0,0, 0.3);
            while(om.opModeIsActive() && om.getRuntime() - startTime < 3 && turnedDegrees < degrees){
                turnedDegrees = -gyro.getHeading() - startingDegrees;
                if(turnedDegrees > 180)
                    turnedDegrees -= 360;
                if(turnedDegrees < -180)
                    turnedDegrees += 360;
                om.telemetry.addData("Starting Degrees: ", startingDegrees);
                om.telemetry.addData("Current Degrees: ", currentDegrees);
                om.telemetry.addData("Turned Degrees: ", turnedDegrees);
                om.telemetry.addData("Gyro Heading: ", gyro.getHeading());
                om.telemetry.update();
            }
        }else if(degrees < -1){
            holoDrive(0,0, -0.3);
            while(om.opModeIsActive() && om.getRuntime() - startTime < 3 && turnedDegrees > degrees){
                turnedDegrees = -gyro.getHeading() - startingDegrees;
                if(turnedDegrees > 180)
                    turnedDegrees -= 360;
                if(turnedDegrees < -180)
                    turnedDegrees += 360;
                om.telemetry.addData("Starting Degrees: ", startingDegrees);
                om.telemetry.addData("Current Degrees: ", currentDegrees);
                om.telemetry.addData("Turned Degrees: ", turnedDegrees);
                om.telemetry.addData("Gyro Heading: ", gyro.getHeading());
                om.telemetry.update();
            }
        }else{

        }
        stop();
    }

    public void start(){

    }

    public void stop() {
        holoDrive(0,0,0);
    }

    public void setHeadingOffset(int h){
        headingOffset = h;
    }
}
