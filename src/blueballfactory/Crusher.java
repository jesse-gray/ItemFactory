/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueballfactory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.locks.Lock;

/**
 * This is the Crusher class, it is a subclass of the Utility class
 * It implements the IChangeState interface
 * @author grayj26
 */
public class Crusher extends Utility implements IChangeState, Runnable{
    //Properties
    private boolean state;
    
    //Constructors

    /**
     * This is the constructor for the Crusher class
     * @param topLeftX
     * @param topLeftY
     * @param width
     * @param height
     * @param canvas
     * @param state
     * @param lock
     */
    public Crusher(int topLeftX, int topLeftY, int width, int height, Graphics2D canvas, Boolean state, Lock lock){
        super(topLeftX, topLeftY, width, height, canvas, lock);
        this.state = state;
        picture = loadImage("Images/crusher.jpg");
    }
    
    //Methods
    @Override
    public String toString(){
        return "Crusher: " + super.toString() + ", State: " + state;
    }
    
    /**
     * Changes state between on/off
     */
    @Override
    public void changeState(){        
        lock.lock();
        Color bgColor = new Color(255, 255, 255);
        canvas.setColor(bgColor);
        canvas.fillRect(topLeftX, topLeftY, width, height);
        lock.unlock();
        if(state){
            topLeftY -= 65;
            state = false;
        } else {
            topLeftY += 65;
            state = true;
        }        
    }
    
    /**
     * Draws the crusher on the canvas
     */
    public void draw(){
        Color red = new Color(255, 0, 0);
        Color green = new Color(0, 128, 0);
        
        lock.lock();
        canvas.drawImage(picture, topLeftX, topLeftY, width, height, null);
        if(!state){
            canvas.setColor(red);
        } else{
            canvas.setColor(green);
        }
        canvas.fillOval(topLeftX + (width/2) - (width/20), topLeftY + (height/2) - (height/20), width/10, height/10);
       lock.unlock();
    }
    
    @Override
    public void run(){
        draw();
        while(true){
            //Sleep
            try {
                Thread.sleep((long)((Math.random() * 500) +2500));
            } catch (InterruptedException ex) {
            }
            //Draw
            changeState();
            draw();
            //Sleep
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
            //Draw
            changeState();
            draw();
        }
    }
    
    //Setters and getters

    /** 
     * Getter for the state property
     * @return state
     */
    public boolean getState() {
        return state;
    }

    /**
     * Setter for the state property
     * @param state
     */
    public void setState(boolean state) {
        this.state = state;
    }    
}
