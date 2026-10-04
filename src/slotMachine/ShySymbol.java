package slotMachine;
import shapes.Figure;
import shapes.Rectangle;

/**
 * A symbol that switches between visible and invisible every time it lands
 * on a wheel because of a spin. The change is only visual.
 * It is drawn with a bar under its shape so it can be told apart.
 * @author Iván Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica
 * @version 04/10/2026
 */
public class ShySymbol extends Symbol {
    public static final String MARK_COLOR = "black";
    public static final int MARK_HEIGHT = 4;
    private boolean visible;
    private Figure mark;

    /**
     * Create a shy symbol with a given color. It starts visible.
     * @param color The color of the symbol that will be created
     */
    public ShySymbol(String color){
        super(color);
        visible = true;
        mark = null;
    }

    /**
     * Create a shy symbol based on another one, with its own figure and mark.
     * @param original A shy symbol that was already created
     */
    private ShySymbol(ShySymbol original){
        super(original);
        visible = original.visible;
        Rectangle bar = new Rectangle();
        bar.changeColor(MARK_COLOR);
        bar.changeSize(MARK_HEIGHT, size);
        mark = bar;
    }

    /**
     * Create a copy of this symbol that is still shy.
     * @return A new shy symbol equal to this one
     */
    @Override
    public Symbol copy(){
        return new ShySymbol(this);
    }

    /**
     * Switch the symbol between visible and invisible.
     */
    @Override
    public void onSpin(){
        visible = !visible;
    }

    /**
     * Return the bar drawn under the shape of the symbol.
     * @return The mark of the symbol, or null if it has no figure yet
     */
    @Override
    public Figure getMark(){
        return mark;
    }

    /**
     * Indicate if the symbol must be drawn on its wheel.
     * @return true if the symbol is drawn, false if it is hidden
     */
    @Override
    public boolean isVisible(){
        return visible;
    }
}
