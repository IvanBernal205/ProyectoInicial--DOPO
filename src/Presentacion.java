import slotMachine.SlotMachine;

/**
 * Casos de prueba pra la presentacion.
 */
public class Presentacion
{
    public static void main(String[] args) {
        
        // Presentación 1 de los metodos lock() y unlock().
        SlotMachine sm = new SlotMachine();
        //sm.makeVisible();

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
        //sm.makeInvisible();






        
        // Presentación 2 de los metodos swap(), spin(wheel:int, steps:int) y spin(setSymbols:String[])
        SlotMachine stm = new SlotMachine();
        stm.makeVisible();

        stm.addSymbol(1, "red");
        stm.addSymbol(2, "green");
        stm.addSymbol(3, "blue");
        stm.addSymbol(4, "magenta");
        stm.addSymbol(5, "gold");
        stm.addSymbol(6, "orange");
        stm.addSymbol(7, "pink");
        stm.addSymbol(8, "brown");
        // [red, green, blue, magenta, gold, orange, pink]
        
        
        stm.addWheel(1);
        stm.placeSymbol(1, "gold");

        stm.addWheel(2);
        stm.placeSymbol(2, "blue");
        
        stm.addWheel(3);
        stm.placeSymbol(3, "red"); 
        
        stm.addWheel(4);
        stm.placeSymbol(4, "brown");
        
        stm.addWheel(5);
        stm.placeSymbol(5, "green");
         
        //[gold, blue, red, brown, green]
        
        //swap()
        
        stm.swap(2,5);
        stm.swap(5,4);
        stm.swap(1,3);
        
        //[red, green, gold, blue, brown]
        
        
        //spin(wheel:int, steps:int)
        stm.isJackpot();
        stm.spin(1,4);
        //[gold, green, gold, blue, brown]
        stm.spin(2,3);
        //[gold, gold, gold, blue, brown]
        stm.spin(4,2);
        //[gold, gold, gold, gold, brown]
        stm.spin(500,5);
        //[gold, gold, gold, gold, gold]
        stm.isJackpot();
        
        //spin(setSymbols:String[])
        String [] combinationGiven = {"inexistentColor","brown","red","blue","green"};
        stm.spin(combinationGiven);
        //[gold, brown, red, blue, green]
        stm.isJackpot();
        
        String [] combinationGiven2 = {"red","red","red","red","red"};
        stm.spin(combinationGiven2);
        //[red, red, red, red, red]
        stm.isJackpot();




        stm.makeInvisible();
        stm.exit();
    }
}