package slotMachine;

import java.util.ArrayList;

import shapes.Circle;
import shapes.Rectangle;

public class PaintSlotMachine {

    private static final int TILE = 60;
    private static final int HEIGHT_CANVAS = 5; //  5*60 = 300
    private static final int WIDTH_CANVAS = 23; //  23*60 = 1380
    private boolean visible = false;
    private ArrayList<Wheel> wheels;

    private ArrayList<Rectangle> machineRecs = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> bars = new ArrayList<>();
    private ArrayList<Circle> circ = new ArrayList<Circle>();   

    public PaintSlotMachine(ArrayList<Wheel> wheels){
        this.wheels = wheels;
    }
    

    public void build (){
        // paintMachine();
        // paintWheels();
        // paintSymbols();
    }

    public void makeVisible(){
        visible = true;
        paintMachine();
        reDraw();
    }

    public void makeInvisible(){
        visible = false;
        reDraw();
    }

    public void reDraw(){
        for (Rectangle r : bars) r.makeInvisible();
        for (Circle c : circ) c.makeInvisible();
        bars.clear();
        circ.clear();

        paintWheels();
        paintSymbols();

        if (visible){
            for (Rectangle re : machineRecs) re.makeVisible();
            for (Rectangle r : bars) r.makeVisible();
            for (Circle c : circ) c.makeVisible();
        }
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
                machineRecs.add(rec);
            }
        }
        paintLever();
}

    private void paintWheels(){
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
            bars.add(rec);
        }
    }

    private void paintSymbols(){
        double numWh = wheels.size();
        double length = WIDTH_CANVAS - 5;
        double lenSection = length/numWh;
        

        for (int i = 0; i < numWh; i++) {
            Symbol symb = wheels.get(i).getShownSymbol();
            if (symb == null || symb.getShape() == null) continue;
            Circle cir = symb.getShape();
            cir.changeColor(symb.getColor());
            cir.changeSize(40);


            double x =  (2 + lenSection*i)*TILE + ((lenSection*TILE) - 40)/2;
            int xFinal = (int) x;





            cir.changePosition(xFinal, 2*TILE + 10);
            circ.add(cir);
        }
    }

    private void paintLever(){
        Rectangle rec = new Rectangle();
        rec.changeColor("black");
        rec.changeSize(10, 60);
        rec.changePosition(21*TILE,3*TILE);
        machineRecs.add(rec);

        Rectangle rec1 = new Rectangle();
        rec1.changeColor("black");
        rec1.changeSize(70, 10);
        rec1.changePosition(22*TILE,2*TILE);
        machineRecs.add(rec1);

        Circle cir = new Circle();
        cir.changeColor("red");
        cir.changeSize(40);
        cir.changePosition(21*TILE+45,1*TILE+20);
        //circ.add(cir);
        cir.makeVisible();
    }
}
