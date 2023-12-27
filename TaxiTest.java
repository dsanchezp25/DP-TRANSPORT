import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The test class TaxiTest.
 *
 * @author  David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023.10.10 DP classes 
 */
public class TaxiTest
{
    private Taxi taxi,taxi2;
    private Passenger passenger;
    private Assigment asignacion;
    TransportCompany company;
    Location taxiLocation;
    Location pickup;
    Location destination;

    //TODO
    //crear más campos (si es necesario) 
    /**
     * Default constructor for test class TaxiTest
     */
    public TaxiTest()
    {

    }

    /**
     * Create a taxi.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        company = new TransportCompany("Compañía Taxis Cáceres");
        // Starting position for the taxi.
        taxiLocation = new Location(7, 7);
        // Locations for the passenger.
        pickup = new Location(1, 2);
        destination = new Location(5, 6);

        //passenger = new Passenger(pickup, destination,"Kevin",Reliable.HIGH);
        taxi = new Taxi(company, taxiLocation,"T1");
        taxi2 = new Taxi(company, taxiLocation,"T2");

        asignacion = new Assigment(taxi2,passenger);
        //Completar (si es necesario) este método
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * Test creation and the initial state of a taxi.
     */
    @Test
    public void testCreation()
    {
        assertEquals(taxi.getName(),"T1");
        assertEquals(taxi.getTaxiCompany(),company);
        assertEquals(taxi.getLocation(),taxiLocation);
        assertEquals(true, taxi.isFree());
    }

    /**
     * Test that a taxi is no longer free after it has
     * picked up a passenger.
     */
    @Test
    public void testPickup()
    {
        //TODO implementar este método
        taxi.pickup(passenger);
        assertEquals(destination,taxi.getTargetLocation());
        assertEquals(1,taxi.getNumPassengers());
        assertEquals(taxi.getPassenger(),passenger);
        assertEquals(false,taxi.isFree());
    }

    /**
     * Test that a taxi becomes free again after offloading
     * a passenger.
     */
    @Test
    public void testOffload()
    {
        taxi.pickup(passenger);
        //vemos que el taxi no está libre
        assertFalse(taxi.isFree());
        //dejamos al pasajero
        taxi.offloadPassenger();
        //verificamos que el taxi está libre
        assertTrue(taxi.isFree());
    }

    /**
     * Test that a taxi picks up and delivers a passenger within
     * a reasonable number of steps.
     */
    @Test
    public void testDelivery()
    {
        //el taxi recogera la pasajero
        //TODO
        //taxi.setTargetLocation(passenger.getRecogida());
        company.addVehicle(taxi2);
        company.addVehicle(taxi);
        // asignacion.setAssigPassenger(passenger);
        // asignacion.setAssigTaxi(taxi);
        asignacion = new Assigment(taxi,passenger);
        company.addAsignacion(asignacion);
        int cont = 0;
        while(taxi.isFree()){
            taxi.act();
            cont++;
        }
        while(!taxi.isFree()){
            taxi.act();
            cont++;
        }
        assertTrue(taxi.isFree());
        //el limite de pasos razonables sera 15
        assertTrue(cont <= 15);

    }
}

