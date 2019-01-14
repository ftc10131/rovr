package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="LandSampleCapture", group="Autonomous")
public class LSCPDepot extends RovrAuton{

    public LSCPDepot(){
        super();
        paramFileName = "LSCParams";
    }

    @Override
    public void runMe(){
       land();
       sample();
       startingAtCrater=false;
       claimAfterSample();
       parkAfterClaim();
       hyrule.stop();
    }

}