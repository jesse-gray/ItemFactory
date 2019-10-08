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
public abstract class Utility extends Shape {
    //Properties
    private volatile boolean collision = false;
    private volatile boolean collisionHandled = true;
    
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
    public Utility(int topLeftX, int topLeftY, int width, int height, Graphics2D canvas, Lock lock){
        super(topLeftX, topLeftY, width, height, canvas, lock);
    }
    
    //Methods
    @Override
    public String toString(){
        return super.toString();
    }

    //Setters and Getters

    /**
     *
     * @return
     */
    public boolean isCollision() {
        return collision;
    }

    /**
     *
     * @param collision
     */
    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    /**
     *
     * @return
     */
    public boolean isCollisionHandled() {
        return collisionHandled;
    }

    /**
     *
     * @param collisionHandled
     */
    public void setCollisionHandled(boolean collisionHandled) {
        this.collisionHandled = collisionHandled;
    }
}
