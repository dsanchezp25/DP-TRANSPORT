import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Provide a simple demonstration of running a stage-one
 * scenario. Several passengers and taxis are created. Pickups are
 * requested. As the simulation is run, the passengers
 * should be picked up and then taken to their destination.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023 DP Classes
 */
public class DemoAvanzada
{
    TransportCompany company;
    private final List<Taxi> actors;
    private Assigment asignacion;
    private Taxi taxi;

    /**
     * Constructor for objects of class DemoOnePassanger
     */
    public DemoAvanzada()
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
        Taxi taxi;
        int cont=0;
        for(int i=0; i< company.getPassengers().size();i++){
            pasajero=company.getPassengers().get(i);
            taxi=company.getVehicles().get(i);
            if(!taxi.isFree()){
                cont++;
                taxi=company.getVehicles().get(i+cont);//si el taxi no está libre coge el siguiente
            }
            if(!pasajero.getNombreTaxi().equals("Sin Taxi")){
                asignacion = new Assigment(company.getVehicles().get(i),//asigna un taxi a un pasajero sin taxi
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

        Taxi taxi1 = new Taxi(company, new Location(10, 13),"T2");
        Taxi taxi2 = new Taxi(company, new Location(0, 0),"T1");
        Taxi taxi3 = new Taxi(company, new Location(16, 18),"T3");
        Taxi taxi4 = new Taxi(company, new Location(11, 1),"T5");
        Taxi taxi5 = new Taxi(company, new Location(2, 10),"T6");
        Taxi taxi6 = new Taxi(company, new Location(7, 7),"T8");
        Taxi taxi7 = new Taxi(company, new Location(15, 9),"T7");
        Taxi taxi8 = new Taxi(company, new Location(8, 19),"T4");

        company.addVehicle(taxi1);
        company.addVehicle(taxi2);
        company.addVehicle(taxi3);
        company.addVehicle(taxi4);
        company.addVehicle(taxi5);
        company.addVehicle(taxi6);
        company.addVehicle(taxi7);
        company.addVehicle(taxi8);

        actors.addAll(company.getVehicles());
    }

    /**
     * Passengers are created and added to the company
     */
    private void createPassengers() {
       /* Passenger passenger1 = new Passenger(new Location(2, 2),
                new Location(10, 10),"Kevin");
        Passenger passenger2 = new Passenger(new Location(4, 16),
                new Location(19,0),"Margo");
        Passenger passenger3 = new Passenger(new Location(10, 10),
                new Location(2,2),"Edith");
        Passenger passenger4 = new Passenger(new Location(15, 3),
                new Location(7,1),"Stuart");
        Passenger passenger5 = new Passenger(new Location(11, 6),
                new Location(19,19),"Agnes");
        Passenger passenger6 = new Passenger(new Location(13, 17),
                new Location(0,0),"Bob");
        company.addPassenger(passenger1);
        company.addPassenger(passenger2);
        company.addPassenger(passenger3);
        company.addPassenger(passenger4);
        company.addPassenger(passenger5);
        company.addPassenger(passenger6);*/
    }

    /**
     * A pickup is requested for a single passenger.
     * @throws IllegalStateException If a pickup cannot be found
     */
    private void runSimulation() {
        List<Passenger> passengers = company.getPassengers();
        for(Passenger passenger : passengers) {
            if(!company.requestPickup(passenger)) {
                throw new IllegalStateException("Failed to find a pickup for: "+ passenger.getName());        
            }
        }

    }

    /**
     * Initial info is showed with the information about taxis and passengers
     */
    private void showInicialInfo() {
        //para ordenar una colección aplicando un comparador, esta sería 
        //la sintaxis (suponiendo que "passengers" es una colección donde
        //la compañía de taxis almacena los pasajero/as):
        //Collections.sort(passengers, new ComparadorNombrePassenger());
        System.out.println("--->> Simulation of the company: " + company.getName()+" <<---");
        System.out.println("-->> Taxis of the company <<--");
        //      TODO ordenar y mostrar los taxis
        Collections.sort(actors, new Comparator<Taxi>() {
                @Override
                public int compare(Taxi taxi1, Taxi taxi2){
                    return taxi1.getName().compareTo(taxi2.getName());
                }
            });
        int i = 0;
        while(i < actors.size()) {
            actors.get(i).showFinalInfo();
            i++;
        } //muestra la informacion inicial de taxis ordenados 

        System.out.println("-->> Passengers requesting taxi <<--");
        //      TODO ordenar y mostrar los pasajero/as
        //Collections.sort(actors, new ComparadorNombrePassenger());  
        for(i=0;i<company.getPassengers().size();i++){
            System.out.println(company.getPassengers().get(i).showFinalInfo());
        }

        System.out.println("-->> ---------------- <<--");
        //      mostrar los pasajeros que requieran taxi
        System.out.println("-->> Simulation start <<--");
        System.out.println("-->> ---------------- <<--");
        System.out.println("");        

    }

    /**
     * Final info is showed with the information about taxis and passengers
     */
    private void showFinalInfo() {
        int i;
        System.out.println("");
        System.out.println("-->> ----------------- <<--");
        System.out.println("-->> End of simulation <<--");        
        System.out.println("-->> ----------------- <<--");
        System.out.println("");

        System.out.println("-->> Taxis final information <<--");
        //TODO ordenar y mostrar los taxis
        for(i = 0; i < actors.size(); i++)
            actors.get(i).showFinalInfo();

        System.out.println("-->> Passengers final information <<--");
        //TODO ordenar y mostrar los pasajero/as
        int companySize = company.getPassengers().size();
        for(i = 0; i < companySize; i++)
            System.out.println(company.getPassengers().get(i).showFinalInfo());
    }

}

