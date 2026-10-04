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
        
        assertEquals(left.getShownSymbol(), tested.getShownSymbol());
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