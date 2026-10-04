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
     * Shows the symbol of the left neighbour wheel if it exists, otherwise shows the symbol of this wheel
     * @param index The index of the symbol to show
     * @param template The template of the symbol to show
     * @param spun Whether the wheel has been spun or not
     */
    @Override
    public void showSymbol(int index, Symbol template, boolean spun){
        if(leftWh != null && leftWh.getShownSymbol() != null){
            showStateOf(leftWh.getSymbIndex(), leftWh.getShownSymbol());
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
