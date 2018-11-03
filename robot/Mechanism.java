package rovr.robot;
import rovr.util.Param;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;

public class Mechanism {

    public String name = null;
    HardwareMap  hardwareMap;
    HashMap<String,Param> hm;

    public Mechanism (String n, HardwareMap h){
        name = n;
        hardwareMap = h;
        hm = new HashMap<String,Param>();
    }

    public Mechanism(){

    }

    public void init(){

    }

    public void start(){

    }

    public void stop(){

    }
}
