package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "LandSampleCapture", group = "Autonomous")
public class LSCPCrater extends RovrAuton {

    public LSCPCrater() {
        super();
    }

    @Override
    public void runMe() {
        land();
        sample();
        startingAtCrater = true;
        claimAfterSample();
        parkAfterClaim();
        hyrule.stop();
    }

}
