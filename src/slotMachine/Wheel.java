package slotMachine;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A wheel which is on the slot machine and that contains a symbol
 * @author Iván Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica
 * @version 23/08/2026
 */

public class Wheel {
    
    private Integer symbIndex;
    private Symbol shownSymbol;
    private boolean locked;
    private HashMap<String, Symbol> ownSymbols = new HashMap<>();

    public Wheel(){
        locked = false;
    }


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
     * Show a symbol based on the index at symbols list
     * @param index The index of the symbol that will be shown
     * @param template The symbol that will be shown
     * @param spun If the wheel was spun or not
     */
    public void showSymbol(int index, Symbol template, boolean spun){
        Symbol own = ownSymbols.get(template.getColor());
        if (own == null){
            own = template.copy();
            ownSymbols.put(template.getColor(), own);
        }
        if (spun) own.onSpin();
        this.symbIndex = index;
        this.shownSymbol = own;
    }

    /**
     * Forget a symbol that was deleted
     * @param color The color of the symbol that will be forgotten
     */
    public void forgetSymbol(String color){
        ownSymbols.remove(color);
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
                showSymbol(newIndex, symbols.get(newIndex), false);
            }
        }
        else if(symbIndex != null && symbIndex > deletedPos){
            symbIndex = symbIndex - 1;
        }
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
    
    //is
    public boolean getLocked(){
        return locked;
    }

    public void setLocked(boolean isLock){
        this.locked = isLock;
    }
}