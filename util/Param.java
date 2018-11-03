package rovr.util;

public class Param {

    private double value;
    boolean hasMax;
    double max;
    boolean hasMin;
    double min;
    double updateSpeed;

    public Param (double v){
        value = v;
        hasMax = false;
        max = 1;
        hasMin = false;
        min = -1;
        updateSpeed = 1;
    }

    public void setValue(double input){
        if(hasMax && input >= max) {
            value = max;
        }else if(hasMin && input <= min){
            value = min;
        }else{
            value = input;
        }
    }
    public double getValue(){
        return value;
    }

}
