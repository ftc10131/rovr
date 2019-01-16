package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Forward", group="Test")
public class ForwardAuton extends RovrAuton{

    public ForwardAuton(){
        super();
    }

    @Override
    public void runMe(){
       forwardTile();
    }

}