package gr.uop;

public class TimeDisplay {
    private int hours;
    private int minutes;
    
    public TimeDisplay(int h, int m) {
        hours = h;
        minutes = m;
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
}
