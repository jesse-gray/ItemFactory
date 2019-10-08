/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueballfactory;

import java.awt.Graphics2D;
import java.util.concurrent.locks.Lock;

/**
 * This is the Banana class, it is a subclass of the Fruit class
 * It implements the IMoveAnd Draw interface
 * @author grayj26
 */
public class Banana extends Fruit implements IMoveAndDraw, Runnable{
    //Properties

    //Constructors

    /**
     * This is the constructor for the Banana class
     * @param topLeftX
     * @param topLeftY
     * @param width
     * @param height
     * @param canvas
     * @param status
     * @param lock
     */
    public Banana(int topLeftX, int topLeftY, int width, int height, Graphics2D canvas, Boolean status, Lock lock){
        super(topLeftX, topLeftY, width, height, canvas, status, lock);
        picture = super.loadImage("Images/banana.jpg");
    }

    //Methods
    @Override
    public String toString() {
        return "Banana: " + super.toString();
    }
}
