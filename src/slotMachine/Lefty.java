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
    
    @Override
    public void placeSymbol(int index, Symbol newSymbol){
        if(leftWh!=null){
        this.symbIndex = leftWh.getSymbIndex();
        this.shownSymbol = leftWh.getShownSymbol();
        }
        else{
            super.placeSymbol(index, newSymbol);
        }
    }
    
    public void setLeftWh(Wheel wh){
        leftWh = wh;
    }
    
    public Wheel getLeftWh(){
        return leftWh;
    }
}
