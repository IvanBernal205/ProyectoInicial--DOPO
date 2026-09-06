package SlotMachineCC2Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import slotMachine.SlotMachine;

/**
 * The test class UnitTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class UnitTest
{
    /**
     * Default constructor for test class UnitTest
     */
    public UnitTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp(){
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

    @Test
    public void accordingBsShouldNotSpinLockedWheel()
    {
        SlotMachine sm = new SlotMachine();
        sm.addSymbol(1, "red");
        sm.addSymbol(1, "blue");
        sm.addSymbol(1, "green");
        sm.addSymbol(1, "magenta");

        sm.addWheel(1);
        sm.placeSymbol(1, "red");

        sm.addWheel(2);
        sm.placeSymbol(2, "red");

        sm.spin();
        assertArrayEquals(new String[]{"magenta", "magenta"}, sm.configuration());
        assertTrue(sm.isJackpot());

        sm.lock(2);
        sm.spin();

        assertTrue(sm.ok());
        assertArrayEquals(new String[]{"green", "magenta"}, sm.configuration());
        assertFalse(sm.isJackpot());
        assertEquals(2, sm.distinctSymbols());
    }


    @Test
    public void accordingBsShouldSpinWheelAfterUnlock()
    {
        SlotMachine sm = new SlotMachine();
        sm.addSymbol(1, "red");
        sm.addSymbol(1, "blue");
        sm.addSymbol(1, "green");
        sm.addSymbol(1, "magenta");

        sm.addWheel(1);
        sm.placeSymbol(1, "red");

        sm.addWheel(2);
        sm.placeSymbol(2, "red");

        sm.spin();
        sm.lock(2);
        sm.spin();
        assertArrayEquals(new String[]{"green", "magenta"}, sm.configuration());

        sm.unlock(2);
        sm.spin();

        assertTrue(sm.ok());
        assertArrayEquals(new String[]{"blue", "green"}, sm.configuration());
    }
    
    @Test
    public void accordingMgshouldSwapThePositionOfTwoDifferentWheels(){
        SlotMachine sm = new SlotMachine();
        sm.addSymbol(1, "red");
        sm.addSymbol(2, "blue");
        sm.addSymbol(3, "green");
        sm.addSymbol(4, "magenta");
        
        sm.addWheel(1);
        sm.placeSymbol(1, "red");

        sm.addWheel(2);
        sm.placeSymbol(2, "blue");
        
        sm.addWheel(3);
        sm.placeSymbol(3, "magenta");
        
        sm.swap(1,3);
        
        assertArrayEquals(new String []{"magenta","blue","red"}, sm.configuration());
    }
    
    @Test
    public void accordingMgshouldSpinAGivenAmountOfSteps(){
        SlotMachine sm = new SlotMachine();
        sm.addSymbol(1, "red");
        sm.addSymbol(2, "blue");
        sm.addSymbol(3, "green");
        sm.addSymbol(4, "magenta");
        
        sm.addWheel(1);
        sm.placeSymbol(1, "red");

        sm.addWheel(2);
        sm.placeSymbol(2, "blue");
        
        sm.addWheel(3);
        sm.placeSymbol(3, "magenta");
        
        assertTrue(sm.ok());
        sm.spin(2, 2);
        assertArrayEquals(new String []{"red", "magenta", "magenta"}, sm.configuration());
        assertFalse(sm.isJackpot());
        
        
        assertTrue(sm.ok());
        sm.spin(2,2);
        assertArrayEquals(new String []{"red", "blue", "magenta"}, sm.configuration());
        assertFalse(sm.isJackpot());
        
        assertTrue(sm.ok());
        sm.spin(1,4);
        assertArrayEquals(new String []{"red", "blue", "magenta"}, sm.configuration());
        assertFalse(sm.isJackpot());
        
        assertTrue(sm.ok());
        sm.spin(-15,1);
        assertArrayEquals(new String []{"blue", "blue", "magenta"}, sm.configuration());
        assertFalse(sm.isJackpot());
        
        assertTrue(sm.ok());
        sm.spin(3,2);
        assertArrayEquals(new String []{"blue", "blue", "blue"}, sm.configuration());
        assertTrue(sm.isJackpot());
        
    }
    
    @Test
    public void accordingMgshouldSetTheMachineInAGivenCombination(){
        SlotMachine sm = new SlotMachine();
        sm.addSymbol(1, "red");
        sm.addSymbol(2, "blue");
        sm.addSymbol(3, "green");
        sm.addSymbol(4, "magenta");
        
        sm.addWheel(1);
        sm.placeSymbol(1, "red");

        sm.addWheel(2);
        sm.placeSymbol(2, "blue");
        
        sm.addWheel(3);
        sm.placeSymbol(3, "magenta");
        
        
        String [] combination = {"green","green","green"}; 
        sm.spin(combination);
        
        assertTrue(sm.ok());
        assertArrayEquals(new String[]{"green","green","green"}, sm.configuration());
        assertTrue(sm.isJackpot());
        
        
        String[] combination2 = {"red","magenta","blue"};
        sm.spin(combination2);
        
        assertTrue(sm.ok());
        assertArrayEquals(new String[]{"red","magenta","blue"}, sm.configuration());
        assertFalse(sm.isJackpot());
        
        
        String[] combination3 = {"inexistentColor","magenta","blue"};
        sm.spin(combination3);
        
        assertTrue(sm.ok());
        assertArrayEquals(new String[]{"red","magenta","blue"}, sm.configuration());
        assertFalse(sm.isJackpot());
    }

}