package rovr.opMode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp: Ploop Down")
public class TeleopPloopDown extends TeleopPloopUp{

    @Override
    public void init(){
        super.init();
        hyrule.ploop.startingDown();
    }

}
