import java.util.Comparator;

/**
 * Compare taxis by name in ascending order.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparadorTaxi implements Comparator<Taxi>
{
    public int compare(Taxi t1,Taxi t2){
        if(t1.passengersTransported()>t2.passengersTransported())
           return 1; 
        else if(t1.passengersTransported()<t2.passengersTransported())
            return -1;
        else return (t1.getName().compareTo(t2.getName()));
    }
}
