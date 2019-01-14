package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "SCCrater", group = "Crater")
public class SCCrater extends RovrAuton {

    public SCCrater() {
        super();
    }

    @Override
    public void runMe() {
        sample();
        startingAtCrater = true;
        claimAfterSample();
    }

}
