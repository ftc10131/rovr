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

package rovr.opMode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import rovr.robot.Robot;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name = "TeleOp for States: Ploop Up")
//@Disabled
public class TeleopPloopUpStates extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    Robot hyrule ;
    double driveSpeed;
    double turnSpeed;
    boolean leftStickPressed;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        //hyrule = new Robot(hardwareMap);
        // Tell the driver that initialization is complete.
        hyrule = new Robot(hardwareMap);
        hyrule.init(false);
        telemetry.addData("Status", "Initialized");
        driveSpeed = 1;
        turnSpeed = 0.75;
        leftStickPressed = false;
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
        hyrule.start();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        hyrule.driveTrain.holoGyro(gamepad1.left_stick_x * driveSpeed, -gamepad1.left_stick_y * driveSpeed, gamepad1.right_stick_x * turnSpeed,(int)hyrule.gyro.getHeading());

        if(gamepad1.left_stick_button && !leftStickPressed){
            if(driveSpeed > 0.9) {
                driveSpeed = 0.5;
                turnSpeed = 0.5;
            }else if(driveSpeed < 0.6) {
                driveSpeed = 1.0;
                turnSpeed = 0.75;
            }
        }
        leftStickPressed = gamepad1.left_stick_button;

        if (gamepad1.dpad_up) {
            hyrule.gyro.resetHeadingForward();
        }
        if (gamepad1.dpad_left) {
            hyrule.gyro.resetHeadingLeft();
        }
        if(gamepad1.dpad_right){
            hyrule.gyro.resetHeadingRight();
        }
        if(gamepad1.dpad_down){
            hyrule.gyro.resetHeadingBackward();
        }

        if(gamepad2.right_trigger > 0.5){
            hyrule.intake.in();
        }else if(gamepad2.right_bumper){
            hyrule.intake.out();
        }else{
            hyrule.intake.stopPower();
        }

        if(gamepad2.x){
            hyrule.sorter.dumpBlock();
        }else if(gamepad2.b){
            hyrule.sorter.dumpBall();
        }else{
            hyrule.sorter.center();
        }

        if(gamepad1.right_stick_button){
            hyrule.gyro.resetHeadingForward();
        }


        if (gamepad2.left_stick_y < -0.5) {
            hyrule.hanger.dropRobot();
        } else if (gamepad2.left_stick_y > 0.5) {
            hyrule.hanger.liftRobot();
        } else hyrule.hanger.stopWinch();

        if(gamepad2.right_stick_y < -0.5){
            hyrule.hanger.lock();
        }
        if(gamepad2.right_stick_y > 0.5){
            hyrule.hanger.unlock();
        }

        if (gamepad1.a) {
            hyrule.ploop.fullDown();
        } else if (gamepad1.y) {
            hyrule.ploop.fullIn();
        } else if(gamepad1.x){
            hyrule.ploop.goToDump();
        }else if(gamepad1.left_bumper){
            hyrule.ploop.raise();
        }else if(gamepad1.left_trigger > 0.5){
            hyrule.ploop.lower();
        }else{
            hyrule.ploop.stop();
        }
        //Not sure if this is official button
        if(gamepad1.right_trigger > 0.5 && gamepad1.right_bumper){
            hyrule.ploop.startingDown();
        }



        if(gamepad2.y) {
            hyrule.ballDumper.slowDump(this);
            hyrule.blockDumper.slowDump(this);
        }else{
            hyrule.ballDumper.collect();
            hyrule.blockDumper.collect();
        }

        //THIS IS THE LAST LINE DON"T PUT ANYTHING FUNCTIONAL AFTER IT
        hyrule.stopIfStalled();
        // Show the elapsed game time and wheel power.
        telemetry.addData("Heading: ", hyrule.gyro.getHeading());
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        hyrule.stop();
    }

}