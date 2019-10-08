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
 * This is the Backet class, it is a subclass of the Utility class
 * @author grayj26
 */
public class Bucket extends Utility implements Runnable{
    //Properties
    private String text;
    private int count;
    
    //Constructors

    /**
     * This is the constructor for the Bucket class
     * @param topLeftX
     * @param topLeftY
     * @param width
     * @param height
     * @param canvas
     * @param text
     * @param lock
     */
    public Bucket(int topLeftX, int topLeftY, int width, int height, Graphics2D canvas, String text, Lock lock){
        super(topLeftX, topLeftY, width, height, canvas, lock);
        this.text = text;
        this.count = 0;
        picture = loadImage("Images/bucket.jpg");   
    }
    
    //Methods
    @Override
    public String toString(){
        return "Bucket: " + super.toString() + ", Text: " + text;  
    }
    
    /**
     * Draws the bucket on the canvas
     */
    public void draw(){
        lock.lock();
        Color black = new Color(0, 0, 0);
        Color white = new Color(255, 255, 255);
        canvas.setColor(white);
        canvas.fillRect(topLeftX, topLeftY, width, height + 20);
        canvas.setColor(black);
        canvas.drawImage(picture, topLeftX, topLeftY, width, height, null);
        canvas.drawString(text + ": " + count, topLeftX + 10, topLeftY +50);
        lock.unlock();
    }
    
    @Override
    public void run(){
        while(true){
            draw();
        }
    }

    //Setters and getters

    /**
     * Getter for the text property
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for the text property
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter for the count property
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Setter for the count property
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }    
}
