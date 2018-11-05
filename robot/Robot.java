package rovr.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
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

    public ArrayList<Mechanism> mechanisms;

    public Robot(HardwareMap h){
        hardwareMap = h;

        driveTrain = new DriveTrain("driveTrain", hardwareMap);
        blockDumper = new MineralDumper("blockDumper", hardwareMap);
        ballDumper = new MineralDumper("ballDumper", hardwareMap);
        hanger = new Hanger("hanger", hardwareMap);
        ploop = new Ploop("ploop", hardwareMap);
        intake = new Intake("intake", hardwareMap);
        markerDumper = new MarkerDumper("markerDumper", hardwareMap);
        sorter = new Sorter("sorter", hardwareMap);

        mechanisms = new ArrayList<>();
        mechanisms.add(driveTrain);
        mechanisms.add(blockDumper);
        mechanisms.add(ballDumper);
        mechanisms.add(hanger);
        mechanisms.add(ploop);
        mechanisms.add(intake);
        mechanisms.add(markerDumper);
        mechanisms.add(sorter);
    }

    public void init(){
        for (int i=0; i<mechanisms.size(); i++ ){
     //   for(Mechanism m: mechanisms){
            mechanisms.get(i).init();
        }
    }

    public void start(){
        for (int i=0; i<mechanisms.size(); i++ ){
            //   for(Mechanism m: mechanisms){
            mechanisms.get(i).start();
        }
    }

    public void stop(){
        for (int i=0; i<mechanisms.size(); i++ ){
            //   for(Mechanism m: mechanisms){
            mechanisms.get(i).stop();
        }
    }

    public void paramTelemetry(LinearOpMode om){
        for(Mechanism m: mechanisms){
            if(!m.hmp.isEmpty()){
                for(String s: m.hmp.keySet()){
                    om.telemetry.addData(s,m.hmp.get(s).getValue());
                }
            }
        }
    }
}
