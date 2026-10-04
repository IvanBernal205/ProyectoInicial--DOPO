package UnitTests;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slotMachine.SlotMachine;
import slotMachine.Wheel;
import slotMachine.Rebel;
import slotMachine.Lefty;
import java.util.ArrayList;

/**
 * The test class SlotMachineCC4Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SlotMachineCC4Test
{
    /**
     * Default constructor for test class SlotMachineCC4Test
     */
    public SlotMachineCC4Test()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }
    
    @Test
    public void shouldCreateANormalWheelWithDefectValues(){
        Wheel normal = new Wheel();
        
        assertTrue(normal.isSwapped());
        assertTrue(normal.isDeleted());
        
        assertFalse(normal.isLocked());
        normal.setLocked(true);
        
        assertTrue(normal.isLocked());
    }
    
    @Test
    public void shouldNotAllowARebelWheelToLock(){
        Rebel reb = new Rebel();
        
        assertFalse(reb.isLocked());
        reb.setLocked(true);
        
        assertFalse(reb.isLocked());
    }
    
    @Test
    public void shouldNotSwapARebelWheel(){
        SlotMachine sm = new SlotMachine();
        sm.addSymbol(1, "Red");
        sm.addSymbol(2, "Green");
        sm.addSymbol(3, "Blue");
        
        sm.addWheel("normal", 1);
        sm.addWheel("normal", 2);
        sm.addWheel("Rebel", 3);
        
        sm.placeSymbol(1, "Red");
        sm.placeSymbol(2, "Green");
        sm.placeSymbol(3, "Blue");
        
        ArrayList<Wheel> whs = sm.getWheels();
        
        Wheel norm = whs.get(1);
        Rebel reb = (Rebel) whs.get(2);
        
        sm.swap(1,3);
        
        assertEquals(reb, whs.get(2));
    }
    
    @Test
    public void shouldTakeTheCorrectWheelInAPositionBehindForALeftyWheel(){
        SlotMachine sm = new SlotMachine();
        sm.addSymbol(1, "Red");
        sm.addSymbol(2, "Green");
        sm.addSymbol(3, "Blue");
        
        sm.addWheel("normal", 1);
        sm.addWheel("normal", 2);
        sm.addWheel("Lefty", 3);
        
        sm.placeSymbol(1, "Red");
        sm.placeSymbol(2, "Green");
        sm.placeSymbol(3, "Blue");
        
        ArrayList<Wheel> whs = sm.getWheels();
        Wheel left = whs.get(1);
        
        Lefty tested = (Lefty) whs.get(2);
        
        assertEquals(left, tested.getLeftWh());
        
        assertEquals(left.getShownSymbol().getColor(), tested.getShownSymbol().getColor());
    }

    @Test
    public void shouldShrinkEphemeralWhenItLandsBySpin(){
        SlotMachine sm = new SlotMachine();
        sm.addSymbol("ephemeral", 1, "red");
        sm.addSymbol("normal", 2, "blue");
        sm.addWheel(1);
        sm.placeSymbol(1, "red");

        assertEquals(38, sm.getWheels().get(0).getShownSymbol().getSize());

        sm.spin(1); // red -> blue
        sm.spin(1); // blue -> red

        assertEquals("red", sm.configuration()[0]);
        assertEquals(30, sm.getWheels().get(0).getShownSymbol().getSize());
    }

    @Test
    public void shouldNotShrinkEphemeralBelowMinimum(){
        SlotMachine sm = new SlotMachine();
        sm.addSymbol("ephemeral", 1, "red");
        sm.addSymbol("normal", 2, "blue");
        sm.addWheel(1);
        sm.placeSymbol(1, "red");

        for (int i = 0; i < 30; i++){
            sm.spin(1);
            assertTrue(sm.getWheels().get(0).getShownSymbol().getSize() >= 4);
        }

        assertEquals("red", sm.configuration()[0]);
        assertEquals(4, sm.getWheels().get(0).getShownSymbol().getSize());
    }

    @Test
    public void shouldToggleShyEachTimeItLandsBySpin(){
        SlotMachine sm = new SlotMachine();
        sm.addSymbol("shy", 1, "green");
        sm.addSymbol("normal", 2, "blue");
        sm.addWheel(1);
        sm.placeSymbol(1, "green");

        assertTrue(sm.getWheels().get(0).getShownSymbol().isVisible());

        sm.spin(1); // green -> blue
        sm.spin(1); // blue -> green: oculto
        assertEquals("green", sm.configuration()[0]);
        assertFalse(sm.getWheels().get(0).getShownSymbol().isVisible());

        sm.spin(1);
        sm.spin(1); // vuelve a green: visible
        assertTrue(sm.getWheels().get(0).getShownSymbol().isVisible());
    }

    @Test
    public void shouldKeepHiddenShyInLogic(){
        SlotMachine sm = new SlotMachine();
        sm.addSymbol("shy", 1, "green");
        sm.addSymbol("normal", 2, "blue");
        sm.addWheel(1);
        sm.addWheel(2);
        sm.placeSymbol(1, "green");
        sm.placeSymbol(2, "green");

        sm.spin(1);
        sm.spin(1); // la primera rueda muestra green oculto

        assertFalse(sm.getWheels().get(0).getShownSymbol().isVisible());
        assertArrayEquals(new String[]{"green", "green"}, sm.configuration());
        assertEquals(1, sm.distinctSymbols());
        assertTrue(sm.isJackpot());
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}