package gr.uop;

public class Profile {
    private String name;
    private TimeDisplay start;
    private TimeDisplay stop;
    private double temperature;
    private boolean active;

    public Profile(String name, TimeDisplay start, TimeDisplay stop, double temperature) {
        this.name = name;
        this.start = start;
        this.stop = stop;
        this.temperature = temperature;
        this.active = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeDisplay getStart() {
        return start;
    }

    public void setStart(TimeDisplay start) {
        this.start = start;
    }

    public TimeDisplay getStop() {
        return stop;
    }

    public void setStop(TimeDisplay stop) {
        this.stop = stop;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
