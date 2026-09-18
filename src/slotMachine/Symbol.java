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
    private String color;
    private String selectShape;
    private Figure shape;

    /**
     * Create a symbol with a given color 
     * @param color The color of the symbol what will be created
     */
    public Symbol(String color){
        int index = (int) (Math.random() * 3);

        this.color = color;
        this.shape = null;
        this.selectShape = FIGURES[index];
    }

    /**
     * Create a symbol based on another one.
     * @param original A symbol that was already created 
     */
    public Symbol(Symbol original){
        this.color = original.color;
        this.shape = assignFigure(original.selectShape);
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

    public String getColor(){
        return this.color;
    }

    public Figure getShape(){
        return shape;
    }

}
