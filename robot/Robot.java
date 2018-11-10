package rovr.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rovr.robot.sensors.Gyro;
import rovr.util.Param;
import rovr.util.ParamManager;

public class Robot extends Mechanism{

    public DriveTrain driveTrain;
    public MineralDumper blockDumper;
    public MineralDumper ballDumper;
    public Hanger hanger;
    public Ploop ploop;
    public Intake intake;
    public MarkerDumper markerDumper;
    public Sorter sorter;
    public Gyro gyro;
    public ParamManager paramManager;

    public String paramFileName;

    HardwareMap hardwareMap;

    public ArrayList<Mechanism> mechanisms;

    public Robot(HardwareMap h){
        super("hyrule", h);

        hardwareMap = h;

        driveTrain = new DriveTrain("driveTrain", hardwareMap);
        blockDumper = new MineralDumper("blockDumper", hardwareMap);
        ballDumper = new MineralDumper("ballDumper", hardwareMap);
        hanger = new Hanger("hanger", hardwareMap);
        ploop = new Ploop("ploop", hardwareMap);
        intake = new Intake("intake", hardwareMap);
        markerDumper = new MarkerDumper("markerDumper", hardwareMap);
        sorter = new Sorter("sorter", hardwareMap);
        gyro = new Gyro("imu",hardwareMap);
        paramManager = new ParamManager();

        mechanisms = new ArrayList<>();
        mechanisms.add(driveTrain);
        mechanisms.add(blockDumper);
        mechanisms.add(ballDumper);
        mechanisms.add(hanger);
        mechanisms.add(ploop);
        mechanisms.add(intake);
        mechanisms.add(markerDumper);
        mechanisms.add(sorter);
        mechanisms.add(gyro);

        paramFileName = mName("Params");
    }

    public void init(){
        for (int i=0; i<mechanisms.size(); i++ ){
     //   for(Mechanism m: mechanisms){
            mechanisms.get(i).init();
            for(String s : mechanisms.get(i).hmp.keySet()){
                mechanisms.get(i).hmp.keySet();
                hmp.put(s,mechanisms.get(i).hmp.get(s));
            }

        }

        paramManager.loadFromFile(hardwareMap.appContext, paramFileName,hmp);

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
