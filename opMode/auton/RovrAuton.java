package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
    public HashMap hmp;
    boolean editing;
    boolean startingAtCrater;
    double degrees;
    ParamManager paramManager;
    public String paramFileName;

    public RovrAuton() {
        super();
        startingAtCrater = true;
        degrees = 0;
        paramFileName = "RovrAutonParams";
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
        sleep(250);

        degrees = 1.05 * hyrule.vision.degreesToGold(this);

        hyrule.driveTrain.holoDrive(0, 0.33, 0);
        sleep(500);
        hyrule.driveTrain.stop();
        sleep(500);

        hyrule.driveTrain.turnDegrees(degrees, hyrule.gyro, this);
        hyrule.intake.in();
        hyrule.driveTrain.holoDrive(0, 0.5, 0);
        if (degrees != 0)
            sleep(1000);
        sleep(1000);
        hyrule.driveTrain.stop();
        sleep(1000);
        hyrule.intake.stopPower();
    }

    public void claimAfterSample() {
        //ploop up
        hyrule.ploop.autonFullUp(this);
        //back up
        hyrule.driveTrain.holoDrive(0, -0.5, 0);
        if (degrees != 0)
            sleep(1000);
        sleep(1000);
        hyrule.driveTrain.stop();
        //turn back
        hyrule.driveTrain.turnDegrees(-degrees, hyrule.gyro, this);
        //move
        hyrule.driveTrain.moveEnc(0.5, 0.5 , this);
        //strafe left (1.4 tiles?)
        hyrule.driveTrain.strafeEnc(-0.5, 1.4 , this);
        //turn back towards depot
        if (startingAtCrater == true) {
            hyrule.driveTrain.turnDegrees(-45, hyrule.gyro, this);
        } else {
            hyrule.driveTrain.turnDegrees(135, hyrule.gyro, this);

        }
        //strafe to wall and away a bit
        if (startingAtCrater == true) {
            hyrule.driveTrain.strafeEnc(-0.5, 0.5 , this);
            hyrule.driveTrain.strafeEnc(0.5, 0.1 , this);
        } else {
            hyrule.driveTrain.strafeEnc(0.5, 0.5 , this);
            hyrule.driveTrain.strafeEnc(-0.5, 0.1 , this);
        }

        //back up 1.8 tiles
        hyrule.driveTrain.moveEnc(-0.5, 1.8 , this);
        //ploop down
        hyrule.ploop.autonFullDown(this);
        //turn a little
        if (startingAtCrater == true) {
            hyrule.driveTrain.turnDegrees(15, hyrule.gyro, this);
        } else {
            hyrule.driveTrain.turnDegrees(-15, hyrule.gyro, this);
        }
        //dump marker
        if (startingAtCrater == true) {
            hyrule.blockDumper.dump();
            sleep(1000);
            hyrule.blockDumper.collect();
            sleep(1000);
        } else {
            hyrule.ballDumper.dump();
            sleep(1000);
            hyrule.ballDumper.collect();
            sleep(1000);
        }
        //turn back
        if (startingAtCrater == true) {
            hyrule.driveTrain.turnDegrees(-15, hyrule.gyro, this);
        } else {
            hyrule.driveTrain.turnDegrees(15, hyrule.gyro, this);
        }
    }

    public void parkAfterClaim() {
        hyrule.ploop.autonFullUp(this);
        hyrule.driveTrain.moveEnc(0.5, 3 , this);
    }
}
