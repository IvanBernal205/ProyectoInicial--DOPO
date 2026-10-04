package slotMachine;

/**
 * A symbol that matches any other symbol when checking for a jackpot.
 * @author Iván Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica
 * @version 04/10/2026
 */
public class ComodinSymbol extends Symbol {

    /**
     * Create a comodin symbol with a given color.
     * @param color The color of the symbol that will be created
     */
    public ComodinSymbol(String color){
        super(color);
    }

    /**
     * Create a comodin symbol based on another one, with its own figure.
     * @param original A comodin symbol that was already created
     */
    private ComodinSymbol(ComodinSymbol original){
        super(original);
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
     * Indicate that this symbol is a comodin.
     * @return true always
     */
    @Override
    public boolean isComodin(){
        return true;
    }
}
