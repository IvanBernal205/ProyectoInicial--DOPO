package slotMachine;

import shapes.Circle;

public class Symbol {

    private String color;
    private Circle shape;

    public Symbol(String color){
        this.color = color;
    }

    public Symbol(Symbol original){
        this.color = original.color;
        this.shape = new Circle();
    }

    public String getColor(){
        return this.color;
    }

}
