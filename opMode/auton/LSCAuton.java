package rovr.opMode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="LandSampleCapture", group="Autonomous")
public class LSCAuton extends LaSAuton{

    public LSCAuton(){
        super();
        paramFileName = "LSCParams";
    }

    @Override
    public void runMe(){
        super.runMe();
        hyrule.intake.in();
        switch (goldLocation){

            case 1:
                hyrule.driveTrain.holoDrive(0,0.5,0);
                sleep(1500);
                hyrule.driveTrain.stop();
                hyrule.intake.out();
                sleep(2000);
                break;
            default:
                break;
        }
    }

}
