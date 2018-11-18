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

package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="LandAndSample", group="Autonomous")
//@Disabled
public class LaSAuton extends SampleAuton {

    public int goldLocation;

    public LaSAuton(){
        super();
        //hmp.put("",1);
        paramFileName = "LandAndSampleAutonParams";
    }

    @Override
    public void runMe(){
        hyrule.hanger.land(this);
        hyrule.driveTrain.holoDrive(-0.5,0.1,0);
        sleep(400);
        hyrule.driveTrain.stop();
        hyrule.hanger.pullDownHalfway(this);
        //hyrule.driveTrain.holoDrive(0,00,0);
        //sleep(15000);
        hyrule.driveTrain.holoDrive(0.5,0,0);
        sleep(400);
        hyrule.driveTrain.holoDrive(0,-0.5,0);
        sleep(333);
        hyrule.driveTrain.stop();
        hyrule.ploop.autonFullDown(this);
        hyrule.sorter.dumpBlock();
        sleep(250);
        //Insert vision check for gold mineral here
        goldLocation = 1;
        switch (goldLocation){
            case 1:
                hyrule.intake.in();
                hyrule.driveTrain.holoDrive(0,0.5,0);
                sleep(1000);
                hyrule.driveTrain.stop();
                hyrule.intake.stopPower();
                break;
            default:
        }

        hyrule.driveTrain.stop();

        super.runMe();
    }

}
