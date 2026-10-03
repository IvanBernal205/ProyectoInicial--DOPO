package slotMachine;


/**
 * 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rebel extends Wheel{
    
    public Rebel(){
        locked = false;
        enableSwap = false;
        enableDel = false;
    }
    
    @Override
    public void setLocked(boolean isLocked){
        //no se permite desbloquear
    }
}