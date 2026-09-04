package slotMachine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
/**
 * A slot machine where you can configure symbols and wheels, and also spin and know if 
 * it is a jackpot.
 * 
 * @author Iván Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica
 * @version 23/08/2026
 */
public class SlotMachine
{
    // CSS colors
    private static final Set<String> CSS_COLORS = new HashSet<>(Arrays.asList(
        "black", "blue", "brown", "gold", "gray", "green", "magenta",
        "orange", "pink", "purple", "red", "white", "yellow"));

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
     * @param pos The position where the wheel will be added.
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
     * @param pos The position of the wheel you want to delete
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
     * Lock a specific wheel so it doesn't spin.
     * @param wheel The position of the wheel to lock
     */
    public void lock(int wheel){
        ok = false;
        if(wheels.isEmpty()){
            messageForUser("No puedes fijar una rueda si no hay ruedas.");
            return;
        }
        int posWheel = normalizePosWheel(wheel);
        Wheel wheelToLock = wheels.get(posWheel);

        wheelToLock.setLocked(true);
        ok = true;
    }

    /**
     * Unlock a specific wheel so it can spin.
     * @param wheel The position of the wheel to unlock.
     */
    public void unlock(int wheel){
        ok = false;
        if(wheels.isEmpty()){
            messageForUser("No puedes desfijar una rueda si no hay ruedas.");
            return;
        }
        int posWheel = normalizePosWheel(wheel);
        Wheel wheelToUnlock = wheels.get(posWheel);

        wheelToUnlock.setLocked(false);
        ok = true;
    }

    /**
     * Add a symbol in a specific position in order to be used later.
     * @param pos The position where you want to add the symbol
     * @param color The color of the symbol
     */
    public void addSymbol(int pos, String color){
        ok = false;
        color = normalizeColor(color);
        if (color == null){
            messageForUser("El color no es un color CSS valido.");
            return;
        }
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
     * @param symbol The color of the symbol you want to delete
     */
    public void delSymbol(String symbol){
        ok = false;
        symbol = normalizeColor(symbol);
        if (symbol == null){
            messageForUser("El color indicado no es un color CSS valido.");
            return;
        }
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
     * @param wheel The position of the wheel where you want to locate a symbol
     * @param symbol The color of the symbol you want to place
     */
    public void placeSymbol(int wheel, String symbol){
        ok = false;
        if(wheels.isEmpty() || symbols.isEmpty()){
            messageForUser("No se puede agregar un simbolo si no hay ruedas.");
            return;
        }

        symbol = normalizeColor(symbol);
        if (symbol == null){
            messageForUser("El simbolo que desea asignar no existe");
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
     * @param wheel The position of the wheel you want to spin
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

        if (actualSymb == null || wh.getLocked()){ // Si esta fijo, no cambia de simbolo
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
        
        // isJackpot(); // por si al girar toca indicar que gano
        ok = true;
    }
    
    /**
     * Return a list with the symbols that can be used.
     * @return List of symbols that can be used.
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
     * @return Number of different symbols on screen
     */
    public int distinctSymbols(){ 
        ok = false;
        if (wheels.isEmpty() || symbols.isEmpty()){
            ok = true;
            return 0;
        }

        ArrayList <String> colorOfSymbols = new ArrayList<>(); 
        
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
     * @return List of the symbols on screen.
     */
    public String[] configuration(){
        if (wheels.isEmpty()){
            messageForUser("No hay ruedas.");
            return new String[0];
        }
        
        String [] visibleElements = new String[wheels.size()]; 
        
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
     * @return true if all the symbols on screen are equal, false otherwise
     */
    public boolean isJackpot(){
        ok = false;
        if (wheels.isEmpty() || symbols.isEmpty()){
            messageForUser("No hay ruedas o simbolos.");
            ok = true;
            return false;
        }
        
        boolean jackpot = true;
        Symbol firstSymbol = wheels.get(0).getShownSymbol();

        if(firstSymbol == null){
            jackpot = false;
        }
        else{
            String firstColor = firstSymbol.getColor();

            for (int i = 1; i < wheels.size() && jackpot ; i++){
                Symbol actualSymbol = wheels.get(i).getShownSymbol();

                if (actualSymbol == null || !firstColor.equals(actualSymbol.getColor())){
                    jackpot = false;
                }
            }
        }

        if(isVisible){
            if(jackpot) psm.reDrawWin();
            else psm.reDrawNormal();
        }

        ok = true;
        return jackpot;
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
        if (isVisible){
            makeInvisible();
        }
        System.exit(0);
    }
    
    /**
     * Indicate if last operacion was succesful
     * @return true if the last operation was succesful, false otherwise
     */
    public boolean ok(){
        return ok;
    }
    
    /**
     * Adjust the position based on the zero-indexed standard.
     * @param pos The position that you want to normalize
     * @return The position but now it's normalized
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
     * @param pos The position that you want to normalize
     * @return The position but now it's normalized
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
     * @param ms The message you want to show on screen
     */
    private void messageForUser(String ms){
        if(isVisible){
            JOptionPane.showMessageDialog(null, ms);
        }
    }

    /**
     * Verify if a symbol already exists.
     * @param symbol The symbol you want to confirm if exists
     * @return true if the color already exists, false if the color does not exist
     */
    private boolean existColor(String symbol){
        for (Symbol s : symbols) {
            if(symbol.equals(s.getColor()))
                return true;
        }
        return false;
    }

    /**
     * Adjust a color name to the CSS standard form used by the simulator.
     * @param color The color name given by the user
     * @return The color name in lower case, or null if it is not a valid CSS color
     */
    private String normalizeColor(String color){
        if (color == null) return null;

        String normalized = color.trim().toLowerCase();
        if (!CSS_COLORS.contains(normalized)) return null;

        return normalized;
    }

    public  ArrayList<Symbol> getSymbols(){
        return symbols;
    }

    public  ArrayList<Wheel> getWheels(){
        return wheels;
    }
}