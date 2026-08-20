package slotMachine;

import java.util.ArrayList;

public class Wheel {
    
    private Integer symbIndex;
    private Symbol shownSymbol;

    public void setSymbIndex(int index){
        if(symbIndex != null){
            this.symbIndex = index;
        }
    }

    public void symbolStillExist(String color,  ArrayList<Symbol> symbols, boolean isVisible){
        if(shownSymbol.getColor().equals(color)){
            Symbol newSymbol = symbols.get(symbIndex);
            shownSymbol = new Symbol(newSymbol);
            
            if(isVisible){
                // posible accion para actualizar el canvas
            }
        }
    }

    public void placeSymbol(int index, Symbol newSymbol){
        this.symbIndex = index;
        this.shownSymbol = newSymbol;
    }



    public Symbol getShownSymbol(){
        return shownSymbol;
    }







    public void xd(Symbol sym){
        symbIndex = 1;
        shownSymbol = new Symbol(sym);
    }
}
