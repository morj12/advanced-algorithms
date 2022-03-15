package model;

public class TimePoint {

    private final long time;
    private final int stopNumber;

    public TimePoint(long time, int stopNumber) {
        this.time = time;
        this.stopNumber = stopNumber;
    }

    public long getTime() {
        return time;
    }
    
    public int getStopNumber() {
        return stopNumber;
    }

    @Override
    public String toString() {
        return "TimePoint{" +
                "time=" + time +
                ", stopNumber=" + stopNumber +
                '}';
    }
}
