package slotMachine;
import java.util.ArrayList;
import shapes.Circle;
import shapes.Figure;
import shapes.Rectangle;

/**
 * A slot Machine which will be the main element.
 * 
 * @author Iván Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica
 * @version 23/08/2026
 */
public class PaintSlotMachine {

    private static final int TILE = 60;
    private static final int HEIGHT_CANVAS = 5; //  5*60 = 300
    private static final int WIDTH_CANVAS = 23; //  23*60 = 1380
    private boolean visible = false;
    private boolean winning = false;
    private ArrayList<Wheel> wheels;

    private ArrayList<Figure> machineFig = new ArrayList<>();
    private ArrayList<Figure> wheelsFig = new ArrayList<>();
    private ArrayList<Figure> symbolsFig = new ArrayList<>();
    private ArrayList<Figure> leverFig = new ArrayList<>();

    /**
     * Create a new PaintSlotMachine with the given wheels.
     * @param wheels An array with the wheels already created
     */
    public PaintSlotMachine(ArrayList<Wheel> wheels){
        this.wheels = wheels;
        winning = false;
    }

    /**
     * Make visible every instance used to create the slot machine and
     * the recent symbols on screen.
     */
    public void makeVisible(){
        if(visible) return; // Si ya era visible no se pinta de nuevo
        visible = true;
        paintBody("black");
        paintLever();
        paintWheels();
        paintSymbols();
    }

    /**
     * Make invisible every instance used to create the slot machine and
     * the recent symbols on screen.
     */
    public void makeInvisible(){
        visible = false;
        eraseMachine();
    }

    /**
     * Draw the elements of the slot machine
     */
    public void reDraw(){
        for(Figure f : wheelsFig) f.makeInvisible();
        for(Figure f : symbolsFig) f.makeInvisible();
        symbolsFig.clear();
        wheelsFig.clear(); 

        paintWheels();
        paintSymbols();

        for(Figure f : wheelsFig) f.makeVisible();
        for(Figure f : symbolsFig) {
            wait(200);
            f.makeVisible();
        }
    }
    
    /**
     * Draw the recent symbols on the slot machine.
     */
    public void reDrawSymbols(){
        for(Figure f : symbolsFig) f.makeInvisible();
        symbolsFig.clear();
        paintSymbols();
        for(Figure f : symbolsFig) {
            wait(200);
            f.makeVisible();
        }
    }

    /**
     * Paint the borders and the lever of the machine in a given color.
     * @param color The color of the body of the machine
     */
    private void paintBody(String color){
        Rectangle topBar = new Rectangle();
        topBar.changeColor(color);
        topBar.changeSize(TILE, TILE*(WIDTH_CANVAS-3));
        topBar.changePosition(TILE, TILE);
        topBar.makeVisible();
        machineFig.add(topBar);

        Rectangle leftEdge = new Rectangle();
        leftEdge.changeColor(color);
        leftEdge.changeSize(TILE, TILE);
        leftEdge.changePosition(TILE, TILE*2);
        leftEdge.makeVisible();
        machineFig.add(leftEdge);

        Rectangle rightEdge = new Rectangle();
        rightEdge.changeColor(color);
        rightEdge.changeSize(TILE, TILE);
        rightEdge.changePosition(TILE*(WIDTH_CANVAS-3), TILE*2);
        rightEdge.makeVisible();
        machineFig.add(rightEdge);

        Rectangle bottomBar = new Rectangle();
        bottomBar.changeColor(color);
        bottomBar.changeSize(TILE, TILE*(WIDTH_CANVAS-3));
        bottomBar.changePosition(TILE, TILE*3);
        bottomBar.makeVisible();
        machineFig.add(bottomBar);
    }

    /**
     * Paint the separation between a couple of wheels.
     */
    private void paintWheels(){
        double numWh = wheels.size();
        double length = WIDTH_CANVAS - 5;
        double lenSection = length/numWh;

        for (int i = 1; i < numWh; i++) { 
            Rectangle rec = new Rectangle();
            rec.changeColor("black"); //lineas delgadas
            rec.changeSize(TILE, 2);


            double x = (2 + lenSection*i)*60;
            int xFinal = (int) x-1;

            rec.changePosition(xFinal,2*TILE);
            wheelsFig.add(rec);
            rec.makeVisible();
        }
    }

    /**
     * Paint the symbols on screen.
     */
    private void paintSymbols(){
        double numWh = wheels.size();
        double length = WIDTH_CANVAS - 5;
        double lenSection = length/numWh;
        
        for (int i = 0; i < numWh; i++) {
            Symbol symb = wheels.get(i).getShownSymbol();
            if (symb == null || symb.getShape() == null) {
                continue;
            }
            Figure shp = symb.getShape();
            shp.changeColor(symb.getColor());
            shp.changeSize(40);

            double x =  (2 + lenSection*i)*TILE + ((lenSection*TILE) - 40)/2;
            int xFinal = (int) x;

            shp.changePosition(xFinal, 2*TILE + 10);
            symbolsFig.add(shp); 
            shp.makeVisible();
        }
    }
    
    /**
     * Paint the lever on screen
     */
    public void paintLever(){
        if(!visible) return;
        for(Figure f : leverFig) f.makeInvisible();
        leverFig.clear();
        
        Rectangle rec = new Rectangle();
        rec.changeColor("black");
        rec.changeSize(10, 60);
        rec.changePosition(21*TILE,3*TILE);
        rec.makeVisible();
        leverFig.add(rec);

        Rectangle rec1 = new Rectangle();
        rec1.changeColor("black");
        rec1.changeSize(70, 10);
        rec1.changePosition(22*TILE,2*TILE);
        rec1.makeVisible();
        leverFig.add(rec1);

        Circle circLever = new Circle();
        circLever.changeColor("red");
        circLever.changeSize(40);
        circLever.changePosition(21*TILE+45,1*TILE+20);
        circLever.makeVisible();
        leverFig.add(circLever);
    }
    
    /**
     * Erase the slot machine from the screen
     */
    private void eraseMachine(){
        for(Figure f : machineFig) f.makeInvisible(); // hace inivisible la maquina
        for(Figure f : leverFig) f.makeInvisible(); // hace invisible la palanca
        for(Figure f : symbolsFig) f.makeInvisible(); // hace invisible las simbolos
        for(Figure f : wheelsFig) f.makeInvisible(); // hace invisible las ruedas de la maquina
    }

    /**
     * Paint the machine back with its normal look.
     * It does nothing if the machine already has that look.
     */
    public void reDrawNormal(){
        if (!winning) return;
        for(Figure f : machineFig) f.changeColor("black");
        winning = false;
    }

    /**
     * Paint a different style of slot machine if the user wins a jackpot
     */
    public void paintWin(){
        if (winning) return;
        for(Figure f : machineFig) f.changeColor("green");
        winning = true;
    }

    /**
     * Paint the lever animation
     */
    public void paintLeverAnimation(){
        if(!visible) return;
        for(Figure f : leverFig) f.makeInvisible();
        leverFig.clear();

        Rectangle rec = new Rectangle();
        rec.changeColor("black");
        rec.changeSize(10, 60);
        rec.changePosition(21*TILE,3*TILE);
        rec.makeVisible();
        leverFig.add(rec);

        Circle circLever = new Circle();
        circLever.changeColor("red");
        circLever.changeSize(40);
        circLever.changePosition(22*TILE,2*TILE+45);
        circLever.makeVisible();
        leverFig.add(circLever);
    }

    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    private void wait(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        } catch (Exception e){
            // ignoring exception at the moment
        }
    }
}
