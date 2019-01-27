package rovr.opMode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp for States: Ploop Down")
public class TeleopPloopDownStates extends TeleopPloopUpStates{

    @Override
    public void init(){
        super.init();
        hyrule.ploop.startingDown();
    }

}
