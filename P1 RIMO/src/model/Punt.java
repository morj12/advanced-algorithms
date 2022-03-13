package model;

public class Punt {

    private long temps;
    private int n;
    private int id;
    
    public Punt(int id, long temps, int n) {
        this.id = id;
        this.temps = temps;
        this.n = n;
    }
    
    public int getId() {
        return id;
    }
    
    public long getTemps() {
        return temps;
    }
    
    public int getN() {
        return n;
    }
    
    @Override
    public String toString() {
        return "id:" + id + ", temps:" + temps + ", n:" + n;
    }
}
