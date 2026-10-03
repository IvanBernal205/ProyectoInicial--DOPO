package slotMachine;


/**
 * Write a description of class Lefty here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
}
