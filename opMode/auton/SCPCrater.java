package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "SCPCrater", group = "Crater")
public class SCPCrater extends RovrAuton {

    public SCPCrater() {
        super();
    }

    @Override
    public void runMe() {
        sample();
        startingAtCrater = true;
        claimAfterSample();
        parkAfterClaim();
    }

}
