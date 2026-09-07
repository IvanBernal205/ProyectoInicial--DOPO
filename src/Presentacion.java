
import slotMachine.SlotMachine;

/**
 * Casos de prueba pra la presentacion.
 */
public class Presentacion
{
    public static void main(String[] args) {

        // Presentación 1 de los metodos lock() y unlock().
        SlotMachine sm = new SlotMachine();
        sm.makeVisible();

        sm.addSymbol(1, "red");
        sm.addSymbol(2, "green");
        sm.addSymbol(3, "blue");
        sm.addSymbol(4, "magenta");
        sm.addSymbol(5, "gold");
        sm.addSymbol(6, "orange");
        sm.addSymbol(7, "pink");
        sm.addSymbol(8, "brown");
        // [red, green, blue, magenta, gold, orange, pink]

        sm.addWheel(1);
        sm.placeSymbol(1, "blue");

        sm.addWheel(2);
        sm.placeSymbol(2, "gold");

        sm.addWheel(1);
        sm.placeSymbol(1, "red");

        sm.addWheel(4);
        sm.placeSymbol(4, "orange");
        // [red, blue, gold, orange]

        sm.lock(4);
        sm.spin();
        // [green, magenta, orange, orange]

        sm.spin(4);

        sm.unlock(4);
        sm.spin();
        // [blue, gold, pink, pink]

        sm.lock(3);
        sm.lock(4);
        sm.spin();
        // [magenta, orange, pink, pink]
        sm.spin();
        // [gold, pink, pink, pink]
        sm.lock(2);
        sm.spin();
        sm.spin();
        // [pink, pink, pink, pink]
        sm.isJackpot();
        sm.makeInvisible();







        // Presentación 2 de los metodos swap(), spin(wheel:int, steps:int) y spint(setSymbols:String[])
        SlotMachine slotM = new SlotMachine();
        slotM.makeVisible();






        slotM.makeInvisible();
        slotM.exit();
    }
}