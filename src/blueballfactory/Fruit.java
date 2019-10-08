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
 *  This is the Fruit abstract class, it is a subclass of the Shape class
 *  It implements the ICheckCollision interface
 * @author grayj26
 */
public abstract class Fruit extends Shape implements ICheckCollision, Runnable{
    //Properties
    protected boolean status;
    protected int[] boundary = new int[4];//[0]:topX,[1]:topY,[2]:bottomX,[3]:bottomY
    protected int speedX = 3;
    protected int speedY = 5;
    protected Color bgColor = new Color(0, 255, 0);
    protected volatile boolean exit = false;
    
    //Collision statuses
    protected volatile boolean fruitFruitCollisionStatus = false;
    protected volatile boolean fruitConveyorCollisionStatus = false;
    protected volatile int conveyorStatus = 0;//0: move free, 1: move on top of conveyorbelt (right), 2: move on top of conveyorbelt (left), 
    //3: move off conveyorbelt, 4: touch the bottotm, 5: touch the side
    protected volatile boolean fruitVacuumCollisionStatus = false;
    protected volatile int vacuumStatus = 0;//1: underneath, 2: touching
    protected volatile boolean fruitSeesawCollisionStatus = false;
    protected volatile int seesawStatus = 0;//1: right facing, 2: left facing
    protected volatile boolean fruitLaserCollisionStatus = false;
    protected volatile boolean fruitCrusherCollisionStatus = false;
    protected volatile boolean fruitScannerCollisionStatus = false;
    protected volatile boolean fruitGateCollisionStatus = false;
    protected volatile int gateStatus = 0;//0: move free, 1: move on top of conveyorbelt, 
    //2: move off conveyorbelt, 3: touch the bottotm, 4: touch the side
    protected volatile boolean fruitBucketCollisionStatus = false;
    
    //Constructors

    /**
     * This is the constructor for the Fruit class
     * @param topLeftX
     * @param topLeftY
     * @param width
     * @param height
     * @param canvas
     * @param status
     * @param lock
     */
    public Fruit(int topLeftX, int topLeftY, int width, int height, Graphics2D canvas, Boolean status, Lock lock){
        super(topLeftX, topLeftY, width, height, canvas, lock);
        this.status = status;
    }
    
    //Methods 
    @Override
    public String toString(){
        return super.toString() + ", Status: " + status;
    }
    
    /**
     * Moves the fruit to a new location on the canvas
     * @param speedX
     * @param speedY
     */
    public void move(int speedX, int speedY){  
        
        //---For Fruit collisions---
        if(fruitFruitCollisionStatus){
            speedX = 0;
            speedY = 0;
        }
        
        //---For Conveyor collisions---
        else if(fruitConveyorCollisionStatus){
            if (conveyorStatus == 0) {
                //Moving freely
            } else if (conveyorStatus == 1) {
                //Moving on top of the conveyor (right)
                speedX = 5;
                speedY = 0;
            } else if (conveyorStatus == 2) {
                //Moving on top of the conveyor (left)
                speedX = -5;
                speedY = 0;
            } else if (conveyorStatus == 3) {
                //Moving off the conveyor
                speedX = 3;
                speedY = 0;
            } else if (conveyorStatus == 4) {
                //Touch the bottom of conveyor
                speedY = -this.speedY;
            } else if (conveyorStatus == 5) {
                //bounce off
                speedX = -this.speedX;
                this.topLeftX += 10;
            }
        }
        
        //---For Seesaw collisions---
        else if(fruitSeesawCollisionStatus){
            if(seesawStatus == 1){
                speedX = -3;
                speedY = 5;
            } else{
                speedX = 3;
                speedY = 5;                
            }        
        }
        
        //---For crusher collisions---
        else if(fruitCrusherCollisionStatus){
            this.setPicture(loadImage("Images/trash.jpg"));
            this.setStatus(false);
        }       
        
        //---For Gate collisions---
        else if(fruitGateCollisionStatus){
            topLeftY -= 2;
            speedX = 5;
            speedY = 0;
        }
        
        //---For Laser collisions---
        if(fruitLaserCollisionStatus){
            this.setPicture(loadImage("Images/trash.jpg"));
            this.setStatus(false);
        }
        
        //--For Vacuum collisions---
        if(fruitVacuumCollisionStatus){
            if(vacuumStatus == 1){
                speedX = 0;
                speedY = -15;
            }       
        }
        
        //---Move---
        topLeftX += speedX;
        topLeftY += speedY;
        checkBoundaryCollision();
    }
    
