/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestCase;

import blueballfactory.Apple;
import blueballfactory.Vacuum;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jesse
 */
public class TestCase3 extends javax.swing.JFrame {
    //Declare global variables
    static Color bgColor = new Color(255, 255, 255);
    static volatile public Graphics2D canvas;
    static volatile Lock masterlock = new ReentrantLock();

    /**
     * Creates new form TestCase3
     */
    public TestCase3() throws IOException {
        initComponents();
        //Force JPanel (main_screen) as graphics2D instance for drawing graphical shapes
        TestCase3.setForeground(bgColor);
        TestCase3.setBackground(bgColor);
        canvas = (Graphics2D) TestCase3.getGraphics();
        canvas.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TestCase3 = new javax.swing.JPanel();
        runBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        runBtn.setText("Run");
        runBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TestCase3Layout = new javax.swing.GroupLayout(TestCase3);
        TestCase3.setLayout(TestCase3Layout);
        TestCase3Layout.setHorizontalGroup(
            TestCase3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TestCase3Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(runBtn)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        TestCase3Layout.setVerticalGroup(
            TestCase3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TestCase3Layout.createSequentialGroup()
                .addContainerGap(250, Short.MAX_VALUE)
                .addComponent(runBtn)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TestCase3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TestCase3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void runBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runBtnActionPerformed
        int canvasWidth = TestCase3.getWidth();//JPanel or canvas width
        int canvasHeight = TestCase3.getHeight();//JPanel or canvas heigth
        int[] boundary = {0, 0, canvasWidth, canvasHeight};

        //---Vacuum---
        Vacuum newVacuum = new Vacuum(canvasWidth/2, 0, 50, 25, canvas, false, masterlock);

        //Create new thread
        Thread newVacuumThread = new Thread(newVacuum);
        newVacuumThread.start();

        //---Apple---
        Apple newApple = new Apple(canvasWidth/2, 70, 30, 30, canvas, true, masterlock);

        //Set properties
        newApple.setBoundary(boundary);
        newApple.setSpeedX(0);
        newApple.setSpeedY(0);
        newApple.setBgColor(bgColor);

        //Create new thread
        Thread newAppleThread = new Thread(newApple);

        //3: Start the the thread;
        newAppleThread.start();

        Thread checkCollisionThread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                    }
                    boolean collision = false;
                    collision = newApple.checkCollision(newVacuum);
                    //If collision happens, do something ....
                    newApple.setFruitVacuumCollisionStatus(collision);

                    int collisionStatus = newApple.checkCollisionStatus(newVacuum);
                    //Set collision status
                    newApple.setVacuumStatus(collisionStatus);
                    if(collision && collisionStatus == 2){
                        newApple.setExit(true);
                    }
                }
            }
        };
        checkCollisionThread.start();
    }//GEN-LAST:event_runBtnActionPerformed

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
            java.util.logging.Logger.getLogger(TestCase3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestCase3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestCase3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestCase3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TestCase3().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(TestCase3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TestCase3;
    private javax.swing.JButton runBtn;
    // End of variables declaration//GEN-END:variables
}
