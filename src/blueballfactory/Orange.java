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
public class Orange extends Fruit implements IMoveAndDraw, Runnable {
    //Properties
    
    //Constructors

    /**
     *
     * @param topLeftX
     * @param topLeftY
     * @param width
     * @param height
     * @param canvas
     * @param status
     * @param lock
     */
    public Orange(int topLeftX, int topLeftY, int width, int height, Graphics2D canvas, Boolean status, Lock lock){
        super(topLeftX, topLeftY, width, height, canvas, status, lock);
        picture = loadImage("Images/orange.jpg");
    }
    
    //Methods
    @Override
    public String toString(){
        return "Orange: " + super.toString();
    }
}
