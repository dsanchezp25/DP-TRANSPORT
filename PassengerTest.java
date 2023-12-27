import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The test class PassengerTest.
 *
 * @author  David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023.10.10 DP classes 
 */
public class PassengerTest
{
    Passenger pasajero;
    /**
     * Default constructor for test class PassengerTest
     */
    public PassengerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        //pasajero = new Passenger(new Location(0,0),new Location(4,3),"Paco");
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
     * Test basic creation of a passenger.
     * Ensure that the pickup and destination locations
     * have been set.
     */
    @Test
    public void testCreation()
    {
        //TODO implementar este método
        // Testear la creación correcta de objetos de tipo Passenger comprobando 
        // que la inicialización de campos como dirección de recogida y destino es correcta.
        assertEquals("Paco",pasajero.getName());
        assertEquals(new Location(4,3),pasajero.getDestination());
    }

    /**
     * Test of the getTaxiName method.
     * Ensure that this method gets and returns the name of the taxi correctly.
     */
    @Test
    public void testGetTaxiName()
    {
        //TODO implementar este método
        // Testear el método que devuelve el nombre del taxi que ha transportado
        //al pasajero/a
        assertEquals("Sin taxi",pasajero.getNombreTaxi());
        pasajero.setNombreTaxi("Taxi Manolo");
        assertEquals("Taxi Manolo",pasajero.getNombreTaxi());
    }

    /**
     * Test of the getPickupLocation method.
     * Ensure that this method gets and returns the pickup location correctly.
     */         
    @Test
    public void testGetRecogida()
    {
        //TODO implementar este método
        // Testear el método que devuelve la dirección de recogida del objeto.
        assertEquals(new Location(0,0),pasajero.getRecogida());
    }
}
