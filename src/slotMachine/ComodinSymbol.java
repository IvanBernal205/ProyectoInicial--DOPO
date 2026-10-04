package slotMachine;
import shapes.Circle;
import shapes.Figure;

/**
 * A symbol that matches any other symbol when checking for a jackpot.
 * It is drawn with a white hole in the middle of its shape so it can be told apart.
 * @author Iván Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica
 * @version 04/10/2026
 */
public class ComodinSymbol extends Symbol {
    public static final String MARK_COLOR = "white";
    public static final int MARK_SIZE = 12;
    private Figure mark;

    /**
     * Create a comodin symbol with a given color.
     * @param color The color of the symbol that will be created
     */
    public ComodinSymbol(String color){
        super(color);
        mark = null;
    }

    /**
     * Create a comodin symbol based on another one, with its own figure and mark.
     * @param original A comodin symbol that was already created
     */
    private ComodinSymbol(ComodinSymbol original){
        super(original);
        Circle hole = new Circle();
        hole.changeColor(MARK_COLOR);
        hole.changeSize(MARK_SIZE);
        mark = hole;
    }

    /**
     * Create a copy of this symbol that is still a comodin.
     * @return A new comodin symbol equal to this one
     */
    @Override
    public Symbol copy(){
        return new ComodinSymbol(this);
    }

    /**
     * Return the hole drawn in the middle of the shape of the symbol.
     * @return The mark of the symbol, or null if it has no figure yet
     */
    @Override
    public Figure getMark(){
        return mark;
    }

    /**
     * Place the hole in the middle of the figure of the symbol.
     * @param x The horizontal position of the figure, in pixels
     * @param y The vertical position of the figure, in pixels
     */
    @Override
    public void placeMark(int x, int y){
        if (mark != null){
            int offset = (size - MARK_SIZE) / 2;
            mark.changePosition(x + offset, y + offset);
        }
    }

    /**
     * Indicate that this symbol is a comodin.
     * @return true always
     */
    @Override
    public boolean isComodin(){
        return true;
    }
}
