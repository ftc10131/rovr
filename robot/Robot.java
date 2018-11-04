package rovr.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;
import java.util.List;

import rovr.util.Param;

public class Robot {

    public DriveTrain driveTrain;
    public MineralDumper blockDumper;
    public MineralDumper ballDumper;
    public Hanger hanger;
    public Ploop ploop;
    public Intake intake;
    public MarkerDumper markerDumper;
    public Sorter sorter;

    HardwareMap hardwareMap;

    List<Mechanism> mechanisms;

    public void Robot(HardwareMap h){
        hardwareMap = h;
    }

    public void init(){
        driveTrain = new DriveTrain("driveTrain", hardwareMap);
        blockDumper = new MineralDumper("blockDumper", hardwareMap);
        ballDumper = new MineralDumper("ballDumper", hardwareMap);
        hanger = new Hanger("hanger", hardwareMap);
        ploop = new Ploop("ploop", hardwareMap);
        intake = new Intake("intake", hardwareMap);
        markerDumper = new MarkerDumper("markerDumper", hardwareMap);
        sorter = new Sorter("sorter", hardwareMap);

        mechanisms.add(driveTrain);
        mechanisms.add(blockDumper);
        mechanisms.add(ballDumper);
        mechanisms.add(hanger);
        mechanisms.add(ploop);
        mechanisms.add(intake);
        mechanisms.add(markerDumper);
        mechanisms.add(sorter);

        for(Mechanism m: mechanisms){
            m.init();
        }
    }

    public void start(){
        for(Mechanism m: mechanisms){
            m.start();
        }
    }

    public void stop(){
        for(Mechanism m: mechanisms){
            m.stop();
        }
    }

    public void paramTelemetry(LinearOpMode om){
        for(Mechanism m: mechanisms){
            if(!m.hm.isEmpty()){
                for(String s: m.hm.keySet()){
                    om.telemetry.addData(s,m.hm.get(s).getValue());
                }
            }
        }
    }
}
