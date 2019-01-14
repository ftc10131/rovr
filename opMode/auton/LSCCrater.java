package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "LSCCrater", group = "Crater")
public class LSCCrater extends RovrAuton {

    public LSCCrater() {
        super();
    }

    @Override
    public void runMe() {
        land();
        sample();
        startingAtCrater = true;
        claimAfterSample();
    }

}
