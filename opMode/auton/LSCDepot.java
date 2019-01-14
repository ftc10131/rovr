package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name="LSCDepot", group="Depot")
public class LSCDepot extends RovrAuton{

    public LSCDepot(){
        super();
    }

    @Override
    public void runMe(){
       land();
       sample();
       startingAtCrater=false;
       claimAfterSample();
    }

}