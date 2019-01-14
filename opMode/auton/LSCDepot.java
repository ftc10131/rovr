package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

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