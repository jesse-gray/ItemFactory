/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueballfactory;

import java.awt.Graphics2D;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author grayj26
 */
public class Seesaw extends Utility implements IChangeState, Runnable {
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
    public Seesaw(int topLeftX, int topLeftY, int width, int height, Graphics2D canvas, boolean state, Lock lock){
        super(topLeftX, topLeftY, width, height, canvas, lock);
        this.state = state;
        picture = loadImage("Images/seesaw1.jpg");
    }
    
    //Methods
    public String toString(){
        return "Seesaw: " + super.toString() + ", State: " + state;
    }
    
    /**
     *
     */
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
        canvas.drawImage(picture, topLeftX, topLeftY, width, height, null);
    }
    
    public void run(){
        draw();   
        while(true){
            try {
                Thread.sleep((long)((Math.random() * 500) +2500));
            } catch (InterruptedException ex) {
            }
            if(this.getState()){
                this.setState(false);
            picture = loadImage("Images/seesaw2.jpg");
            }
            else
            {
                this.setState(true);
                picture = loadImage("Images/seesaw1.jpg");
            }
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
