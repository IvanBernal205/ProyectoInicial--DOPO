package slotMachine;

import java.util.ArrayList;


/**
 * Write a description of class SlotMachine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SlotMachine
{
    private ArrayList<Wheel> wheels;
    private ArrayList<Symbol> symbols;
    private boolean isVisible;
    private boolean ok; 

    public SlotMachine(){
        wheels = new ArrayList<>();
        symbols = new ArrayList<>();
        isVisible = false;
        ok = true;
    }

    public void addWheel(int pos){
        ok = false;
        pos = normalizePos(pos);

        Wheel wh = new Wheel();
        wheels.add(pos, wh);
        if(isVisible){
            //pintar nueva rueda sin ningun simbolo
        }
        ok = true;
    }

    public void delWheel(int pos){
        ok = false;
        if(wheels.isEmpty()){
            // posible mensaje de error
            ok = true;
            return;
        }
        pos = normalizePos(pos);
        wheels.remove(pos);
        if(isVisible){
            // quitar la rueda eliminada y re acomodar las demas
        }
        ok = true;
    }

    public void addSymbol(int pos, String color){
        ok = false;
        pos = normalizePos(pos);

        Symbol sym = new Symbol(color);
        symbols.add(pos, sym);

        for (int i = pos+1; i < wheels.size(); i++) {
            Wheel wh = wheels.get(i);
            wh.setSymbIndex(i+1);
        }

        ok = true;
    }

    public void delSymbol(int pos){
        ok = false;
        if(symbols.isEmpty()){
            // posible mensaje de error
            ok = true;
            return;
        }
        pos = normalizePos(pos);

        String delColor = symbols.remove(pos).getColor();

        for (int i = 0; i < wheels.size(); i++) {
            Wheel wh = wheels.get(i);
            if(wh.getShownSymbol().getColor().equals(delColor)){
                //revisar para que se puso este if
            }
            wh.symbolStillExist(delColor, symbols, isVisible);
        }

        ok = true;
    }

    public void placeSymbol(int wheel, String symbol){
        ok = false;
        if(wheels.isEmpty()){
            // posible mensaje de error
            ok = true;
            return;
        }
        wheel = normalizePos(wheel);

        boolean colorExist = false;
        Symbol sym = null;
        int i;
        for ( i = 0; i < symbols.size(); i++) {
            sym = symbols.get(i);
            if(sym.getColor().equals(symbol)){
                colorExist = true;
                break;
            }
        }

        if(colorExist){
            Wheel wh = wheels.get(wheel);
            wh.placeSymbol(i, new Symbol(sym));
            if(isVisible){
                // pintar el color asignado
            }
        }
        
        ok = true;
    }








    private int normalizePos(int pos){
        pos--;

        if(pos <= 0 || symbols.isEmpty()){
            pos = 0;
        }else if (pos > symbols.size()){
            pos = symbols.size() - 1;
        }
        return pos;
    }



    public void pintar(){
        PaintSlotMachine psm = new PaintSlotMachine();
        psm.paintAll(wheels);
    }



    public  ArrayList<Symbol> getSymbols(){
        return symbols;
    }

    public  ArrayList<Wheel> getWheels(){
        return wheels;
    }
}