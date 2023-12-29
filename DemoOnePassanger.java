import java.util.ArrayList;
import java.util.List;

/**
 * Provide a simple demonstration of running a stage-one
 * scenario. A single passenger and taxi are created, and a pickup
 * requested. As the simulation is run, the passenger
 * should be picked up and then taken to their destination.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023 DP Classes
 */
public class DemoOnePassanger
{
    TransportCompany company;
    private List<Taxi> actors;
    private Assigment asignacion;

    /**
     * Constructor for objects of class DemoOnePassanger
     */
    public DemoOnePassanger()
    {
        company = new TransportCompany("Compañía Taxis Cáceres");
        actors = new ArrayList<>();
        reset();
    }

    /**
     * Run the demo for a fixed number of steps.
     */
    public void run()
    {
        //Ejecutamos un número de pasos la simulación.
        //En cada paso, cada taxi realiza su acción
        Passenger pasajero;
        for(int i=0; i< company.getPassengers().size();i++){
            pasajero=company.getPassengers().get(i);
            if(!pasajero.getNombreTaxi().equals("Sin Taxi")){
                asignacion = new Assigment(company.getVehicles().get(i),
                    company.getPassengers().get(i));
                company.addAsignacion(asignacion);
            }
        }

        for(int step = 0; step < 100; step++) {
            step();
        }
        showFinalInfo();
    }

    /**
     * Run the demo for one step by requesting
     * all actors to act.
     */
    public void step()
    {
        for(Taxi actor : actors) {
            actor.act();
        }
    }

    /**
     * Reset the demo to a starting point.
     * A single taxi and passenger are created, and a pickup is
     * requested for a single passenger.
     * @throws IllegalStateException If a pickup cannot be found
     */
    public void reset()
    {
        actors.clear();
        createTaxis();
        createPassengers();
        showInicialInfo();
        runSimulation();
    }

    /**
     * Taxis are created and added to the company
     */
    private void createTaxis() {
        Taxi taxi = new Taxi(company, new Location(10, 10),"T1");
        company.addVehicle(taxi);
        actors.addAll(company.getVehicles());
    }

    /**
     * Passengers are created and added to the company
     */
    private void createPassengers() {
        Passenger passenger = new Passenger(new Location(6, 6), new Location(5,2),"Lucy");
        company.addPassenger(passenger);
    }

    /**
     * A pickup is requested for a single passenger.
     * @throws IllegalStateException If a pickup cannot be found
     */
    private void runSimulation() {
        List<Passenger> passengers = company.getPassengers();
        for(Passenger passenger : passengers) {
            if(!company.requestPickup(passenger)) {
                throw new IllegalStateException("Failed to find a pickup.");
            }
        }
        Passenger p = company.getPassengers().get(0);
        Taxi t = company.getVehicles().get(0);
         System.out.println("<<<< Taxi " + t.getName() +  " at "
         + t.getLocation() + " go to pick up passenger " + p.getName()
         + " at location " + p.getRecogida());
    }

    /**
     * Initial info is showed with the information about taxis and passengers
     */
    private void showInicialInfo() {

        System.out.println("--->> Simulation of the company: " + company.getName()+" <<---");
        System.out.println("-->> Taxis of the company <<--");

        //Como es un unico taxi, podemos guardar la informacion en una variable tipo taxi
        Taxi t1 = actors.get(0);

        //TODO ordenar y mostrar los taxis
        System.out.println("Taxis " + t1.getName() + " at " +
            t1.getLocation().toString());

        System.out.println("-->> Passengers requesting taxi <<--");
/*
        TODO ordenar y mostrar los pasajero/as
        *para ordenar una colección aplicando un comparador, esta sería
        *la sintaxis (suponiendo que "passengers" es una colección donde
        *la compañía de taxis almacena los pasajero/as):
        *Collections.sort(passenger, new ComparadorNombrePassenger());
        *Como es un unico passenger, podemos guardar la informacion en una variable tipo passenger

*/
        Passenger passenger = company.getPassengers().get(0);
        System.out.println("Passenger " + passenger.getName() +
            " travelling from " + passenger.getRecogida().toString() +
            " to " + passenger.getDestination().toString());

        System.out.println("");
        System.out.println("-->> ---------------- <<--");
        System.out.println("-->> Simulation start <<--");
        System.out.println("-->> ---------------- <<--");
        System.out.println("");
    }

    /**
     * Final info is showed with the information about taxis and passengers
     */
    private void showFinalInfo() {

        System.out.println("");
        System.out.println("-->> ----------------- <<--");
        System.out.println("-->> End of simulation <<--");
        System.out.println("-->> ----------------- <<--");
        System.out.println("");

        System.out.println("-->> Taxis final information <<--");
        //TODO ordenar y mostrar los taxis
        Taxi taxi = actors.get(0);
        System.out.println("Taxi " + taxi.getName() + " at " +
            taxi.getLocation() + " - passenger transported: " +
            taxi.getNumPassengers() + " - non active for : " + taxi.getIdleCount() + " times");

        System.out.println("-->> Passengers final information <<--");
        //TODO ordenar y mostrar los pasajero/as
        Passenger p1 = company.getPassengers().get(0);
        System.out.println("Passenger" + p1.getName() + " in " + p1.getDestination().toString()
            + " transported by: " + taxi.getName());

    }
}
