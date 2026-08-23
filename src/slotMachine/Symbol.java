package slotMachine;
import shapes.Circle;

/**
 * A symbol that can be used in different wheels.
 */

public class Symbol {

    private String color;
    private Circle shape;

    /**
     * Create a symbol with a given color 
     */
    public Symbol(String color){
        this.color = color;
        this.shape = null;
    }

    /**
     * Create a symbol based on another one.
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
