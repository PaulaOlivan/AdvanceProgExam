package gr.uop;

public class TimeDisplay {
    private int hours;
    private int minutes;
    
    public TimeDisplay(int h, int m) {
        hours = h;
        minutes = m;
    }

    public TimeDisplay(String time){
        String h = time.substring(0, 2);
        String m = time.substring(3);
        hours = Integer.parseInt(h);
        minutes = Integer.parseInt(m);
    }
    
    public void tickUp() {
        minutes++;
        if (minutes == 60)  {
            minutes = 0;
            hours++;
            if (hours == 24)  {
                hours = 0;
            }
        }
    }
    
    public void tickDown() {
        minutes--;
        if (minutes == -1)  {
            minutes = 59;
            hours--;
            if (hours == -1)  {
                hours = 23;
            }
        }
    }

    @Override
    public String toString(){
        String hour;
        String min;
        if (hours < 10){
            hour = "0" + hours;
        }
        else{
            hour = hours + "";
        }
        if (minutes < 10){
            min = "0" + minutes;
        }
        else{
            min = minutes + ""; 
        }
        return hour + ":" + min;
    }
}
