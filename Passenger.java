import java.util.List;

/**
 * Model a passenger wishing to get from one
 * location to another.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023.10.10 DP classes 
 */
public class Passenger
{
    private final String name; //nombre
    private final Location pickup; //localizacion del pasajero
    private final Location destination; //destino del pasajero
    private String taxiName; //taxi que ha usado
    private int arrivalTime;
    private int creditCard;
    private Reliable reliable;
    //incluir campo para el nombre del taxi que lo ha transportado

    /**
     * Constructor for objects of class Passenger
     *
     * @param pickup      The pickup location, must not be null.
     * @param destination The destination location, must not be null.
     * @param name        The passenger's name
     * @throws NullPointerException If either location is null.
     */

    //Metodo que crea un pasajero
    public Passenger(Location pickup, Location destination, String name)
    {
        if(pickup == null) {
            throw new NullPointerException("Pickup location");
        }
        if(destination == null) {
            throw new NullPointerException("Destination location");
        }
        this.pickup = pickup;
        this.destination = destination;
        this.name = name;
        this.taxiName="Sin taxi";
//        this.creditCard = creditCard;
//        this.reliable = reliable;
    }

    /**
     * @return The name of the passenger.
     */

    //Metodo que devuelve el nombre de un pasajero
    public String getName()
    {
        return name;
    }

    /**
     * @return The destination location.
     */

    //Metodo que devuelve el destino al que va un pasajero
    public Location getDestination()
    {
        return destination;
    }

    /**
     * Return details of the passenger, such as where it is.
     * @return A string representation of the passenger.
     */

    //Metodo que te dice de donde a donde va el pasajero x
    public String toString()
    {
        return "Passenger "+getName()+" travelling from " +
        pickup + " to " + destination;
    }

    //Metodo que devuelve desde el punto de inicio de un pasajero
    //(donde se le recoge)
    public Location getRecogida(){
        return pickup;
    }

    //Metodo que modifica el nombre del taxi asignado al pasajero
    public void setNombreTaxi(String nameTaxi){
        this.taxiName = nameTaxi;
    }


    //Metodo que devuelve el nombre del taxi asignado al pasajero
    public String getNombreTaxi(){
        return taxiName;
    }
    
    public int getArrivalTime() {return arrivalTime;}
    
    public void setArrivalTime(int arrivalTim){this.arrivalTime = arrivalTim;}
    
    public int creditCard() {return creditCard;}
    
    public void setCreditCard(int creditCa){this.creditCard = creditCa;}
    
    public double getValorReliable(){return reliable.getValor();}
    
    public String getNombreReliable(){return reliable.getNombre();}
    
    public Reliable getReliable(){
        return reliable;
    }
    
    public void setReliable(Reliable reliable){this.reliable = reliable;}
    
    public String toStringReliable(){return getClass().getName() + getName()+">" + getReliable();}

    //Metodo que te da la informacion final de un pasajero
    //(nombre,destino y taxi usado)
    /**
     * Show the final information about the passenger, including the name of the taxi that used.
     */
    public String showFinalInfo()
    {
        return "Passenger: " + name + " in " + destination +
        " transported by taxi: " + taxiName;
    }
    
    //compara si dos pasajeros son el mismo(se llaman igual)
    public boolean equals(Passenger p){return this.name.equals(p.getName());}

    //metodo para comparar si dos pasajeros son el mismo
    public boolean equals(Object other){
        if(other instanceof Passenger otherPassenger) {
            return this.name.equals(otherPassenger.getName());
        }
        else return false;
    }

}
