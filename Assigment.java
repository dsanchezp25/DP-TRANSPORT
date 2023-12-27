/**
 * Write a description of class Assigment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Assigment
{
    // instance variables - replace the example below with your own
    private Taxi t;
    private Passenger p;

    /**
     * Constructor for objects of class Assigment
     */
    public Assigment(Taxi taxi, Passenger passenger)
    {
        // initialise instance variables
        taxi.setTargetLocation(passenger.getRecogida());
        this.t = taxi;
        this.p = passenger;

    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public Taxi getAssigTaxi()
    {
        return this.t;
    }

    public void setAssigTaxi(Taxi taxi)
    {
        this.t = taxi;
        this.t.setTargetLocation(this.p.getRecogida());
    }

    public Passenger getAssigPassenger()
    {
        return this.p;
    }

    public void setAssigPassenger(Passenger pasajero)
    {
        this.p = pasajero;
        this.t.setTargetLocation(pasajero.getRecogida());
    }

}
