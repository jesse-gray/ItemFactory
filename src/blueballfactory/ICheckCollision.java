/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueballfactory;

/**
 * This is the ICheckCollision interface
 * @author grayj26
 */
public interface ICheckCollision {
    
    /**
     *
     * @param toCheck
     * @return
     */
    public boolean checkCollision(Fruit toCheck);
    
    /**
     *
     * @param toCheck
     * @return
     */
    public boolean checkCollision(Conveyor toCheck);
    
    /**
     *
     * @param toCheck
     * @return
     */
    public boolean checkCollision(Vacuum toCheck);
    
    /**
     *
     * @param toCheck
     * @return
     */
    public boolean checkCollision(Seesaw toCheck);
    
    /**
     *
     * @param toCheck
     * @return
     */
    public boolean checkCollision(Laser toCheck);
    
    /**
     *
     * @param toCheck
     * @return
     */
    public boolean checkCollision(Crusher toCheck);
    
    /**
     *
     * @param toCheck
     * @return
     */
    public boolean checkCollision(Gate toCheck);
    
    /**
     *
     * @param toCheck
     * @return
     */
    public boolean checkCollision(Bucket toCheck);
    
    /**
     *
     */
    public void checkBoundaryCollision(); 
}
