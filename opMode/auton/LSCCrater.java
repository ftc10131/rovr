package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "LandSampleCapture", group = "Autonomous")
public class LSCCrater extends RovrAuton {

    public LSCCrater() {
        super();
        paramFileName = "LSCParams";
    }

    @Override
    public void runMe() {
        land();
        sample();
        startingAtCrater = true;
        claimAfterSample();
        hyrule.stop();
    }

}
