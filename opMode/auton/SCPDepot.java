package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="SCPDepot", group="Depot")
public class SCPDepot extends RovrAuton{

    public SCPDepot(){
        super();
    }

    @Override
    public void runMe(){
       sample();
       startingAtCrater=false;
       claimAfterSample();
       parkAfterClaim();
    }

}