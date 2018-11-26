package espol.edu.ec.tda;

/**
 *
 * @author MiguelPS
 */
public class Entry{
    Integer n;
    Double time;

    public Entry(Integer n, Double time) {
        this.n = n;
        this.time = time;
    }

    public Integer getN() {
        return n;
    }

    public Double getTime() {
        return time;
    }
    
    public String toString(){
        return String.valueOf(n) + String.valueOf(time);
    }
}
