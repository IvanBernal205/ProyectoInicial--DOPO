package slotMachine;

import shapes.Circle;
import shapes.Rectangle;

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
        machine.addSymbol(4, "pink"); // [green,blue,yellow,red]
        for(Symbol sy : machine.getSymbols()){
            System.out.println("color: "+sy.getColor());
            //[blue,green,red,pink]
        }
        machine.addWheel(0);
        machine.addWheel(0);
        machine.addWheel(0);
        machine.addWheel(0);

        machine.placeSymbol(1, "pink");
        machine.placeSymbol(2, "green");
        machine.placeSymbol(3, "blue");
        machine.placeSymbol(4, "red");
        
        for(Wheel wh : machine.getWheels()){
            System.out.println("colorWh: "+wh.getShownSymbol().getColor());
        }
        machine.delSymbol(2);

        for(Wheel wh : machine.getWheels()){
            System.out.println("colorWh2: "+wh.getShownSymbol().getColor());
        }
        machine.makeVisible();

        // machine.makeInvisible();

        
        if (false){
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 23; j++) {

                    if(i == 0 || j == 0 || i == 4 || j == 22 || j == 21 || (i==2 && j!=1 && j!=20)){
                        continue;
                    }

                    // if(i == 1 && j != 0 && j != 19){
                    //     continue;
                    // }
                    Rectangle rec = new Rectangle();
                    rec.changeColor("black");
                    rec.changeSize(60, 60);
                    rec.changePosition(j*60,i*60);
                    rec.makeVisible();
                }
            }
            

            double ruedas = 15;
            double largo = 18;
            double seccion = largo/ruedas;
            int sX = 2;
            int sY = 2;

            for (int i = 1; i < ruedas; i++) {
                Rectangle rec = new Rectangle();
                rec.changeColor("black");
                rec.changeSize(60, 3);
                double xFinal =  (sX+seccion*i)*60;
                int con = (int) xFinal-1;
                rec.changePosition(con,sY*60);
                rec.makeVisible();

                

                
            }

            for (int i = 0; i < ruedas; i++) {
                    Circle cir = new Circle();
                    cir.changeColor("e");
                    cir.changeSize(40);
                    double xFinal =  (sX+seccion*i)*60;
                    double sin =  xFinal+((seccion*60)-40)/2+1;
                    int xd = (int) sin;
                    cir.changePosition(xd,sY*65);
                    cir.makeVisible();
            }
        }
        

    }
}
