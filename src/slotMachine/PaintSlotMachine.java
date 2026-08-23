package slotMachine;
import java.util.ArrayList;
import shapes.Circle;
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
    private ArrayList<Wheel> wheels;

    private ArrayList<Rectangle> machineRecs = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> bars = new ArrayList<>();
    private ArrayList<Circle> circ = new ArrayList<Circle>();  //aqui se guardan los symbols
    
    private Circle circLever; 
    //private ArrayList<Rectangle> rectLever = new ArrayList<Rectangle>();
    //private ArrayList<Circle> circLever = new ArrayList<Circle>();
    
    private Rectangle actualRect;

    /**
     * Paint a default slot machine.
     * @param wheels An array with the wheels already created
     */
    public PaintSlotMachine(ArrayList<Wheel> wheels){
        this.wheels = wheels;
    }
    

    public void build (){
        // paintMachine();
        // paintWheels();
        // paintSymbols();
    }

    /**
     * Make visible every instance used to create the slot machine and
     * the recent symbols on screen.
     */
    public void makeVisible(){
        visible = true;
        paintMachine();
        reDraw();
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
    
    /**
     * Draw the recent symbols on the slot machine.
     */
    public void reDrawSymbols(){
        for (Circle c: circ) c.makeInvisible();
        circ.clear();
        paintSymbols();
        
        if(visible){
            for(Circle c:circ) c.makeVisible();
        }
    }

    /**
     * Paint the slot machine broders and lever
     */
    private void paintMachine(){
        for (Rectangle r : machineRecs) r.makeInvisible();
        machineRecs.clear();
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
            bars.add(rec);
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

    /**
     * Erase the symbols on screen.
     */
    private void eraseSymbols(){
        for (int i = 0; i < circ.size(); i++){
            circ.get(i).makeInvisible();
        }
    }
    
    /**
     * Paint the lever on screen
     */
    private void paintLever(){
        Rectangle rec = new Rectangle();
        rec.changeColor("black");
        rec.changeSize(10, 60);
        rec.changePosition(21*TILE,3*TILE);
        //rec.makeVisible();
        machineRecs.add(rec);
        //rectLever.add(rec);

        Rectangle rec1 = new Rectangle();
        rec1.changeColor("black");
        rec1.changeSize(70, 10);
        rec1.changePosition(22*TILE,2*TILE);
        //
        //rec1.makeVisible();
        machineRecs.add(rec1); //Cambio Provisional
        //rectLever.add(rec1);

        if (circLever != null) circLever.makeInvisible();
        circLever = new Circle();
        circLever.changeColor("red");
        circLever.changeSize(40);
        circLever.changePosition(21*TILE+45,1*TILE+20);
        //circ.add(cir);
        circLever.makeVisible(); //circle rojo de lever
    }
    
    /**
     * Erase the slot machine from the screen
     */
    private void eraseMachine(){
        //if (!visible) return; //si ya es invisible, retorna
        for (int i = 0; i < bars.size(); i++){
            bars.get(i).makeInvisible();
        }
        
        for (int i = 0; i < machineRecs.size(); i++){ //para hacer invisible el contorno negro
            actualRect = machineRecs.get(i);
            actualRect.makeInvisible();
        }
        
        if (circLever != null) circLever.makeInvisible(); //hace invisible el circulo rojo
        eraseSymbols(); //hace invisible los simbolos
        
        for (int i = 0; i < bars.size(); i++){
            bars.get(i).makeInvisible(); //hace invisibles las barritas divisoras de wheels.
        }
        visible = false;
    }
    
    /**
     * Paint a different style of slot machine if the user wins a jackpot
     */
    public void reDrawWin(){
        paintWin();
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
    
    /**
     * Paint a different style of slot machine if the user wins a jackpot
     */
    public void paintWin(){
        for(Rectangle r: machineRecs) r.makeInvisible();
        machineRecs.clear();
        
        for (int i = 0; i < HEIGHT_CANVAS; i++) {
            for (int j = 0; j < WIDTH_CANVAS; j++) {

                if(i == 0 || j == 0 || i == HEIGHT_CANVAS-1 || j == WIDTH_CANVAS-1 || j == WIDTH_CANVAS-2 || (i==2 && j!=1 && j!=WIDTH_CANVAS-3)){
                    continue;
                }
                Rectangle rec = new Rectangle();
                rec.changeColor("green");
                rec.changeSize(TILE, TILE);
                rec.changePosition(j*TILE,i*TILE);
                machineRecs.add(rec);
            }
        }
        paintLever();
    }
}
