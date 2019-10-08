/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueballfactory;

import java.awt.Graphics2D;
import java.util.concurrent.locks.Lock;

/**
 * This is the Conveyor class, it is a subclass of the Utility class
 * @author grayj26
 */
public class Conveyor extends Utility implements Runnable {
    //Properties
    private boolean direction = true;
    
    //Constructors

    /**
     * This is the constructor for the Conveyor class
     * @param topLeftX
     * @param topLeftY
     * @param width
     * @param height
     * @param canvas
     * @param direction
     * @param lock
     */
    public Conveyor(int topLeftX, int topLeftY, int width, int height, Graphics2D canvas, boolean direction, Lock lock){
        super(topLeftX, topLeftY, width, height, canvas, lock);
        this.direction = direction;//true: rightward, false: leftward
        picture = loadImage("Images/conveyor.jpg");
    }
    
    //Methods
    public String toString(){
        return "Conveyor: " + super.toString() + ", Direction: " + direction;
    }    
    
    /**
     * Draws the conveyor on the canvas
     */
    public void draw(){
        canvas.drawImage(picture, topLeftX, topLeftY, width, height, null);
    }
    
    @Override
    public void run(){
        while(true){
            draw();
        }
    }

    //Setters and getters

    /**
     * Getter for the direction property
     * @return direction
     */
    public boolean getDirection() {
        return direction;
    }

    /**
     * Setter for the direction property
     * @param direction
     */
    public void setDirection(boolean direction) {
        this.direction = direction;
    }
}
