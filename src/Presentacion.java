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

        sm.addSymbol("comodin", 5, "green");
        sm.spin(4, 2);
        sm.isJackpot();
        sm.spin(4,1);
        sm.isJackpot();

        
    }
    
    public static void main2(String[] args){
        SlotMachine sm = new SlotMachine();
        sm.makeVisible();
        
        sm.addSymbol(1, "yellow");
        sm.addSymbol(2, "blue");
        sm.addSymbol(3, "red");
        sm.addSymbol(4, "green");
        sm.addSymbol(5, "orange");
        
        sm.addWheel("normal", 1);
        sm.addWheel("lefty", 2);
        sm.addWheel("rebel", 3);
        sm.addWheel("normal", 4);
        sm.addWheel("normal", 5);
        
        sm.placeSymbol(1, "yellow");
        sm.placeSymbol(2, "blue");
        sm.placeSymbol(3, "red");
        sm.placeSymbol(4, "green");
        sm.placeSymbol(5, "orange");
        
        sm.spin(1);
        sm.spin(1);
        sm.spin(1);
        
        sm.spin(2); //lefty va a copiar el estado de la wheel de su izquierda
        
        sm.lock(3);//rebel no se permite bloquear, por lo que se permite girar siempre
        sm.spin(3);
        sm.delWheel(3);//rebel no se deja eliminar
        
        sm.swap(3,5);  //rebel no lo permite 
        
        sm.spin(5, -1);
        sm.spin();
        
        sm.isJackpot();
    }
}