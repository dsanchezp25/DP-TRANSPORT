import java.util.List;

/**
 * Model the common elements of taxis and shuttles.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023.10.10 DP classes 
 */
public class Taxi {
    // The Taxi Company of this Taxi.
    private final TransportCompany company;
    // Where the vehicle is.
    private Location location;     
    // Where the vehicle is headed.
    private  Location targetLocation;
    // Record how often the vehicle has nothing to do.
    private int idleCount;       
    //name of the taxi
    private final String name;
    // The Passenger of this Taxi.
    private Passenger passenger;
    // Counter of passengers of this Taxi.
    private int numPassenger;
    // Taxi is free or not
    private boolean freeTaxi;

    /**
     * Constructor of class Vehicle
     * @param company The taxi company. Must not be null.
     * @param location The vehicle's starting point. Must not be null.
     * @throws NullPointerException If company or location is null.
     */
    public Taxi(TransportCompany company, Location location, String name){
        if(company == null) {
            throw new NullPointerException("company");
        }
        if(location == null) {
            throw new NullPointerException("location");
        }
        this.company = company;
        this.location = location;
        targetLocation = null;
        idleCount = 0;
        this.name = name;
        passenger = null;
        freeTaxi = true;
        numPassenger = 0;

    }

    /**
     * @return the name of the taxi
     */
    public String getName(){
        return name;
    }

    //devuelve el pasajero del taxi actual
    public Passenger getPassenger(){
        return passenger;
    }

    /**
     * Get the location.
     * @return Where this taxi is currently located.
     */
    public Location getLocation(){
        return location;
    }

    /**
     * Get the company
     * @return taxis company.
     */
    public TransportCompany getTaxiCompany(){
        return company;
    }

    /**
     * Set the current location.
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setLocation(Location location){
        if(location != null) {
            this.location = location;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Get the target location.
     * @return Where this vehicle is currently headed, or null
     *         if it is idle.
     */
    public Location getTargetLocation(){
        return targetLocation;
    }

    /**
     * Set the required target location.
     * @param location Where to go. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setTargetLocation(Location location){
        if(location != null) {
            targetLocation = location;
        }
        else {
            throw new NullPointerException();
        }
    }

    /**
     * Receive a pickup location. This becomes the
     * target location.
     * @param location The pickup location.
     */
    public void setPickupLocation(Location location){
        setTargetLocation(location);
    }

    /**
     * Has the vehicle a target Location?
     * @return Whether or not this vehicle has a target Location.
     */
    public boolean hasTargetLocation(){
        return getTargetLocation() != null;
    }

    /**
     * Clear the target location.
     */
    public void clearTargetLocation(){
        targetLocation = null;
    }

    /**
     * @return on how many steps this vehicle has been idle.
     */
    public int getIdleCount(){
        return idleCount;
    }

    /**
     * @return the number of pasengers the taxi have arrived.
     */
    public int getNumPassengers(){
        return numPassenger;
    }

    /**
     * @return set free on a taxi .
     */
    public void setFreeTaxi(){
        freeTaxi = true;
    }

    /**
     * @return not free on a taxi .
     */
    public void setNotFreeTaxi(){
        freeTaxi = false;
    }

    /**
     * Increment the number of steps on which this vehicle
     * has been idle.
     */
    public void incrementIdleCount(){
        idleCount++;
    }

    /**
     * Return details of the taxi, such as where it is.
     * @return A string representation of the taxi.
     */
    public String toString(){
        return getClass().getName() + " " +getName()+" at " + getLocation();
    }

    /**
     * Is the taxi free?
     * @return Whether or not this taxi is free.
     */
    public boolean isFree(){
        return freeTaxi;
    }

    /**
     * Notify the company of our arrival at a pickup location.
     */
    public void notifyPickupArrival(){company.arrivedAtPickup(this);
    //el this se refiere a que ha llegado yo mismo refiriendose al taxi
    }

    /**
     * Notify the company of our arrival at a passenger's destination.
     */
    public void notifyPassengerArrival(Passenger passenger){
        Taxi taxi = new Taxi(company, location, name);
        company.arrivedAtDestination(taxi, passenger);
    }

    /**
     * Receive a passenger.
     * Set passenger's destination as its target location.
     * @param passenger The passenger.
     */
    public void pickup(Passenger passenger){
        /* hacer esto en trasnport company mejor*/      //passenger.setNombreTaxi(name);   
        targetLocation = passenger.getDestination();
        incrementPassengersTransported();
        this.passenger = passenger;
        setNotFreeTaxi();
    }

    /**
     * Offload the passenger.
     */
    public void offloadPassenger(){
        //passenger.setNombreTaxi(" ");
        targetLocation = null;
        passenger = null;
        setFreeTaxi();
    }

    /**
     * @return how many passengers this vehicle has transported.
     */
    public int passengersTransported(){
        return numPassenger;
    }

    /**
     * Increment the number of passengers this vehicle has transported.
     */
    protected void incrementPassengersTransported(){
        numPassenger++;
    }

    /**
     * Get the distance to the target location from the current location.
     * @return distance to target location.
     */
    public int distanceToTheTargetLocation(){               
        return location.distance(targetLocation);
    }
    
    public int distToLoc(Location loc){               
        return location.distance(loc);
    }

    /**
     * Carry out a taxi's actions.
     */
    public void act(){
        //TODO  implementar este método 
        if(!hasTargetLocation()){ // no tiene pasajero, no tiene destino
            incrementIdleCount(); //incrementa el tiempo parado
        }else{
            location = location.nextLocation(getTargetLocation());
            if(location.equals(getTargetLocation())){ //si es posicion para recoger a pasajero
                setLocation(location); //guardamos la localizacion del taxi despues de hacer un movimiento
                if(getPassenger() == null){
                    notifyPickupArrival(); //notifica a la compania que es 
                }
                else{ //Ya que si tiene pasajero el destino al que ha llegado el taxi es el destino del pasajero
                    notifyPassengerArrival(getPassenger());
                    offloadPassenger();
//                    incrementPassengersTransported();
                }
            }else{
                 System.out.println("@@@  Taxi: " + getName() + " moving to: " + location);
            }
        }
    }

    /**
     * Return details of the taxi, such as where it is.
     */
    public void showFinalInfo(){
        //TODO  implementar este método
        System.out.println("Taxi: " + getName() + " at " +
            getLocation() + " - " + "passengers transported: "
            + getNumPassengers() + " - " + " non active for: "
            + getIdleCount() + " times");
    }

    //Compara si un taxi es igual a otro
    public boolean equals(Object other){
        if(other instanceof Taxi) {
            Taxi otherTaxi = (Taxi) other;
            return name.equals(otherTaxi.getName());
        }
        else return false;
    }


    
}


