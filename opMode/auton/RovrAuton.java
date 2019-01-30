package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.HashMap;

import rovr.robot.Robot;
import rovr.util.Param;
import rovr.util.ParamManager;

/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name = "Rovr", group = "Autonomous")
@Disabled
public class RovrAuton extends BasicAuton {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    boolean editing;
    boolean startingAtCrater;
    double degrees;
    ParamManager paramManager;
    public String paramFileName;
    boolean intakeDuringSampling;

    public RovrAuton() {
        super();
        startingAtCrater = true;
        degrees = 0;
        paramFileName = "RovrAutonParams";
        initParams();

        intakeDuringSampling=true;
    }
    @Override
    public void initParams(){
        super.initParams();
        hmp.put("RAClaimSteps", new Param(13));
        setParamUpdateStep("RAClaimSteps", 1);
        hmp.put("RAC-StrafeTiles1", new Param(-2.3));
        setParamUpdateStep("RAC-StrafeTiles1", 0.1);
        hmp.put("RAC-StrafeTiles2", new Param(-1.0));
        setParamUpdateStep("RAC-StrafeTiles2", 0.1);
        hmp.put("RAC-StrafeTiles3", new Param(0.1));
        setParamUpdateStep("RAC-StrafeTiles3", 0.05);
        hmp.put("RAC-StrafeTiles4", new Param(0.4));
        setParamUpdateStep("RAC-StrafeTiles4", 0.05);
        hmp.put("RAC-MoveTiles1", new Param(-2.5));
        setParamUpdateStep("RAC-MoveTiles1", 0.05);
        hmp.put("RAC-TurnToDump1", new Param(15));
        setParamUpdateStep("RAC-TurnToDump1", 0.05);
        hmp.put("RAP-TilesToPark", new Param(3.4));
        setParamUpdateStep("RAP-TilesToPark", 0.1);
        hmp.put("RAC-Move1", new Param(0.75));
        setParamUpdateStep("RAC-Move1", 0.05);
        hmp.put("RA-Speed", new Param(0.6));
        setParamUpdateStep("RA-Speed", 0.05);
    }

    public void forwardTile() {
        sleep(500);
        hyrule.driveTrain.moveEnc(0.5, 1, this);
        sleep(500);
    }

    public void rightTile() {
        sleep(500);
        hyrule.driveTrain.strafeEnc(0.5, 1, this);
        sleep(500);
    }

    public void land() {
        hyrule.hanger.land(this);
        hyrule.driveTrain.holoDrive(-0.5, 0.1, 0);
        sleep(400);
        hyrule.driveTrain.stop();
        hyrule.hanger.pullDownHalfway(this);
        //hyrule.driveTrain.holoDrive(0,00,0);
        //sleep(15000);
        hyrule.driveTrain.holoDrive(0.5, 0, 0);
        sleep(400);
        hyrule.driveTrain.holoDrive(0, -0.5, 0);
        sleep(333);
        hyrule.driveTrain.stop();
    }

    public void sample() {
        hyrule.ploop.autonFullDown(this);
        //sleep(250);

        degrees = 0.85 * hyrule.vision.degreesToGold(this);

        hyrule.driveTrain.holoDrive(0, 0.33, 0);
        sleep(500);
        hyrule.driveTrain.stop();
        sleep(250);

        hyrule.driveTrain.turnDegrees(degrees, hyrule.gyro, this);
        if (intakeDuringSampling)
            hyrule.intake.in();
        else
            hyrule.intake.out();
        hyrule.driveTrain.holoDrive(0, 0.5, 0);
        if (degrees != 0)
            sleep(300);
        sleep(1000);
        hyrule.driveTrain.stop();
        //sleep(1000);
        hyrule.intake.stopPower();
    }

