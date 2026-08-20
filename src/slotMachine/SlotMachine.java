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
        pos--;

        if(pos <= 0 || wheels.isEmpty()){
            pos = 0;
        }else if (pos > wheels.size()){
            pos = wheels.size() - 1;
        }

        Wheel wh = new Wheel();
        wheels.add(pos, wh);
        if(isVisible){
            //pintar nueva rueda sin ningun simbolo
        }
        ok = true;
    }

    public void delWheel(int pos){
        ok = false;
        pos--;
        if(wheels.isEmpty()){
            // posible mensaje de error
            ok = true;
            return;
        }else if(pos <= 0){
            pos = 0;
        }else if (pos > wheels.size()){
            pos = wheels.size() - 1;
        }
        wheels.remove(pos);
        if(isVisible){
            // quitar la rueda eliminada y re acomodar las demas
        }
        ok = true;
    }

    public void addSymbol(int pos, String color){
        ok = false;
        pos--;

        if(pos <= 0 || symbols.isEmpty()){
            pos = 0;
        }else if (pos > symbols.size()){
            pos = symbols.size() - 1;
        }

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
        pos--;

        if(symbols.isEmpty()){
            // posible mensaje de error
            ok = true;
            return;
        }else if(pos <= 0){
            pos = 0;
        }else if (pos > symbols.size()){
            pos = symbols.size() - 1;
        }
        String delColor = symbols.remove(pos).getColor();

        for (int i = 0; i < wheels.size(); i++) {
            Wheel wh = wheels.get(i);
            if(wh.getShownSymbol().getColor().equals(delColor)){

            }
            wh.symbolStillExist(delColor, symbols, isVisible);
        }

        ok = true;
    }

    public void placeSymbol(int wheel, String symbol){
        ok = false;
        wheel--;

        if(wheels.isEmpty()){
            // posible mensaje de error
            ok = true;
            return;
        }else if(wheel <= 0){
            wheel = 0;
        }else if (wheel > wheels.size()){
            wheel = wheels.size() - 1;
        }

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
            wh.placeSymbol(i, sym);
            if(isVisible){
                // pintar el color asignado
            }
        }
        
        ok = true;
    }











    public  ArrayList<Symbol> getSymbols(){
        return symbols;
    }

    public  ArrayList<Wheel> getWheels(){
        return wheels;
    }
}