/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import blueballfactory.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * This is the main Graphical User Interface
 * @author grayj26
 */
public class MainScreen extends javax.swing.JFrame {    
    //Declare global variables
    static Color bgColor = new Color(255, 255, 255);
    /**
     * Main canvas of the GUI
     */
    static volatile public Graphics2D canvas;
    
    //Lock
    static volatile Lock masterlock = new ReentrantLock();
    
    //Fruit
    /**
     * ArrayList of fruit currently in the Item Factory
     */
    static public volatile ArrayList<Fruit> fruit = new ArrayList<Fruit>();
    static public volatile Fruit fruit1, fruit2;
    
    //Components
    static public volatile Conveyor conveyor1, conveyor2, conveyor3, conveyor4, conveyor5, conveyor6;
    static public volatile Vacuum vacuum;
    static public volatile Seesaw seesaw;
    static public volatile Laser laser;
    static public volatile Crusher crusher;
    static public volatile Gate bananaGate, appleGate, orangeGate;
    static public volatile Bucket bananaBucket, appleBucket, orangeBucket, trashBucket;
    
    //Arrays
    /**
     * Array of conveyors in the Item Factory
     */
    static public Conveyor[] conveyors;

    /**
     * Array of gates in the Item Factory
     */
    static public Gate[] gates;

    /**
     * Array of buckets in the Item Factory
     */
    static public Bucket[] buckets;
    
