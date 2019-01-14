package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="SCDepot", group="Depot")
public class SCDepot extends RovrAuton{

    public SCDepot(){
        super();
    }

    @Override
    public void runMe(){
       sample();
       startingAtCrater=false;
       claimAfterSample();
       hyrule.stop();
    }

}