    public void claimAfterSample() {
        Param p = (Param) hmp.get("RAClaimSteps");
        int steps = (int) p.getValue();
        //back up
        if (intakeDuringSampling)
            hyrule.intake.in();
        hyrule.driveTrain.holoDrive(0, -0.5, 0);
        if (degrees != 0)
            sleep(300);
        sleep(750);
        hyrule.intake.stopPower();
        sleep(250);
        hyrule.driveTrain.stop();
        if (steps <= 1 + 0.01) return;
        //ploop up
        hyrule.ploop.startingDown();
        hyrule.ploop.autonFullUp(this);
        if (steps <= 2 + 0.01) return;
        //turn back
        hyrule.driveTrain.turnDegrees(-degrees, hyrule.gyro, this);
        if (steps <= 3 + 0.01) return;
        //move??
        hyrule.driveTrain.moveEnc(getPVal("RA-Speed"), getPVal("RAC-Move1"), this);
        if (steps <= 4 + 0.01) return;
        //strafe left (1.4 tiles?)
        hyrule.driveTrain.strafeEnc(getPVal("RA-Speed"), getPVal("RAC-StrafeTiles1"), this);
        if (steps <= 5 + 0.01) return;
        //turn back towards depot
        hyrule.driveTrain.holoGyro(-0.5*getPVal("RA-Speed"),0.5*getPVal("RA-Speed"),0.0, (int)hyrule.gyro.getHeading());
        sleep(333);
        hyrule.driveTrain.stop();
        if (startingAtCrater == true) {
            hyrule.driveTrain.turnDegrees(40, hyrule.gyro, this);
        } else {
            hyrule.driveTrain.turnDegrees(-100, hyrule.gyro, this);

        }
        sleep(100);
        if (steps <= 6 + 0.01) return;
        //strafe to wall and away a bit
        if (startingAtCrater == true) {
            hyrule.driveTrain.strafeEnc(getPVal("RA-Speed"), getPVal("RAC-StrafeTiles2"), this);
            hyrule.driveTrain.strafeEnc(getPVal("RA-Speed"), getPVal("RAC-StrafeTiles3"), this);
        } else {
            hyrule.driveTrain.strafeEnc(getPVal("RA-Speed"), -getPVal("RAC-StrafeTiles2"), this);
            hyrule.driveTrain.strafeEnc(getPVal("RA-Speed"), -getPVal("RAC-StrafeTiles3"), this);
        }
        if (steps <= 7 + 0.01) return;
        //back up 1.8 tiles
        hyrule.driveTrain.moveEnc(getPVal("RA-Speed"), getPVal("RAC-MoveTiles1"), this);
        if (steps <= 8 + 0.01) return;
        //Strafe into wall and out a bit
        if (startingAtCrater == true) {
            hyrule.driveTrain.strafeEnc(getPVal("RA-Speed"), -getPVal("RAC-StrafeTiles4"), this);
            hyrule.driveTrain.strafeEnc(getPVal("RA-Speed"), getPVal("RAC-StrafeTiles3"), this);
        } else {
            hyrule.driveTrain.strafeEnc(getPVal("RA-Speed"), getPVal("RAC-StrafeTiles4"), this);
            hyrule.driveTrain.strafeEnc(getPVal("RA-Speed"), -getPVal("RAC-StrafeTiles3"), this);
        }
        if (steps <= 9 + 0.01) return;
        //ploop down
        hyrule.ploop.autonFullDown(this);
        if (steps <= 10 + 0.01) return;
        //dump marker
        if (startingAtCrater == true) {
            hyrule.blockDumper.dump();
            sleep(1000);
            hyrule.blockDumper.collect();
            sleep(500);
        } else {
            hyrule.ballDumper.dump();
            sleep(1000);
            hyrule.ballDumper.collect();
            sleep(500);
        }
        if (steps <= 11 + 0.01) return;
        //strafe into wall and out again
        /*if (startingAtCrater == true) {
            hyrule.driveTrain.strafeEnc(getPVal("RA-Speed"), -getPVal("RAC-StrafeTiles4"), this);
            hyrule.driveTrain.strafeEnc(getPVal("RA-Speed"), getPVal("RAC-StrafeTiles3"), this);
        } else {
            hyrule.driveTrain.strafeEnc(getPVal("RA-Speed"), getPVal("RAC-StrafeTiles4"), this);
            hyrule.driveTrain.strafeEnc(getPVal("RA-Speed"), -getPVal("RAC-StrafeTiles3"), this);
        }
        if (steps <= 12 + 0.01) return;*/
    }

    public void parkAfterClaim() {
        hyrule.ploop.autonFullUp(this);
        hyrule.driveTrain.moveEnc(getPVal("RA-Speed"), getPVal("RAP-TilesToPark"), this);
        hyrule.driveTrain.diagonalEnc(getPVal("RA-Speed"),0.75,this,startingAtCrater);

    }

    public double getPVal(String s) {
        Param p = (Param) hmp.get(s);
        return p.getValue();
    }

    public void setParamUpdateStep(String s, double d) {
        Param p = (Param) hmp.get(s);
        p.setUpdateStep(d);
    }
}
