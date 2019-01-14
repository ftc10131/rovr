package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

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