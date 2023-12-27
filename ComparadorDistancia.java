import java.util.Comparator;
/**
 * Write a description of class ComparadorDistancia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparadorDistancia implements Comparator<Taxi>
{
    private Location lo;
    public ComparadorDistancia (Location loc){
        lo = loc;
    }
    public int compare(Taxi t1, Taxi t2){
        if(t1.distToLoc(lo) > t2.distToLoc(lo)){
            return 1;
        }
        else{
           if(t1.distToLoc(lo) == t2.distToLoc(lo)){
            return -1;
        }
        else{
            return t1.getName().compareTo(t2.getName());
        }
        }
        
    }
}
