package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name="LSCPDepot", group="Depot")
public class LSCPDepot extends RovrAuton{

    public LSCPDepot(){
        super();
    }

    @Override
    public void runMe(){
       land();
       sample();
       startingAtCrater=false;
       claimAfterSample();
       parkAfterClaim();
    }

}