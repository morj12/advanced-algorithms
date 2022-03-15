package model;

public class TimePoint {

    private final long time;
    private final int iterations;

    public TimePoint(long time, int iterations) {
        this.time = time;
        this.iterations = iterations;
    }

    public long getTime() {
        return time;
    }
    
    public int getIterations() {
        return iterations;
    }

    @Override
    public String toString() {
        return "TimePoint{" +
                "time=" + time +
                ", stopNumber=" + iterations +
                '}';
    }
}
