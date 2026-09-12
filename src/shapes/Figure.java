package shapes;

/**
 * Base class for the shapes that can be manipulated and drawn on a canvas.
 */
public class Figure{

    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean isVisible;

    public Figure(int x, int y, String color){
        xPosition = x;
        yPosition = y;
        this.color = color;
        isVisible = false;
    }

    /**
     * Make this figure visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }

    /**
     * Make this figure invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    /*
     * Draw the figure with current specifications on screen.
     * Each subclass knows its own shape and overrides this.
     */
    protected void draw(){
    }

    /*
     * Erase the figure on screen.
     */
    protected void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

    /**
     * Move the figure a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the figure a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the figure a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the figure a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the figure horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the figure vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the figure horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the figure vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the color.
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /**
     * Change the position of the figure to a specific one.
     */
    public void changePosition(int x, int y){
        erase();
        yPosition = y;
        xPosition = x;
        draw();
    }

}
