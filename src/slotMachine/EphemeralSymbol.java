package slotMachine;

/**
 * A symbol that shrinks every time it lands on a wheel because of a spin,
 * until it becomes a dot. The change is only visual.
 * @author Iván Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica
 * @version 03/10/2026
 */
public class EphemeralSymbol extends Symbol {
    public static final int START_SIZE = 38;
    private static final int STEP = 8;
    private static final int MIN_SIZE = 4;

    /**
     * Create an ephemeral symbol with a given color.
     * It starts smaller than a normal symbol so it can be told apart.
     * @param color The color of the symbol that will be created
     */
    public EphemeralSymbol(String color){
        super(color);
        size = START_SIZE;
    }

    /**
     * Create an ephemeral symbol based on another one, with its own figure.
     * @param original An ephemeral symbol that was already created
     */
    private EphemeralSymbol(EphemeralSymbol original){
        super(original);
    }

    /**
     * Create a copy of this symbol that is still ephemeral.
     * @return A new ephemeral symbol equal to this one
     */
    @Override
    public Symbol copy(){
        return new EphemeralSymbol(this);
    }

    /**
     * Shrink the symbol one step, never below the minimum size.
     */
    @Override
    public void onSpin(){
        size = Math.max(MIN_SIZE, size - STEP);
    }
}
