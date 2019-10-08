/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueballfactory;

import java.awt.Graphics2D;
import java.util.concurrent.locks.Lock;

/**
 * This is the Apple class, it is a subclass of the Fruit class
 * It implements the IMoveAnd Draw interface
 * @author grayj26
 */
public class Apple extends Fruit implements IMoveAndDraw, Runnable {
    //Properties
    
    //Constructors

    /**
     * This is the constructor for the Apple class
     * @param topLeftX
     * @param topLeftY
     * @param width
     * @param height
     * @param canvas
     * @param status
     * @param lock
     */
    public Apple(int topLeftX, int topLeftY, int width, int height, Graphics2D canvas, Boolean status, Lock lock){
        super(topLeftX, topLeftY, width, height, canvas, status, lock);
        picture = super.loadImage("Images/apple.jpg");
    }
    
    //Methods    
    @Override
    public String toString(){
        return "Apple: " + super.toString();
    }
}

