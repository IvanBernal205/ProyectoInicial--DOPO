package UnitTests;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import slotMachine.SlotMachineContest;

/**
 * The test class SlotMachineContestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SlotMachineContestTest
{
    /**
     * Default constructor for test class SlotMachineContestTest
     */
    public SlotMachineContestTest()
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

    /**
     * solve() debe devolver acciones que el juez acepte: la rueda tiene que estar
     * entre 1 y n, los pasos dentro del rango permitido y en total no puede pasar
     * de 10000 acciones.
     */
    @Test
    public void shouldReturnValidActionsWhenSolving()
    {
        int n = 10;
        SlotMachineContest contest = new SlotMachineContest();

        int[][] solution = contest.solve(n);

        assertNotNull(solution);
        assertTrue(solution.length > 0);
        assertTrue(solution.length <= 10000);

        for (int[] action : solution){
            assertEquals(2, action.length);
            assertTrue(action[0] >= 1 && action[0] <= n);
        }
    }

    /**
     * Con 50 ruedas solve() debe resolver en menos de 2 segundos, que es el limite
     * de tiempo del problema y el maximo de ruedas.
     */
    @Test
    public void shouldSolveFiftyWheelsInLessThanTwoSeconds()
    {
        int n = 50;
        SlotMachineContest contest = new SlotMachineContest();

        int[][] solution = assertTimeoutPreemptively(Duration.ofSeconds(2),
                                                     () -> contest.solve(n));

        assertNotNull(solution);
        assertTrue(solution.length <= 10000);
    }
}