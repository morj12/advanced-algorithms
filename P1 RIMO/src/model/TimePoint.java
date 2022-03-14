package model;

public class TimePoint {

    private final long time;
    private final int stopNumber;
    private final int algoIndex;
    
    public TimePoint(int algoIndex, long time, int stopNumber) {
        this.algoIndex = algoIndex;
        this.time = time;
        this.stopNumber = stopNumber;
    }
    
    public int getAlgoIndex() {
        return algoIndex;
    }
    
    public long getTime() {
        return time;
    }
    
    public int getStopNumber() {
        return stopNumber;
    }
    
    @Override
    public String toString() {
        return "id:" + algoIndex + ", temps:" + time + ", n:" + stopNumber;
    }
}
