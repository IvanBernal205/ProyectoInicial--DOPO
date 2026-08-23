package slotMachine;
import java.util.ArrayList;

/**
 * A wheel which is on the slot machine and that contains a symbol
 */
public class Wheel {
    
    private Integer symbIndex;
    private Symbol shownSymbol;

    /**
     * Set a symbol based on the wheel's index
     */
    public void setSymbIndex(int index){
        if(symbIndex != null){
            this.symbIndex = index;
        }
    }

    /**
     * 
     */
    public void symbolStillExist(String color,  ArrayList<Symbol> symbols, int deletedPos, boolean isVisible){
        if(shownSymbol == null) return;

        if(shownSymbol.getColor().equals(color)){
            if(symbols.isEmpty()){
                symbIndex = null;
                shownSymbol = null;
            }
            else{
                int newIndex = deletedPos % symbols.size();
                placeSymbol(newIndex, new Symbol(symbols.get(newIndex)));
            }

            if(isVisible){
                // posible accion para actualizar el canvas
            }
        }
        else if(symbIndex != null && symbIndex > deletedPos){
            symbIndex = symbIndex - 1;
        }
    }

    /**
     * Place a symbol on a specific wheel.
     */
    public void placeSymbol(int index, Symbol newSymbol){
        this.symbIndex = index;
        this.shownSymbol = newSymbol;
    }



    public Symbol getShownSymbol(){
        return shownSymbol;
    }

    public int getSymbIndex(){
        if (symbIndex == null){
            return -1;
        }
        return symbIndex;
    }
}