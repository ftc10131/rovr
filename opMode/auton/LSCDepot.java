package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="LandSampleCapture", group="Autonomous")
public class LSCDepot extends RovrAuton{

    public LSCDepot(){
        super();
        paramFileName = "LSCParams";
    }

    @Override
    public void runMe(){
       land();
       sample();
       startingAtCrater=false;
       claimAfterSample();
       hyrule.stop();
    }

}