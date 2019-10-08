/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueballfactory;

import java.awt.Graphics2D;
import java.util.concurrent.locks.Lock;

/**
 * This is the Gate class, it is a subclass of the Utility class
 * @author grayj26
 */
public class Gate extends Utility implements Runnable{
    //Properties
    private String type;
        
    //Constructors

    /**
     * This is the constructor for the Gate subclass
     * @param topLeftX
     * @param topLeftY
     * @param width
     * @param height
     * @param canvas
     * @param type
     * @param state
     * @param lock
     */
    public Gate(int topLeftX, int topLeftY, int width, int height, Graphics2D canvas, String type, boolean state, Lock lock){
        super(topLeftX, topLeftY, width, height, canvas, lock);
        this.type = type;
        picture = loadImage("Images/scanner.jpg");
    }
    
    //Methods
    public String toString(){
        return "Gate: " + super.toString() + ", Type: " + type;
    }
    
    /**
     * Draws the gate on the canvas
     */
    public void draw(){
        canvas.drawImage(picture, topLeftX, topLeftY, width, height, null);
    }

    @Override
    public void run() {
        while(true){
            draw();
        }
    }
    
    //Setters and getters

    /**
     * Getter for the type property
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for the type property
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}
