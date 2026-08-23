package slotMachine;
import shapes.Circle;

/**
 * A symbol that can be used in different wheels.
 * @author Iván Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica
 * @version 23/08/2026
 */

public class Symbol {

    private String color;
    private Circle shape;

    /**
     * Create a symbol with a given color 
     * @param color The color of the symbol what will be created
     */
    public Symbol(String color){
        this.color = color;
        this.shape = null;
    }

    /**
     * Create a symbol based on another one.
     * @param original A symbol that was already created 
     */
    public Symbol(Symbol original){
        this.color = original.color;
        this.shape = new Circle();
    }

    public String getColor(){
        return this.color;
    }

    public Circle getShape(){
        return shape;
    }

}