    /** Creates new form MainScreen
     * @throws java.io.IOException */
    public MainScreen() throws IOException {
        initComponents();
        //Force JPanel (main_screen) as graphics2D instance for drawing graphical shapes
        mainCanvas.setForeground(bgColor);
        mainCanvas.setBackground(bgColor);
        canvas = (Graphics2D) mainCanvas.getGraphics();
        canvas.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainCanvas = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        stopBtn = new javax.swing.JButton();
        quitBtn = new javax.swing.JButton();
        createBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainCanvas.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout mainCanvasLayout = new javax.swing.GroupLayout(mainCanvas);
        mainCanvas.setLayout(mainCanvasLayout);
        mainCanvasLayout.setHorizontalGroup(
            mainCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mainCanvasLayout.setVerticalGroup(
            mainCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );

        addBtn.setText("Add Random Fruit");
        addBtn.setEnabled(false);
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        stopBtn.setText("Stop");
        stopBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopBtnActionPerformed(evt);
            }
        });

        quitBtn.setText("Quit");
        quitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBtnActionPerformed(evt);
            }
        });

        createBtn.setLabel("Create Factory");
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(createBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(stopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(quitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        createBtn.getAccessibleContext().setAccessibleName("Create Factory");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void quitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitBtnActionPerformed
        //Display message dialog when clicking button                                                
        int option = JOptionPane.showConfirmDialog(this, "Do you want to quit program?");
        if (option == JOptionPane.YES_OPTION) { 
            System.exit(0);
        }
    }//GEN-LAST:event_quitBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        int canvasWidth = mainCanvas.getWidth();//JPanel or canvas width
        int canvasHeight = mainCanvas.getHeight();//JPanel or canvas heigth
        
        Random rand = new Random();
        int[] boundary = {0, 0, canvasWidth, canvasHeight};
        int speedX = 0;
        int speedY = 10;
        
        //Create a random fruit
        switch(rand.nextInt(3)){
            case 0://Banana
                Banana newBanana = new Banana(30, 0, 30, 30, canvas, true, masterlock);
        
                //Set properties
                newBanana.setBoundary(boundary);
                newBanana.setSpeedX(speedX);    
                newBanana.setSpeedY(speedY);
                newBanana.setBgColor(bgColor);
                
                //Create new thread
                Thread newBananaThread = new Thread(newBanana);

                //Add circle to circles array
                fruit.add(newBanana);

                //3: Start the the thread;
                newBananaThread.start();
                break;
            case 1://Apple
                Apple newApple = new Apple(30, 0, 30, 30, canvas, true, masterlock);
        
                //Set properties
                newApple.setBoundary(boundary);
                newApple.setSpeedX(speedX);
                newApple.setSpeedY(speedY);
                newApple.setBgColor(bgColor);

                //Create new thread
                Thread newAppleThread = new Thread((Runnable) newApple);

                //Add circle to circles array
                fruit.add(newApple);

                //3: Start the the thread;
                newAppleThread.start();
                break;
            case 2://Orange
                Orange newOrange = new Orange(30, 0, 30, 30, canvas, true, masterlock);
        
                //Set properties
                newOrange.setBoundary(boundary);
                newOrange.setSpeedX(speedX);
                newOrange.setSpeedY(speedY);
                newOrange.setBgColor(bgColor);

                //Create new thread
                Thread newOrangeThread = new Thread((Runnable) newOrange);

                //Add circle to circles array
                fruit.add(newOrange);

                //3: Start the the thread;
                newOrangeThread.start();
                break;
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void stopBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopBtnActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Do you want to stop factory?");
        if (option == JOptionPane.YES_OPTION) {
            //Stop all threads.
            for (Fruit fruit : fruit) {
                fruit.stop();
            }
            fruit.clear();
            try {
            //Delay 100ms
            Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
            createFactory();
        }
    }//GEN-LAST:event_stopBtnActionPerformed

    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBtnActionPerformed
        createFactory();
        addBtn.setEnabled(rootPaneCheckingEnabled);
        createBtn.setEnabled(!rootPaneCheckingEnabled);
    }//GEN-LAST:event_createBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    
                    new MainScreen().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
        Thread checkCollisionThread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                    }

                    //Create a thread to check the collision amongst objects
                    //
                    if (fruit.size() < 2) {
                        //do nothing
                    } else {
                        //Check the collision
                        for (int i = 0; i < fruit.size() - 1; i++) {
                            fruit1 = fruit.get(i);
                            
                            for (int j = i + 1; j < fruit.size(); j++) {
                                fruit2 = fruit.get(j);
                                boolean collision = false;
                                collision = fruit1.checkCollision(fruit2);
                                
                                //If collision happens, do something ....
                                if(fruit1.getTopLeftY() < fruit2.getTopLeftY() || (fruit1.getTopLeftY() == fruit2.getTopLeftY() && fruit1.getTopLeftX() < fruit2.getTopLeftX())){
                                    fruit1.setFruitFruitCollisionStatus(collision);                                        
                                } else {
                                    fruit2.setFruitFruitCollisionStatus(collision);
                                }
                            }
                        }
                    }
                    
                    //Collision between item and conveyorBelt
                    if (fruit.size() < 1 || conveyors.length < 1) {
                        //do nothing
                    } else {
                        //Check the collision
                        for (int i = 0; i < fruit.size(); i++) {
                            //
                            for(int j = 0; j < conveyors.length; j++){
                                boolean collision = false;
                                collision = fruit.get(i).checkCollision(conveyors[j]);                        
                                //If collision happens, do something ....
                                fruit.get(i).setFruitConveyorCollisionStatus(collision);
                                
                                int collisionStatus = fruit.get(i).checkCollisionStatus(conveyors[j]);
                                //Set collision status
                                fruit.get(i).setConveyorStatus(collisionStatus);
                                
                                if(collision){
                                    break;
                                }
                            }
                        }
                    }
                    
                    //Collision between item and vacuum
                    if (fruit.size() < 1 || vacuum == null) {
                        //do nothing
                    } else {
                        //Check the collision
                        for (int i = 0; i < fruit.size(); i++) {
                            boolean collision = false;
                            collision = fruit.get(i).checkCollision(vacuum);
                            //If collision happens, do something ....
                            fruit.get(i).setFruitVacuumCollisionStatus(collision);
                            
                            int collisionStatus = fruit.get(i).checkCollisionStatus(vacuum);
                                //Set collision status
                                fruit.get(i).setVacuumStatus(collisionStatus);
                                if(collision && collisionStatus == 2){
                                    fruit.get(i).setExit(true);
                                    fruit.remove(i);
                                }
                        }
                    }
                    
                    //Collision between item and seesaw
                    if (fruit.size() < 1 || seesaw == null) {
                        //do nothing
                    } else {
                        //Check the collision
                        for (int i = 0; i < fruit.size(); i++) {
                            boolean collision = false;
                            collision = fruit.get(i).checkCollision(seesaw);
                            //If collision happens, do something ....
                            fruit.get(i).setFruitSeesawCollisionStatus(collision);
                            
                            int collisionStatus = fruit.get(i).checkCollisionStatus(seesaw);
                            //Set collision status
                            fruit.get(i).setSeesawStatus(collisionStatus);
                        }
                    }
                    
                    //Collision between item and laser
                    if (fruit.size() < 1 || laser == null) {
                        //do nothing
                    } else {
                        //Check the collision
                        for (int i = 0; i < fruit.size(); i++) {
                            boolean collision = false;
                            collision = fruit.get(i).checkCollision(laser);
                            //If collision happens, do something ....
                            fruit.get(i).setFruitLaserCollisionStatus(collision);
                        }
                    }
                    
                    //Collision between item and crusher
                    if (fruit.size() < 1 || crusher == null) {
                        //do nothing
                    } else {
                        //Check the collision
                        for (int i = 0; i < fruit.size(); i++) {
                            boolean collision = false;
                            collision = fruit.get(i).checkCollision(crusher);
                            //If collision happens, do something ....
                            fruit.get(i).setFruitCrusherCollisionStatus(collision);
                        }
                    }
                    
                  //Collision between item and gate
                    if (fruit.size() < 1 || gates.length < 1) {
                        //do nothing
                    } else {
                        //Check the collision
                        for (int i = 0; i < fruit.size(); i++) {
                            //
                            for(int j = 0; j < gates.length; j++){
                                boolean collision = false;
                                collision = fruit.get(i).checkCollision(gates[j]);
                                //If collision happens, do something ....
                                //Moves along top of gate if it is the wrong fruit or it is trash.
                                if((collision && !fruit.get(i).getClass().toString().contains(gates[j].getType())) || fruit.get(i).getStatus() == false){
                                    fruit.get(i).setFruitGateCollisionStatus(collision);
                                    break;
                                }
                            }
                        }
                    }
                    
                    //Collision between item and bucket
                    if (fruit.size() < 1 || buckets.length < 1) {
                        //do nothing
                    } else {
                        //Check the collision
                        for (int i = 0; i < fruit.size(); i++) {
                            //
                            for(int j = 0; j < buckets.length; j++){
                                boolean collision = false;
                                collision = fruit.get(i).checkCollision(buckets[j]);
                                //If collision happens, do something ....
                                fruit.get(i).setFruitBucketCollisionStatus(collision);

                                if(collision){
                                    fruit.get(i).setExit(true);
                                    fruit.remove(i);
                                    buckets[j].setCount(buckets[j].getCount() + 1);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        };
        checkCollisionThread.start();
    }
    
    /**
     * This creates the factory components within the Item Factory
     */
    static public void createFactory(){        
        //Create, add and draw conveyors
        conveyor1 = new Conveyor(0, 80, 550, 30, canvas, true, masterlock);
        conveyor2 = new Conveyor(100, 225, 652, 30, canvas, false, masterlock);
        conveyor3 = new Conveyor(0, 325, 225, 30, canvas, true, masterlock);
        conveyor4 = new Conveyor(300, 325, 75, 30, canvas, true, masterlock);
        conveyor5 = new Conveyor(450, 325, 75, 30, canvas, true, masterlock);
        conveyor6 = new Conveyor(600, 325, 75, 30, canvas, true, masterlock);
        
        conveyors = new Conveyor[]{conveyor1, conveyor2, conveyor3, conveyor4, conveyor5, conveyor6};
        
        for(Conveyor conveyor : conveyors){
            Thread newConveyorThread = new Thread(conveyor);
            newConveyorThread.start();
        }
        
        //Create, add and draw machines
        vacuum = new Vacuum(400, 0, 50, 25, canvas, false, masterlock);
        seesaw = new Seesaw (550, 120, 50, 50, canvas, true, masterlock);
        laser = new Laser (672, 195, 80, 30, canvas, false, masterlock);
        crusher = new Crusher(200, 110, 25, 50, canvas, false, masterlock);
        Thread vacuumThread = new Thread(vacuum);
        vacuumThread.start();
        Thread seesawThread = new Thread(seesaw);
        seesawThread.start();
        Thread laserThread = new Thread(laser);
        laserThread.start();
        Thread crusherThread = new Thread(crusher);
        crusherThread.start();
        
        
        //Create, add and draw scanner/gates
        bananaGate = new Gate (225, 325, 75, 30, canvas, "Banana", false, masterlock);
        appleGate = new Gate (375, 325, 75, 30, canvas, "Apple", false, masterlock);
        orangeGate = new Gate (525, 325, 75, 30, canvas, "Orange", false, masterlock);
        gates = new Gate[]{bananaGate, appleGate, orangeGate};
        
        for(Gate gate : gates){
            Thread newGateThread = new Thread(gate);
            newGateThread.start();
        }
        
        //Create, add and draw buckets
        bananaBucket = new Bucket(225, 355, 75, 40, canvas, "Banana", masterlock);
        appleBucket = new Bucket(375, 355, 75, 40, canvas, "Apple", masterlock);
        orangeBucket = new Bucket(525, 355, 75, 40, canvas, "Orange", masterlock);
        trashBucket = new Bucket(675, 355, 75, 40, canvas, "Trash", masterlock);
        buckets = new Bucket[]{bananaBucket, appleBucket, orangeBucket, trashBucket};
        
        for(Bucket bucket : buckets){
            Thread newBucketThread = new Thread(bucket);
            newBucketThread.start();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton createBtn;
    private javax.swing.JPanel mainCanvas;
    private javax.swing.JButton quitBtn;
    private javax.swing.JButton stopBtn;
    // End of variables declaration//GEN-END:variables

}
