package slotMachine;

import java.util.ArrayList;

import shapes.Circle;
import shapes.Rectangle;

public class PaintSlotMachine {

    private static final int TILE = 60;
    private static final int HEIGHT_CANVAS = 5; //  5*60 = 300
    private static final int WIDTH_CANVAS = 23; //  23*60 = 1380



    private ArrayList<ArrayList<Integer>> grid = new ArrayList<ArrayList<Integer>>();
    
    public void paintAll(ArrayList<Wheel> wheels){
        paintMachine();
        paintWheels(wheels);
        paintSymbols(wheels);
    }

    private void paintMachine(){
        for (int i = 0; i < HEIGHT_CANVAS; i++) {
            for (int j = 0; j < WIDTH_CANVAS; j++) {

                if(i == 0 || j == 0 || i == HEIGHT_CANVAS-1 || j == WIDTH_CANVAS-1 || j == WIDTH_CANVAS-2 || (i==2 && j!=1 && j!=WIDTH_CANVAS-3)){
                    continue;
                }
                Rectangle rec = new Rectangle();
                rec.changeColor("black");
                rec.changeSize(TILE, TILE);
                rec.changePosition(j*TILE,i*TILE);
                rec.makeVisible();
            }
        }
    }

    private void paintWheels(ArrayList<Wheel> wheels){
        double numWh = wheels.size();
        double length = WIDTH_CANVAS - 5;
        double lenSection = length/numWh;

        for (int i = 1; i < numWh; i++) {
            Rectangle rec = new Rectangle();
            rec.changeColor("black");
            rec.changeSize(TILE, 2);


            double x = (2 + lenSection*i)*60;
            int xFinal = (int) x-1;

            rec.changePosition(xFinal,2*TILE);
            rec.makeVisible();
        }
    }

    private void paintSymbols(ArrayList<Wheel> wheels){
        double numWh = wheels.size();
        double length = WIDTH_CANVAS - 5;
        double lenSection = length/numWh;
        

        for (int i = 0; i < numWh; i++) {
            Symbol symb = wheels.get(i).getShownSymbol();
            Circle cir = symb.getShape();
            cir.changeColor(symb.getColor());
            cir.changeSize(40);


            double x =  (2 + lenSection*i)*TILE + ((lenSection*TILE) - 40)/2;
            int xFinal = (int) x;





            cir.changePosition(xFinal, 2*TILE + 10);
            cir.makeVisible();
        }
    }
}
