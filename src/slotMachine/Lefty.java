package slotMachine;


/**
 * Creates a wheel that copy the state of it's left neighbour wheel
 *
 * @author César Santiago Malaver Garnica
 * @author Ivan Andres Bernal Sabogal
 * @version 03/10/2026
 */
public class Lefty extends Wheel{
    private Wheel leftWh; 
    
    public Lefty(Wheel leftWh){
        super();
        this.leftWh = leftWh;
    }
    
    /**
     * Show the same symbol as the left neighbour wheel. If there is no left
     * wheel or it has no symbol, show the given symbol.
     * @param index The index of the symbol that will be shown
     * @param template The symbol that will be shown
     * @param spun If the wheel was spun or not
     */
    @Override
    public void showSymbol(int index, Symbol template, boolean spun){
        if(leftWh != null && leftWh.getShownSymbol() != null){
            super.showSymbol(leftWh.getSymbIndex(), leftWh.getShownSymbol(), spun);
        }
        else{
            super.showSymbol(index, template, spun);
        }
    }
    
    public void setLeftWh(Wheel wh){
        leftWh = wh;
    }
    
    public Wheel getLeftWh(){
        return leftWh;
    }
}