    /**
     * Draws the fruit on the canvas
     */
    public void draw(){
        //Draw image       
        lock.lock();
        try {
            //Draw
            canvas.drawImage(picture, topLeftX, topLeftY, width, height, null);
        } catch (Exception e) {
        }
        lock.unlock();

        //Sleep
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
        }

        //Remove
        lock.lock();
        try {
            canvas.setColor(bgColor);
            canvas.fillRect(topLeftX, topLeftY, width, height);
        } catch (Exception e) {
        }
        lock.unlock();
    }
    
    @Override
    public void run() {
        draw();
        while (!exit) {
            move(speedX, speedY);
            draw();
        }
    }
    
    /**
     * CHecks collision with fruit
     * @param toCheck
     * @return
     */
    @Override
    public boolean checkCollision(Fruit toCheck) {
        //1: Calculate the center coordinates of two objects
        int fruitCenterX = this.getTopLeftX() + this.getWidth() / 2;
        int fruitCenterY = this.getTopLeftY() + this.getHeight() / 2;
        int toCheckCenterX = toCheck.getTopLeftX() + toCheck.getWidth() / 2;
        int toCheckCenterY = toCheck.getTopLeftY() + toCheck.getHeight() / 2;

        //2: Calculate the distance between two objects
        int centralDistanceX = Math.abs(fruitCenterX - toCheckCenterX);
        int centralDistanceY = Math.abs(fruitCenterY - toCheckCenterY);

        //3: Check two collision conditions
        if (centralDistanceX <= (this.getWidth() / 2 + toCheck.getWidth() / 2)
                && centralDistanceY <= (this.getHeight() / 2 + toCheck.getHeight() / 2)) {
            return true;
        } else{
            return false;
        }
    }
    
    /**
     * Checks collision with conveyor
     * @param conveyor
     * @return
     */
    @Override
    public boolean checkCollision(Conveyor conveyor) {
        //1: Calculate the center coordinates of two objects
        int thisFruitCenterX = this.getTopLeftX() + this.getWidth() / 2;
        int thisFruitCenterY = this.getTopLeftY() + this.getHeight() / 2;
        int conveyorCenterX = conveyor.getTopLeftX() + conveyor.getWidth() / 2;
        int conveyorCenterY = conveyor.getTopLeftY() + conveyor.getHeight() / 2;

        //2: Calculate the distance between two objects
        int centralDistanceX = Math.abs(thisFruitCenterX - conveyorCenterX);
        int centralDistanceY = Math.abs(thisFruitCenterY - conveyorCenterY);

        //3: Check two collision conditions
        if (centralDistanceX <= (this.getWidth() / 2 + conveyor.getWidth() / 2)
                && centralDistanceY <= (this.getHeight() / 2 + conveyor.getHeight() / 2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks collision status of conveyor 
     * @param conveyor
     * @return
     */
    public int checkCollisionStatus(Conveyor conveyor) {
        //1: Calculate the center coordinates of two objects
        int thisFruitCenterX = this.getTopLeftX() + this.getWidth() / 2;
        int thisFruitCenterY = this.getTopLeftY() + this.getHeight() / 2;
        int conveyorCenterX = conveyor.getTopLeftX() + conveyor.getWidth() / 2;
        int conveyorCenterY = conveyor.getTopLeftY() + conveyor.getHeight() / 2;

        //2: Calculate the distance between two objects
        int centralDistanceX = Math.abs(thisFruitCenterX - conveyorCenterX);
        int centralDistanceY = Math.abs(thisFruitCenterY - conveyorCenterY);

        //3: Check two collision conditions
        if (centralDistanceX <= (this.getWidth() / 2 + conveyor.getWidth() / 2)
                && centralDistanceY <= (this.getHeight() / 2 + conveyor.getHeight() / 2)) {
            //Check the status
            if (this.speedY >= 0 && thisFruitCenterX < (conveyor.getTopLeftX() + conveyor.getWidth())) {
                //Fall on the surface of conveyor belt
                if(conveyor.getDirection()){
                    return 1;
                }else {
                    return 2;
                }
            } else if (this.speedY == 0 && thisFruitCenterX >= (conveyor.getTopLeftX() + conveyor.getWidth())) {
                //Fall off the conveyor belt
                return 3;
            } else if (this.speedY < 0) {
                //Touch the belt bottom
                return 4;
            } else {
                //Touch the belt side
                return 5;
            }
        } else {
            //Move freely, no collision.
            return 0;
        }
    }
    
    /**
     * Checks collision status of seesaw 
     * @param seesaw
     * @return
     */
    public int checkCollisionStatus(Seesaw seesaw) {
        if(seesaw.getState()){
            return 1;
        } else{
            return 2;
        }
    }
    
    /**
     * Checks collision status of vacuum 
     * @param vacuum
     * @return
     */
    public int checkCollisionStatus(Vacuum vacuum) {
        if(this.getTopLeftY() <= vacuum.getTopLeftY() + vacuum.getHeight()){
            return 2;
        } else{
            return 1;
        }
    }

    /**
     * Checks collision with vacuum
     * @param toCheck
     * @return
     */
    @Override
    public boolean checkCollision(Vacuum toCheck) {
        if (this.getTopLeftX() + this.getWidth() > toCheck.getTopLeftX() && this.getTopLeftX() < toCheck.getTopLeftX() + toCheck.getWidth() && this.getTopLeftY() < 80 && toCheck.getState()) {
            return true;
        } else {
            return false;
        }}

    /**
     * Checks collision with seesaw
     * @param toCheck
     * @return
     */
    @Override
    public boolean checkCollision(Seesaw toCheck) {
        //1: Calculate the center coordinates of two objects
        int thisFruitCenterX = this.getTopLeftX() + this.getWidth() / 2;
        int thisFruitCenterY = this.getTopLeftY() + this.getHeight() / 2;
        int seesawCenterX = toCheck.getTopLeftX() + toCheck.getWidth() / 2;
        int seesawCenterY = toCheck.getTopLeftY() + toCheck.getHeight() / 2;

        //2: Calculate the distance between two objects
        int centralDistanceX = Math.abs(thisFruitCenterX - seesawCenterX);
        int centralDistanceY = Math.abs(thisFruitCenterY - seesawCenterY);

        //3: Check two collision conditions
        if (centralDistanceX <= (this.getWidth() / 2 + toCheck.getWidth() / 2)
                && centralDistanceY <= (this.getHeight() / 2 + toCheck.getHeight() / 2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks collision with laser
     * @param toCheck
     * @return
     */
    @Override
    public boolean checkCollision(Laser toCheck) {
        //1: Calculate the laser coordinates
        int laserY = toCheck.getTopLeftY() + (toCheck.getHeight() / 2);

        //3: Check two collision conditions
        if (this.getTopLeftY() > laserY - this.getHeight() && this.getTopLeftY() < laserY && toCheck.getState()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks collision with crusher
     * @param toCheck
     * @return
     */
    @Override
    public boolean checkCollision(Crusher toCheck) {
        //1: Calculate the center coordinates of two objects
        int thisFruitCenterX = this.getTopLeftX() + this.getWidth() / 2;
        int thisFruitCenterY = this.getTopLeftY() + this.getHeight() / 2;
        int crusherCenterX = toCheck.getTopLeftX() + toCheck.getWidth() / 2;
        int crusherCenterY = toCheck.getTopLeftY() + toCheck.getHeight() / 2;

        //2: Calculate the distance between two objects
        int centralDistanceX = Math.abs(thisFruitCenterX - crusherCenterX);
        int centralDistanceY = Math.abs(thisFruitCenterY - crusherCenterY);

        //3: Check two collision conditions
        if (centralDistanceX <= (this.getWidth() / 2 + toCheck.getWidth() / 2)
                && centralDistanceY <= (this.getHeight() / 2 + toCheck.getHeight() / 2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks collision with gate
     * @param toCheck
     * @return
     */
    @Override
    public boolean checkCollision(Gate toCheck) {
        //1: Calculate the center coordinates of two objects
        int thisFruitCenterX = this.getTopLeftX() + this.getWidth() / 2;
        int thisFruitCenterY = this.getTopLeftY() + this.getHeight() / 2;
        int gateCenterX = toCheck.getTopLeftX() + toCheck.getWidth() / 2;
        int gateCenterY = toCheck.getTopLeftY() - 10 + toCheck.getHeight() / 2;

        //2: Calculate the distance between two objects
        int centralDistanceX = Math.abs(thisFruitCenterX - gateCenterX);
        int centralDistanceY = Math.abs(thisFruitCenterY - gateCenterY);

        //3: Check two collision conditions
        if (centralDistanceX <= (this.getWidth() / 2 + toCheck.getWidth() / 2)
                && centralDistanceY <= (this.getHeight() / 2 + toCheck.getHeight() / 2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks collision with bucket
     * @param toCheck
     * @return
     */
    @Override
    public boolean checkCollision(Bucket toCheck) {
        //1: Calculate the center coordinates of two objects
        int thisFruitCenterX = this.getTopLeftX() + this.getWidth() / 2;
        int thisFruitCenterY = this.getTopLeftY() + this.getHeight() / 2;
        int bucketCenterX = toCheck.getTopLeftX() + toCheck.getWidth() / 2;
        int bucketCenterY = toCheck.getTopLeftY() + toCheck.getHeight() / 2;

        //2: Calculate the distance between two objects
        int centralDistanceX = Math.abs(thisFruitCenterX - bucketCenterX);
        int centralDistanceY = Math.abs(thisFruitCenterY - bucketCenterY);

        //3: Check two collision conditions
        if (centralDistanceX <= (this.getWidth() / 2 + toCheck.getWidth() / 2)
                && centralDistanceY <= (this.getHeight() / 2 + toCheck.getHeight() / 2)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Checks collision with canvas boundaries
     */
    @Override
    public void checkBoundaryCollision() {
        //Chcks if fruit hits a boundary and rebounds if yes  
        //Check the horizontal borders
        if (this.topLeftY < boundary[1]) {
            //Top border
            speedY = -speedY;
            topLeftY = boundary[1];
        } else if (topLeftY + height > boundary[3]) {
            //Bottom border
            speedY = -speedY;
            topLeftY = boundary[3] - height;
        }

        //Check the vertical borders
        if (topLeftX < boundary[0]) {
            //Left border
            speedX = -speedX;
            topLeftX = boundary[0];
        } else if (topLeftX + width > boundary[2]) {
            //Right border
            speedX = -speedX;
            topLeftX = boundary[2] - width;
        }
    }

    //stop() method

    /**
     * Stops the fruits thread
     */
    public void stop() {
        exit = true;
    }

    //Setters and getters

    /**
     * Getter for the status property
     * @return status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Setter for the status property
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Getter for the boundary property
     * @return boundary
     */
    public int[] getBoundary() {
        return boundary;
    }

    /**
     * Setter for the boundary property
     * @param boundary
     */
    public void setBoundary(int[] boundary) {
        this.boundary = boundary;
    }   

    /**
     * Getter for the speedX property
     * @return speedX
     */
    public int getSpeedX() {
        return speedX;
    }

    /**
     * Setter for the speedX property
     * @param speedX
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    /**
     * Getter for the speedY property
     * @return speedY
     */
    public int getSpeedY() {
        return speedY;
    }

    /**
     * Setter for the speedY property
     * @param speedY
     */
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    } 

    /**
     * Getter for the bgColor property
     * @return bgColor
     */
    public Color getBgColor() {
        return bgColor;
    }

    /**
     * Setter for the bgColor property
     * @param bgColor
     */
    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }  

    /**
     * Getter for the exit property
     * @return exit
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Setter for the exit property
     * @param exit
     */
    public void setExit(boolean exit) {
        this.exit = exit;
    }

    /**
     * Getter for the fruitFruitCollisionStatus property
     * @return fruitFruitCollisionStatus
     */
    public boolean isFruitFruitCollisionStatus() {
        return fruitFruitCollisionStatus;
    }

    /**
     * Setter for the fruitFruitCollisionStatus property
     * @param fruitFruitCollisionStatus
     */
    public void setFruitFruitCollisionStatus(boolean fruitFruitCollisionStatus) {
        this.fruitFruitCollisionStatus = fruitFruitCollisionStatus;
    }

    /**
     * Getter for the fruitConveyorCollisionStatus property
     * @return fruitConveyorCollisionStatus
     */
    public boolean isFruitConveyorCollisionStatus() {
        return fruitConveyorCollisionStatus;
    }

    /**
     * Setter for the fruitConveyorCollisionStatus property
     * @param fruitConveyorCollisionStatus
     */
    public void setFruitConveyorCollisionStatus(boolean fruitConveyorCollisionStatus) {
        this.fruitConveyorCollisionStatus = fruitConveyorCollisionStatus;
    }

    /**
     * Getter for the conveyorStatus property
     * @return conveyorStatus
     */
    public int getConveyorStatus() {
        return conveyorStatus;
    }

    /**
     * Setter for the conveyorStatus property
     * @param conveyorStatus
     */
    public void setConveyorStatus(int conveyorStatus) {
        this.conveyorStatus = conveyorStatus;
    }

    /**
     * Getter for the fruitVacuumCollisionStatus property
     * @return fruitVacuumCollisionStatus
     */
    public boolean isFruitVacuumCollisionStatus() {
        return fruitVacuumCollisionStatus;
    }

    /**
     * Setter for the fruitVacuumCollisionStatus property
     * @param fruitVacuumCollisionStatus
     */
    public void setFruitVacuumCollisionStatus(boolean fruitVacuumCollisionStatus) {
        this.fruitVacuumCollisionStatus = fruitVacuumCollisionStatus;
    }

    /** 
     * Getter for the fruitSeesawCollisionStatus property
     * @return fruitSeesawCollisionStatus
     */
    public boolean isFruitSeesawCollisionStatus() {
        return fruitSeesawCollisionStatus;
    }

    /**
     * Setter for the fruitSeesawCollisionStatus property
     * @param fruitSeesawCollisionStatus
     */
    public void setFruitSeesawCollisionStatus(boolean fruitSeesawCollisionStatus) {
        this.fruitSeesawCollisionStatus = fruitSeesawCollisionStatus;
    }

    /** 
     * Getter for the fruitLaserCollisionStatus property
     * @return fruitLaserCollisionStatus
     */
    public boolean isFruitLaserCollisionStatus() {
        return fruitLaserCollisionStatus;
    }

    /** 
     * Setter for the fruitLaserCollisionStatus property
     * @param fruitLaserCollisionStatus
     */
    public void setFruitLaserCollisionStatus(boolean fruitLaserCollisionStatus) {
        this.fruitLaserCollisionStatus = fruitLaserCollisionStatus;
    }

    /**
     * Getter for the fruitCrusherCollisionStatus property
     * @return fruitCrusherCollisionStatus
     */
    public boolean isFruitCrusherCollisionStatus() {
        return fruitCrusherCollisionStatus;
    }

    /**
     * Setter for the fruitCrusherCollisionStatus property
     * @param fruitCrusherCollisionStatus
     */
    public void setFruitCrusherCollisionStatus(boolean fruitCrusherCollisionStatus) {
        this.fruitCrusherCollisionStatus = fruitCrusherCollisionStatus;
    }

    /**  
     * Getter for the fruitScannerCollisionStatus property
     * @return fruitScannerCollisionStatus
     */
    public boolean isFruitScannerCollisionStatus() {
        return fruitScannerCollisionStatus;
    }

    /**
     * Setter for the fruitScannerCollisionStatus property
     * @param fruitScannerCollisionStatus
     */
    public void setFruitScannerCollisionStatus(boolean fruitScannerCollisionStatus) {
        this.fruitScannerCollisionStatus = fruitScannerCollisionStatus;
    }

    /**
     * Getter for the fruitGateCollisionStatus property
     * @return fruitGateCollisionStatus
     */
    public boolean isFruitGateCollisionStatus() {
        return fruitGateCollisionStatus;
    }

    /**
     * Setter for the fruitGateCollisionStatus property
     * @param fruitGateCollisionStatus
     */
    public void setFruitGateCollisionStatus(boolean fruitGateCollisionStatus) {
        this.fruitGateCollisionStatus = fruitGateCollisionStatus;
    }

    /**
     * Getter for the stgateStatusatus property
     * @return gateStatus
     */
    public int getGateStatus() {
        return gateStatus;
    }

    /**
     * Setter for the gateStatus property
     * @param gateStatus
     */
    public void setGateStatus(int gateStatus) {
        this.gateStatus = gateStatus;
    }

    /**
     * Getter for the fruitBucketCollisionStatus property
     * @return fruitBucketCollisionStatus
     */
    public boolean isFruitBucketCollisionStatus() {
        return fruitBucketCollisionStatus;
    }

    /**
     * Setter for the fruitBucketCollisionStatus property
     * @param fruitBucketCollisionStatus
     */
    public void setFruitBucketCollisionStatus(boolean fruitBucketCollisionStatus) {
        this.fruitBucketCollisionStatus = fruitBucketCollisionStatus;
    }

    /**
     * Getter for the seesawStatus property
     * @return seesawStatus
     */
    public int getSeesawStatus() {
        return seesawStatus;
    }

    /**
     * Setter for the seesawStatus property
     * @param seesawStatus
     */
    public void setSeesawStatus(int seesawStatus) {
        this.seesawStatus = seesawStatus;
    }

    /**
     * Getter for the vacuumStatus property
     * @return vacuumStatus
     */
    public int getVacuumStatus() {
        return vacuumStatus;
    }

    /**
     * Setter for the vacuumStatus property
     * @param vacuumStatus
     */
    public void setVacuumStatus(int vacuumStatus) {
        this.vacuumStatus = vacuumStatus;
    }
}
