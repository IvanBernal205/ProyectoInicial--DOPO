package slotMachine;

/**
 * Write a description of class prueba here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class prueba
{
    public static void main(){
        SlotMachine machine = new SlotMachine();
        machine.addSymbol(1, "blue"); // [blue]
        machine.addSymbol(2, "red");    // [blue,red]
        machine.addSymbol(2, "green");  // [green,blue,red]
        machine.addSymbol(4, "yellow"); // [green,blue,yellow,red]
        for(Symbol sy : machine.getSymbols()){
            System.out.println("color: "+sy.getColor());
            //[blue,green,red,yellow]
        }
        machine.addWheel(0);
        machine.addWheel(0);
        machine.addWheel(0);

        machine.placeSymbol(1, "yellow");
        machine.placeSymbol(2, "green");
        machine.placeSymbol(3, "blue");
        
        for(Wheel wh : machine.getWheels()){
            System.out.println("colorWh: "+wh.getShownSymbol().getColor());
        }
        machine.delSymbol(2);

        for(Wheel wh : machine.getWheels()){
            System.out.println("colorWh2: "+wh.getShownSymbol().getColor());
        }
    }
}
