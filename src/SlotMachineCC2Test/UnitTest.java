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
    public void setUp()
    {
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
}