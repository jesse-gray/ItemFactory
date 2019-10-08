/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueballfactory;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.locks.Lock;
import javax.imageio.ImageIO;

/**
 *
 * @author grayj26
 */
public abstract class Shape {
    //Properties

    /**
     *
     */
    protected int topLeftX;

    /**
     *
     */
    protected int topLeftY;

    /**
     *
     */
    protected int width;

    /**
     *
     */
    protected int height;

    /**
     *
     */
    protected Graphics2D canvas;

    /**
     *
     */
    protected Image picture;

    /**
     *
     */
    protected Lock lock;
    
    //Constructors

    /**
     *
     * @param topLeftX
     * @param topLeftY
     * @param width
     * @param height
     * @param canvas
     * @param lock
     */
    public Shape(int topLeftX, int topLeftY, int width, int height, Graphics2D canvas, Lock lock){
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.width = width;
        this.height = height;
        this.canvas = canvas;
        this.lock = lock;
    }
    
    //Methods
    //Load image form "images" package to image variable

    /**
     *
     * @param find
     * @return
     */
    public BufferedImage loadImage(String find) {
        //
        BufferedImage image = null;
        //
        try {
            File file = new File(find);
            image = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return
        return image;
    }
    
    @Override
    public String toString(){
        return "Coordinates: (" + topLeftX + ", " + topLeftY + "), Width: " + width + ", Height: " + height;
    }
    
    //Setters and getters

    /**
     *
     * @return
     */
    public int getTopLeftX() {
        return topLeftX;
    }

    /**
     *
     * @param topLeftX
     */
    public void setTopLeftX(int topLeftX) {
        this.topLeftX = topLeftX;
    }

    /**
     *
     * @return
     */
    public int getTopLeftY() {
        return topLeftY;
    }

    /**
     *
     * @param topLeftY
     */
    public void setTopLeftY(int topLeftY) {
        this.topLeftY = topLeftY;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @return
     */
    public Graphics2D getCanvas() {
        return canvas;
    }

    /**
     *
     * @param canvas
     */
    public void setCanvas(Graphics2D canvas) {
        this.canvas = canvas;
    }

    /**
     *
     * @return
     */
    public Image getPicture() {
        return picture;
    }

    /**
     *
     * @param picture
     */
    public void setPicture(Image picture) {
        this.picture = picture;
    }        

    /**
     *
     * @return
     */
    public Lock getLock() {
        return lock;
    }

    /**
     *
     * @param lock
     */
    public void setLock(Lock lock) {
        this.lock = lock;
    }
}
