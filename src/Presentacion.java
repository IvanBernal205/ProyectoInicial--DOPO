import slotMachine.SlotMachine;

/**
* Casos de prueba pra la presentacion.
*/
public class Presentacion
{
    public static void main(String[] args) {
        SlotMachine sm = new SlotMachine();
        sm.makeVisible();
        sm.addSymbol("normal", 1, "red");
        sm.addSymbol("ephemeral", 2, "blue");
        sm.addSymbol("shy", 3, "pink");
        sm.addSymbol("sgsdg", 4, "brown");

        sm.addWheel(1);
        sm.addWheel(2);
        sm.addWheel(3);
        sm.addWheel(4);
        sm.addWheel(5);

        sm.placeSymbol(1, "blue");
        sm.placeSymbol(2, "red");
        sm.placeSymbol(3, "pink");
        sm.placeSymbol(4, "red");
        sm.placeSymbol(5, "brown");

        for(int i = 0; i<5; i++){
            sm.spin();
        }

        sm.spin(1, 1);
        sm.spin(2, 2);
        sm.spin(3, 4);
        sm.spin(4, 2);
        sm.spin(5, -1);
        sm.spin();
        sm.spin();
        sm.spin();
        sm.isJackpot();
        sm.spin();
        sm.spin();
        sm.spin();
        sm.isJackpot();

    }
}