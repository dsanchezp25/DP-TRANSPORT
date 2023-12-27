import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Model the operation of a taxi company, operating different
 * types of vehicle. This version operates a single taxi.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class TransportCompany  
{
    private String name;  //nombre de la compañía
    private ArrayList <Taxi> vehiculo;
    private ArrayList <Passenger> pasajeros;
    private ArrayList <Assigment> asignaciones; //Es una estructura que tiene dentro un tipo taxi y un tipo passenger, y hay que crear una clase para poder hacerloa

    /**
     * Constructor for objects of class TransportCompany
     */
    public TransportCompany(String name)
    {
        this.name = name;
        this.vehiculo = new ArrayList <Taxi> ();
        this.pasajeros = new ArrayList <Passenger> ();
        this.asignaciones = new ArrayList <Assigment> ();
    }

    /**
     * Constructor for objects of class TransportCompany
     */
    public TransportCompany(String name, ArrayList <Taxi> vehiculo,  ArrayList <Passenger> pasajeros, ArrayList <Assigment> asignaciones)
    {
        this.name = name;
        this.vehiculo = vehiculo;
        this.pasajeros = pasajeros;
        this.asignaciones = asignaciones;
    }

    /**
     * @return The name of the company.
     */
    public String getName()
    {
        return name;
    }

    /**
     * A vehicle has arrived at a passenger's destination.
     * @param vehicle The vehicle at the destination.
     * @param passenger The passenger being dropped off.
     */
    public void arrivedAtDestination(Taxi vehicle,
    Passenger passenger)
    {
        System.out.println(vehicle + " offloads " + passenger);
    }

    /**
     * @return The list of vehicles.
     */
    public List<Taxi> getVehicles()
    {       
        return this.vehiculo;
    }

    /**
     * @return The list of passengers.
     */
    public List<Passenger> getPassengers()
    {
        return this.pasajeros;
    }

    /**
     * @return The list of assigment.
     */
    public List<Assigment> getAsignacion()
    {
        return this.asignaciones;
    }

    /**
     * @param vehicle Adds the new Vehicle.
     */
    public void addVehicle(Taxi vehicle)
    {
        this.vehiculo.add(vehicle);
    }

    /**
     * Add a new passenger in the company.
     * @param passenger The new passenger.
     */
    public void addPassenger(Passenger passenger)
    {
        this.pasajeros.add(passenger);
    }

    /**
     * Add a new passenger in the company.
     * @param asignacion The new passenger.
     */
    public void addAsignacion(Assigment asignacion)
    {
        this.asignaciones.add(asignacion);
    }

    /**
     * Find a passenger the most closed free vehicle to a location, if any.
     * @param location location to go
     * @return A free vehicle, or null if there is none.
     */
    private Taxi scheduleVehicle(Location location)
    {
        Collections.sort(vehiculo, new ComparadorDistancia(location));
        boolean exit = false;  
        int i = 0;
        while(i < vehiculo.size()){
            if(vehiculo.get(i).isFree())
                return vehiculo.get(i);
            else
                i++;   
        }
            return null;
    }

    /**
     * Request a pickup for the given passenger.
     * @param passenger The passenger requesting a pickup.
     * @return Whether a free vehicle is available.
     */
    public boolean requestPickup(Passenger passenger)
    {
        Taxi t;
        t = scheduleVehicle(passenger.getRecogida());
        if(t != null){
            t.setTargetLocation(passenger.getDestination());
            asignaciones.add(new Assigment (t, passenger));
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * A vehicle has arrived at a pickup point.
     * @param taxi The vehicle at the pickup point.
     */
    public void arrivedAtPickup(Taxi taxi)
    {
        Iterator <Assigment> iterator = asignaciones.iterator();
        //TODO
        boolean exit = false;
        Assigment a = null;
        Passenger p = null;
        while(iterator.hasNext() && !exit){
            a = iterator.next();
            if(a.getAssigTaxi().equals(taxi)){
                exit = true;
                p = a.getAssigPassenger(); 
                iterator.remove();
            }
        }
        if(!exit){
            System.out.println("No se ha encontrado el taxi"); 
        }
        else{
            taxi.pickup(p);
            p.setNombreTaxi(taxi.getName());
            System.out.println("<<<< "+taxi+" picks up "+taxi.getPassenger().getName());
        }

    }
}
