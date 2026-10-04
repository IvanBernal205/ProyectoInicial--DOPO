package slotMachine;


/**
 * Creates a wheel that doesn't allow to get locked, swap, and neither delete.
 *
 * @author César Santiago Malaver Garnica
 * @author Ivan Andres Bernal Sabogal
 * @version 03/10/2026
 */
public class Rebel extends Wheel{
    
    /**
     * Create a new Rebel wheel with it's values
     */
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