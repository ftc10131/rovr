package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="StrafeRight", group="Test")
public class StrafeRightAuton extends RovrAuton{

    public StrafeRightAuton(){
        super();
    }

    @Override
    public void runMe(){
       rightTile();
    }

}