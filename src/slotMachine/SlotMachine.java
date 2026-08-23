package slotMachine;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * A slot machine where you can configure symbols and wheels, and also spin and know if 
 * it is a jackpot.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SlotMachine
{
    private ArrayList<Wheel> wheels;
    private ArrayList<Symbol> symbols;
    private PaintSlotMachine psm;
    private boolean isVisible;
    private boolean ok; 

    /**
     * Create a new slotMachine and the needed ArrayList for it.
     */
    public SlotMachine(){
        wheels = new ArrayList<>();
        symbols = new ArrayList<>();
        psm = new PaintSlotMachine(wheels);
        ok = true;
    }

    /**
     * Add a wheel to the slot machine in a specific position. 
     */
    public void addWheel(int pos){
        ok = false;
        
        if (pos > wheels.size() && !wheels.isEmpty()){
            pos = normalizePosWheel(pos);
            pos ++;
        } 
        else{
        pos = normalizePosWheel(pos);
        }
        Wheel wh = new Wheel();
        wheels.add(pos, wh);
        
        if(isVisible) psm.reDraw();
        ok = true;
    }

    /**
     * Delete a wheel at a specific position
     */
    public void delWheel(int pos){
        ok = false;
        if(wheels.isEmpty()){
            messageForUser("No puedes eliminar una rueda porque aún no creas ninguna.");
            return;
        }
        
        pos = normalizePosWheel(pos);
        wheels.remove(pos);
        if(isVisible) psm.reDraw();

        ok = true;
    }

    /**
     * Add a symbol in a specific position in order to be used later.
     */
    public void addSymbol(int pos, String color){
        ok = false;
        if (existColor(color)) return; 

        pos = normalizePosSym(pos);
        Symbol sym = new Symbol(color);
        symbols.add(pos, sym);
        
        for (int i = 0; i < wheels.size(); i++){
            Wheel wh = wheels.get(i);
            
            if (wh.getSymbIndex() >= pos){
                wh.setSymbIndex(wh.getSymbIndex() + 1);
            }
        }
        ok = true;
    }

    /**
     * Delete a symbol previously added.
     */
    public void delSymbol(String symbol){
        ok = false;
        if(symbols.isEmpty()){
            messageForUser("No se puede eliminar porque ningun simbolo ha sido creado.");
            return;
        }

        if(!existColor(symbol)) return; 
        
        int deletedPos = 0;
        for (int i = 0; i < symbols.size(); i++) {
            if(symbol.equals(symbols.get(i).getColor())){
                deletedPos = i;
                symbols.remove(i);
                break;
            }
        }

        for (Wheel wh : wheels) {
            // si el simbolo eliminado esta siendo mostrado por alguna
            // rueda entonces se debe actulizar a otro simbolo
            wh.symbolStillExist(symbol, symbols, deletedPos, isVisible);
        }

        if(isVisible) psm.reDrawSymbols();
        ok = true;
    }

    /**
     * Place a symbol in a specific wheel.
     */
    public void placeSymbol(int wheel, String symbol){
        ok = false;
        if(wheels.isEmpty() || symbols.isEmpty()){
            messageForUser("No se puede agregar un simbolo si no hay ruedas.");
            return;
        }

        wheel = normalizePosWheel(wheel);
        
        Symbol symb = null;
        int i = 0;
        
        for (Symbol s : symbols) {
            if(symbol.equals(s.getColor())){
                symb = s;
                break;
            }
            i++;
        }
        
        if(symb != null){
            Wheel wh = wheels.get(wheel);
            wh.placeSymbol(i, new Symbol(symb)); 
            if(isVisible) psm.reDrawSymbols();
        }else{
            messageForUser("El simbolo que desea asignar no existe");
            return;
        }

        ok = true;
    }

    /**
     * Spin the symbol of a specific wheel on screen.
     */
    public void spin(int wheel){
        ok = false;
        if (wheels.isEmpty() || symbols.isEmpty()){
            messageForUser("No se puede girar.");
            return;
        }
        
        wheel = normalizePosWheel(wheel);
        Wheel wh = wheels.get(wheel);
        Symbol actualSymb = wh.getShownSymbol();

        if (actualSymb == null){
            ok = true;
            return;            
        }

        int actualIndex = wh.getSymbIndex();
        int nextIndex = (actualIndex+1)%symbols.size();
        
        Symbol s = symbols.get(nextIndex);
        wh.placeSymbol(nextIndex, new Symbol(s));
        
        if(isVisible){
            // Cuando se usa spin() se repinta cada qe una rueda gira y se ve raro.
            psm.reDrawSymbols(); //estaba sin el Symbols del final
        }
        ok = true;
    }
    
    /**
     * Spin all the symbols on screen.
     */
    public void spin(){
        ok = false;
        if(wheels.isEmpty() ){
            messageForUser("No hay ruedas para girar.");
            return;
        }
        if(symbols.isEmpty()) {
            messageForUser("No hay simbolos.");
            return;
        }
        for (int i = 1; i <= wheels.size(); i++)  spin(i); 

        ok = true;
    }
    
    /**
     * Return a list with the symbols that can be used.
     */
    public String[] symbols(){
        ok = false;
        if (symbols.isEmpty()){
            messageForUser("No hay simbolos.");
            ok = true;
            return new String[0];
        }
        
        int totalSymbols = symbols.size();
        String [] listSymbols = new String[totalSymbols];

        int i = 0;
        for (Symbol s : symbols) {
            listSymbols[i] = s.getColor();
            i++;
        }
        
        ok = true;
        return listSymbols;
    }
    
    /**
     * Return the amount of different symbols on screen.
     */
    public int distinctSymbols(){ 
        
        if (wheels.isEmpty()){
            messageForUser("No hay ruedas.");
            ok = true;
            return 0;
        }

        ArrayList <String> colorOfSymbols = new ArrayList<>(); //cambio var espanol
        
        Symbol actualSymbol;
        String actualColor;
        
        for (int i = 0; i < wheels.size(); i ++){
            actualSymbol = wheels.get(i).getShownSymbol();
            if(actualSymbol == null) continue;
            actualColor = actualSymbol.getColor();
            
            if(!colorOfSymbols.contains(actualColor)){
                colorOfSymbols.add(actualColor);
            }
        }
        
        int totalDistinSymb = colorOfSymbols.size();
        ok = true;
        return totalDistinSymb;
    }
    
    /**
     * Return a list with the colors of the symbols on screen.
     */
    public String[] configuration(){
        if (wheels.isEmpty()){
            messageForUser("No hay ruedas.");
            return new String[0];
        }
        
        String [] visibleElements = new String[wheels.size()]; //var a espanol
        
        for (int i = 0; i < wheels.size(); i++){
            Wheel wh = wheels.get(i);
            Symbol simboloActual = wh.getShownSymbol();
            if(simboloActual == null) {
                visibleElements[i] = null; 
                continue;
            }
            
            visibleElements[i] = simboloActual.getColor();
        }
        ok = true;
        return visibleElements;
    }
    
    /**
     * Show if all symbols on screen have the same color on screen and if it's true, 
     * paint the machine as winner.
     */
    public boolean isJackpot(){
        ok = false;
        if (wheels.isEmpty() || symbols.isEmpty()){
            messageForUser("No hay ruedas.");
            ok = true;
            return false;
        }
        
        Wheel wh; 
        wh = wheels.get(0);
        
        Symbol firstSymbol = wh.getShownSymbol();
        Symbol actualSymbol;
        
        if(firstSymbol == null){
            ok = true;
            return false;
        }
        
        String firstColor = firstSymbol.getColor() ;
        String actualColor;
        
        for (int i = 1; i < wheels.size() ; i++){
            wh = wheels.get(i);
            actualSymbol = wh.getShownSymbol();
            if (actualSymbol == null){
                ok = true;
                return false;
            }
            actualColor = actualSymbol.getColor();
            
            if (!firstColor.equals(actualColor)){
                ok = true;
                return false;
            }
        }
        if(isVisible) psm.reDrawWin();
        ok = true;
        return true;
    }
    
    /**
     * Make the slot machine visible.
     */
    public void makeVisible(){
        isVisible = true;
        psm.makeVisible();
    }
    
    /**
     * Make the slot machine invisible.
     */
    public void makeInvisible(){
        psm.makeInvisible(); 
        isVisible = false;
    }
    
    public void exit(){
        
    }
    
    /**
     * Indicate if last operacion was succesful
     */
    public boolean ok(){
        return ok;
    }
    
    /**
     * Adjust the position based on the zero-indexed standard.
     */
    private int normalizePosSym(int pos){
        pos--;  //1 --> 0

        if(pos <= 0 || symbols.isEmpty()){
            pos = 0;
        }
        else if (pos > symbols.size()){
            pos = symbols.size();
        }
        return pos;
    }
    
    /**
     * Adjust the position based on the zero-indexed standard.
     */
    private int normalizePosWheel(int pos){ 
        pos--; // 1 --> 0 

        if(pos <= 0 || wheels.isEmpty()){
            pos = 0;
        }
        else if (pos >= wheels.size()){
            pos = wheels.size() - 1;
        }
        return pos;
    }

    /**
     * Show different messages on screen.
     */
    private void messageForUser(String ms){
        if(isVisible){
            JOptionPane.showMessageDialog(null, ms);
        }
    }

    /**
     * Verify if a symbol already exists.
     */
    private boolean existColor(String symbol){
        for (Symbol s : symbols) {
            if(symbol.equals(s.getColor()))
                return true;
        }
        return false;
    }

    public  ArrayList<Symbol> getSymbols(){
        return symbols;
    }

    public  ArrayList<Wheel> getWheels(){
        return wheels;
    }
}