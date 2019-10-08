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
 *
 * @author grayj26
 */
public class Vacuum extends Utility implements IChangeState, Runnable {
    //Properties
    private boolean state;
    
    //Constructors

    /**
     *
     * @param topLeftX
     * @param topLeftY
     * @param width
     * @param height
     * @param canvas
     * @param state
     * @param lock
     */
    public Vacuum(int topLeftX, int topLeftY, int width, int height, Graphics2D canvas, Boolean state, Lock lock){
        super(topLeftX, topLeftY, width, height, canvas, lock);
        this.state = state;
        picture = loadImage("Images/vacuum.jpg");
    }
    
    //Methods
    @Override
    public String toString(){
        return "Vacuum: " + super.toString() + ", State: " + state;
    }
    
    /**
     *
     */
    @Override
    public void changeState(){
        if(state){
            state = false;
        } else {
            state = true;
        }
    }
    
    /**
     *
     */
    public void draw(){
       lock.lock();
       Color red = new Color(255, 0, 0);
        Color green = new Color(0, 128, 0);
        
        if(state){
        canvas.setColor(green);
        } else{
        canvas.setColor(red);
        }
        
        canvas.drawImage(picture, topLeftX, topLeftY, width, height, null);
        canvas.fillOval(topLeftX + (width/2) - (width/20), topLeftY + (height/2) - (height/20), width/10, height/10);
        lock.unlock();
    }
    
    @Override
    public void run(){  
        while(true){
            draw();   
            //Sleep
            try {
                Thread.sleep((long)((Math.random() * 500) +4500));
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
     *
     * @return
     */
    public boolean getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(boolean state) {
        this.state = state;
    }
}
