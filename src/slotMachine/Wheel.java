package slotMachine;
import java.util.ArrayList;

/**
 * A wheel which is on the slot machine and that contains a symbol
 * @author Iván Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica
 * @version 23/08/2026
 */

public class Wheel {
    
    private Integer symbIndex;
    private Symbol shownSymbol;

    /**
     * Set a symbol based on the index at symbols list
     * @param index The index of the symbol that will be lcoated
     */
    public void setSymbIndex(int index){
        if(symbIndex != null){
            this.symbIndex = index;
        }
    }

    /**
     * To replace the symbols after these are deleted
     * @param color The color of the symbol that will be inspected
     * @param symbols An array with all the symbols created
     * @param deletPos The position that belong to the symbol deleted
     * @param isVisible If the symbol was visible or not
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
        }
        else if(symbIndex != null && symbIndex > deletedPos){
            symbIndex = symbIndex - 1;
        }
    }

    /**
     * Place a symbol on a specific wheel.
     * @param index The position where the symbol will be added
     * @param newSymbol The symbol that will be added
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