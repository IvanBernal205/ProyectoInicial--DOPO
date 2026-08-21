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
    private PaintSlotMachine psm;

    public SlotMachine(){
        wheels = new ArrayList<>();
        symbols = new ArrayList<>();
        isVisible = false;
        ok = true;
        psm = new PaintSlotMachine();
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

    public void spin(int wheel){
        ok = false;
        if (wheels.isEmpty()){
            //No se puede girar si no hay wheels posible mensaje de error
            ok = true;
            return;
        }
        
        wheel = normalizePosWheel(wheel);
        int wheelPorGirar = wheels.indexOf(wheel);
        //borrador
        //spinnerWheel.setSymbIndex(1);
        
        
    }
    
    public void spin(){
        
    }
    
    public String[] symbols(){
        ok = false;
        if (symbols.isEmpty()){
            ok = true;
            return null;
        }
        
        int totalSimbolos = symbols.size();
        String [] simbolos = new String[totalSimbolos];
        
        Symbol simboloActual;
        String colorActual;
        
        for(int i = 0; i < totalSimbolos; i++){
            simboloActual = symbols.get(i);
            colorActual = simboloActual.getColor();
            
            simbolos [i] = colorActual;
        }
        
        ok = true;
        return simbolos;
    }
    
    public int distincSymbols(){ //cantidad de simbolos distintos
        ok = false;
        
        if (symbols.isEmpty()){
            ok = true;
            return 0;
        }
        ArrayList <String> coloresSimbolos = new ArrayList<>();
        
        Symbol simboloActual;
        String colorActual;
        
        for (int i = 0; i < symbols.size(); i ++){
            simboloActual = symbols.get(i);
            colorActual = simboloActual.getColor();
            
            if(!coloresSimbolos.contains(colorActual)){
                coloresSimbolos.add(colorActual);
            }
        }
        
        int tamanoFinal = coloresSimbolos.size();
        ok = true;
        return tamanoFinal;
    }
    
    public String[] configuration(){
        ok = false;        
        if (wheels.isEmpty()){
            ok = true;
            return null;
        }
        
        String [] elementosVisibles = new String[wheels.size()];
        
        for (int i = 0; i < wheels.size(); i++){
            Wheel wh = wheels.get(i);
            Symbol simboloActual = wh.getShownSymbol();
            
            elementosVisibles[i] = simboloActual.getColor();
        }
        ok = true;
        return elementosVisibles;
    }
    
    public boolean isJackpot(){
        ok = false;
        
        if (wheels.isEmpty()){
            //No hay ruedas
            ok = true;
            return false;
        }
        
        Wheel wh;
        wh = wheels.get(0);
        
        Symbol firstSymbol = wh.getShownSymbol();
        Symbol actualSymbol;
        
        String firstColor = firstSymbol.getColor() ;
        String actualColor;
        
        for (int i = 1; i < wheels.size() ; i++){
            wh = wheels.get(i);
            actualSymbol = wh.getShownSymbol();
            actualColor = actualSymbol.getColor();
            
            if (!firstColor.equals(actualColor)){
                ok = true;
                return false;
            }
        }
        ok = true;
        return true;
    }
    
    public void makeVisible(){
        isVisible = true;
        psm.makeVisible(wheels);
    }
    
    public void makeInvisible(){
        
    }
    
    public void exit(){
        
    }
    
    public boolean ok(){
        return ok;
    }






    //devuelve la posicion real 
    private int normalizePos(int pos){
        pos--;

        if(pos <= 0 || symbols.isEmpty()){
            pos = 0;
        }else if (pos > symbols.size()){
            pos = symbols.size() - 1;
        }
        return pos;
    }
    
    private int normalizePosWheel(int pos){
        pos--;

        if(pos <= 0 || wheels.isEmpty()){
            pos = 0;
        }else if (pos > wheels.size()){
            pos = wheels.size() - 1;
        }
        return pos;
    }





    public  ArrayList<Symbol> getSymbols(){
        return symbols;
    }

    public  ArrayList<Wheel> getWheels(){
        return wheels;
    }
}