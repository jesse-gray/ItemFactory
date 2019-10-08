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
public class Laser extends Utility implements IChangeState, Runnable{
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
    public Laser(int topLeftX, int topLeftY, int width, int height, Graphics2D canvas, boolean state, Lock lock){
        super(topLeftX, topLeftY, width, height, canvas, lock);
        this.state = state;
        picture = loadImage("Images/laser.jpg");        
    }
    
    //Methods
    @Override
    public String toString(){
        return "Laser: " + super.toString() + ", State: " + state;  
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
        Color red = new Color(255, 0, 0);
        Color green = new Color(0, 128, 0);
        Color white = new Color(255, 255, 255);
        int midpoint = (topLeftY + (height / 2));
        
        lock.lock();
        canvas.drawImage(picture, topLeftX, topLeftY, width, height, null);
        if(!state){
            canvas.setColor(white);
            canvas.drawLine(topLeftX, midpoint, 0, midpoint);
            canvas.setColor(red);
        } else{
            canvas.setColor(red);
            canvas.drawLine(topLeftX, midpoint, 0, midpoint);
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
                Thread.sleep((long)((Math.random() * 500) + 20000));
            } catch (InterruptedException ex) {
            }
            //Draw
            changeState();
            draw();
            //Sleep
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
            //Draw
            changeState();
            draw();
        }
    }

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
