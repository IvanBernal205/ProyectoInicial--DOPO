package slotMachine;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    private PaintSlotMachine psm;
    private boolean isVisible;
    private boolean ok; 

    public SlotMachine(){
        wheels = new ArrayList<>();
        symbols = new ArrayList<>();
        psm = new PaintSlotMachine(wheels);
        ok = true;
    }

    // Revisado
    public void addWheel(int pos){
        ok = false;
        pos = normalizePosWheel(pos);

        Wheel wh = new Wheel();
        wheels.add(pos, wh);

        if(isVisible) psm.reDraw();
        ok = true;
    }

    // Revisado
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

    // Revisado
    public void addSymbol(int pos, String color){
        ok = false;
        if (existColor(color)) return; // Si el color ya habia sido agreado no hace nada

        pos = normalizePosSym(pos);
        Symbol sym = new Symbol(color);
        symbols.add(pos, sym);

        // Se actulizan los indices de las ruedas que se vieron afectadas por el nuevo simbolo
        for (int i = pos+1; i < wheels.size(); i++) {
            Wheel wh = wheels.get(i);
            wh.setSymbIndex(i+1); // BUG es el indice incorrecto
        }

        ok = true;
    }

    // revisado
    public void delSymbol(String symbol){
        ok = false;
        if(symbols.isEmpty()){
            messageForUser("No se puede eliminar porque ningun simbolo ha sido creado.");
            return;
        }

        if(!existColor(symbol)) return; // Si el color no existe, no hay nada que borrar
        
        for (Symbol s : symbols) {
            if(symbol.equals(s.getColor())){
                symbols.remove(s);
                break;
            }
        }

        for (Wheel wh : wheels) {
            // si el simbolo eliminado esta siendo mostrado por alguna
            // rueda entonces se debe actulizar a otro simbolo
            wh.symbolStillExist(symbol, symbols, isVisible);
        }
        ok = true;
    }

    // revisado
    public void placeSymbol(int wheel, String symbol){
        if(wheels.isEmpty() || symbols.isEmpty()){
            messageForUser("No se puede agregar un simbolo si no hay ruedas.");
            return;
        }

        wheel = normalizePosWheel(wheel);
        
        Symbol symb = null;
        int i = 0;
        // Si el color existe guarda el indice y el simbolo para asignarlo
        for (Symbol s : symbols) {
            if(symbol.equals(s.getColor())){
                symb = s;
                break;
            }
            i++;
        }

        // si el simbolo existe entonces actuliza la rueda correspondiente
        if(symb != null){
            Wheel wh = wheels.get(wheel);
            wh.placeSymbol(i, new Symbol(symb)); // Linea dificil de explicar
            if(isVisible) psm.reDraw();
        }else{
            messageForUser("El simbolo que desea asignar no existe");
            return;
        }

        ok = true;
    }

    // 
    public void spin(int wheel){
        ok = false;
        if (wheels.isEmpty() || symbols.isEmpty()){
            messageForUser("No se puede girar porque no hay ruedas.");
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
            psm.reDraw();
        }
        ok = true;
    }
    
    public void spin(){
        if(wheels.isEmpty() || symbols.isEmpty()){
            messageForUser("No hay ruedas para girar.");
            return;
        }
        
        for (int i = 1; i < wheels.size()+1; i++)  spin(i);

        ok = true;
    }
    
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
    
    public int distinctSymbols(){ //cantidad de simbolos distintos
        
        if (wheels.isEmpty()){
            messageForUser("No hay ruedas.");
            ok = true;
            return 0;
        }

        ArrayList <String> coloresSimbolos = new ArrayList<>();
        
        Symbol actualSymbol;
        String actualColor;
        
        for (int i = 0; i < wheels.size(); i ++){
            actualSymbol = wheels.get(i).getShownSymbol();
            actualColor = actualSymbol.getColor();
            
            if(!coloresSimbolos.contains(actualColor)){
                coloresSimbolos.add(actualColor);
            }
        }
        
        int totalDistinSymb = coloresSimbolos.size();
        ok = true;
        return totalDistinSymb;
    }
    
    public String[] configuration(){
        if (wheels.isEmpty()){
            messageForUser("No hay ruedas.");
            return new String[0];
        }
        
        String [] elementosVisibles = new String[wheels.size()];
        
        for (int i = 0; i < wheels.size(); i++){
            Wheel wh = wheels.get(i);
            Symbol simboloActual = wh.getShownSymbol();
            if(simboloActual == null) {
                elementosVisibles[i] = null; //revisar si se deberia poner null o no
                continue;
            }
            
            elementosVisibles[i] = simboloActual.getColor();
        }
        ok = true;
        return elementosVisibles;
    }
    
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
        ok = true;
        return true;
    }
    
    public void makeVisible(){
        isVisible = true;
        psm.makeVisible();
    }
    
    public void makeInvisible(){
        psm.makeInvisible(); // falta esta logica :/
    }
    
    public void exit(){
        
    }
    
    public boolean ok(){
        return ok;
    }






    //devuelve la posicion real 
    private int normalizePosSym(int pos){
        pos--;

        if(pos <= 0 || symbols.isEmpty()){
            pos = 0;
        }else if (pos > symbols.size()){
            pos = symbols.size();
            // revisar si falta -1
        }
        return pos;
    }
    
    private int normalizePosWheel(int pos){
        pos--;

        if(pos <= 0 || wheels.isEmpty()){
            pos = 0;
        }else if (pos >= wheels.size()){
            pos = wheels.size() - 1;
            // revisar ese - 1
        }
        return pos;
    }

    private void messageForUser(String ms){
        if(isVisible)
            JOptionPane.showMessageDialog(null, ms);
    }

    // Verifica si el simbolo ya existe el alista de symbols
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