/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueballfactory;

/**
 * This is the IMoveAnd Draw interface
 * @author grayj26
 */
public interface IMoveAndDraw {

    /**
     *
     * @param speedX
     * @param speedY
     */
    public void move(int speedX, int speedY);

    /**
     *
     */
    public void draw();
}
