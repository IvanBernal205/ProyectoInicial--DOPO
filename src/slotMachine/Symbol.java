package slotMachine;
import shapes.Circle;
import shapes.Figure;
import shapes.Rectangle;
import shapes.Triangle;

/**
 * A symbol that can be used in different wheels.
 * @author Iván Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica
 * @version 23/08/2026
 */

public class Symbol {
    public static final String[] FIGURES = {"triangulo", "rectangulo", "circulo"};
    public static final int DEFAULT_SIZE = 40;
    protected String color;
    protected String selectShape;
    protected Figure shape;
    protected int size;

    /**
     * Create a symbol with a given color
     * @param color The color of the symbol what will be created
     */
    public Symbol(String color){
        int index = (int) (Math.random() * 3);

        this.color = color;
        this.shape = null;
        this.selectShape = FIGURES[index];
        this.size = DEFAULT_SIZE;
    }

    /**
     * Create a symbol based on another one, with its own figure.
     * @param original A symbol that was already created
     */
    protected Symbol(Symbol original){
        this.color = original.color;
        this.selectShape = original.selectShape;
        this.size = original.size;
        this.shape = assignFigure(selectShape);
    }

    private Figure assignFigure(String sShape){
        switch (sShape) {
            case "triangulo":
                return new Triangle();
            case "rectangulo":
                return new Rectangle();
            case "circulo":
                return new Circle();
            default:
                return new Circle();
        }
    }

    /**
     * Create a copy of the symbol.
     * @return A copy of the symbol
     */
    public Symbol copy(){
        return new Symbol(this);
    }

    /**
     * Indicate that the symbol was shown on a wheel.
     * A normal symbol does not change when it is shown.
     */
    public void onSpin(){
    }

    public String getColor(){
        return this.color;
    }

    public Figure getShape(){
        return shape;
    }

    /**
     * Return the size used to draw the symbol.
     * @return The size of the symbol
     */
    public int getSize(){
        return size;
    }

    /**
     * Return the figure that marks the type of the symbol
     * @return The mark of the symbol, or null if it has none
     */
    public Figure getMark(){
        return null;
    }

    /**
     * Indicate if the symbol must be drawn on its wheel.
     * A normal symbol is always drawn.
     * @return true if the symbol is drawn, false if it is hidden
     */
    public boolean isVisible(){
        return true;
    }

    /**
     * Indicate if the symbol is a comodin.
     * A normal symbol is not a comodin.
     * @return true if the symbol is a comodin, false otherwise
     */
    public boolean isComodin(){
        return false;
    }
}
