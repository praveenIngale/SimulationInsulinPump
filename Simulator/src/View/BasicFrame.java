/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import static Controller.Controller.index;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Praveen Ingale
 */
public class BasicFrame extends javax.swing.JFrame {

    CardLayout cl;
    //String[] cards = {"home", "menu", "set", "his", "date", "boset", "mode", "baset", "bowiz", "bawiz", "graph1"};
    Timer timer;
    Timer timer1;
    Timer timer2;
    int counter = 100;
    boolean insulin_flag=false;

    /**
     * Creates new form BasicFrame
     */
    public BasicFrame() {

        configure_looknfeel();

        initComponents();
        jPanel2.setLayout(new CardLayout());
        jLabel2.setVisible(true);
        //default card selection
        // select_card();
        jButton2.setVisible(false);
        //default card displayed
        insulin_check();
        clock();
        start_battery();
        clear_notification(9);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Insulin Pump Simulator");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("jLabel1");

        jProgressBar1.setForeground(new java.awt.Color(0, 0, 255));
        jProgressBar1.setValue(400);
        jProgressBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jProgressBar1MouseClicked(evt);
            }
        });

        jProgressBar2.setForeground(new java.awt.Color(0, 204, 0));
        jProgressBar2.setValue(100);
        jProgressBar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jProgressBar2MouseClicked(evt);
            }
        });

        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addComponent(jLabel5)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Menu");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText("Supervisor");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(100, 100, 100)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        if (index == 0) {
            index = 1;
        } else {
            index = 0;
        }
        Controller.change_card();
        
          //  jToggleButton1.setEnabled(Controller.supervisor);

    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

        find_parent();
        select_card();
        if (index == 0) {
            jButton1.setText("Menu");
        } else {
            jButton1.setText("Home");
        }
        //jToggleButton1.setEnabled(Controller.supervisor);

    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jProgressBar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProgressBar2MouseClicked

        // TODO add your handling code here:
        jLabel5.setVisible(false);
        jProgressBar2.setValue(100);
        counter = 100;
        start_battery();
    }//GEN-LAST:event_jProgressBar2MouseClicked

    private void jProgressBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProgressBar1MouseClicked
        // TODO add your handling code here:
        insulin_flag = true;
        jLabel2.setVisible(false);
        Controller.volume=100;
        jProgressBar1.setValue(100);
        jProgressBar1.setForeground(Color.BLUE);
    }//GEN-LAST:event_jProgressBar1MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // Supervisor
        if (!Controller.supervisor) {
            Controller.index = 11;
            Controller.change_card();
        } else {
            Controller.index = 0;
            Controller.change_card();
            Controller.supervisor = false;
        }
        if(Controller.supervisor)
            jButton3.setText("Lock");
        else
            jButton3.setText("Supervisor");
    }//GEN-LAST:event_jButton3MouseClicked

    private void clock() {
        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                jLabel1.setText(Controller.getDate());

            }

        };
        timer = new Timer(1000, listener);
        timer.start();
    }

    private void start_battery() {
        // jLabel2.setVisible(false);

        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                counter--;

                jProgressBar2.setValue(counter);

                if (counter < 98) {

                    notification("Low Battery. Recharge", 0);

                    jProgressBar2.setForeground(Color.red);

                    //timer1.stop();
                } else {
                    clear_notification(3);
                }
            }
        };
        timer1 = new Timer(1000, listener);
        timer1.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    // End of variables declaration//GEN-END:variables

    @SuppressWarnings("empty-statement")
    public void select_card() {
        if (index == 0) {
                jButton2.setVisible(false);
                jButton1.setText("Menu");
            } else {
                jButton2.setVisible(true);
                jButton1.setText("Home");
            }
            
        if (Controller.isGrahpics()) {
            //Transition attempt
            //Hide
            Point location = jPanel2.getLocation();
            int i;

            i = 300;

            jPanel2.setLocation(i, location.y);

            //End of hide
            //Show

            for (; i > location.x; i--) {

                //Delay timer
                int delay = 50;

                final int j = i;
                final Point location1 = location;
                Timer timer3 = new Timer(delay, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jPanel2.setLocation(j, location1.y);

                    }
                });
                timer3.setRepeats(false);
                timer3.start();
                //Delay timer end
            }
        }
        cl = (CardLayout) jPanel2.getLayout();
        cl.show(jPanel2, Controller.cards[index]);
    }

    private void find_parent() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        switch (index) {
            case 1:
            case 11:
                index = 0;
                break;
            case 2:
            case 3:
            case 14:
                index = 1;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 12:
            case 13:
                index = 2;
                break;
            case 8:
            case 9:
            case 10:
                index = 3;
                break;

        }
    }

    private void configure_looknfeel() {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BasicFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>   
    }

    public void addcard(Component x, String y) {
        jPanel2.add(x, y);

    }

    public void auth_fail() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        jButton3.setText("Supervisor");
    }

    public void auth_succ() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        jButton3.setText("Lock");
    }

    public void notification(String string, int x) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        switch (0) {
            case 0:
                jLabel5.setVisible(true);
                jLabel5.setText("Baterry Low");
                //System.out.println("notification "+string+x);
                break;
            case 1:
                jLabel3.setVisible(true);
                jLabel3.setText(string);
                //System.out.println("notification "+string+x);
                break;
            case 2:
                jLabel2.setVisible(true);
                jLabel2.setText("Reservoir Low");
                //System.out.println("notification "+string+x);
                break;
            case 3:
                jLabel5.setVisible(true);
                jLabel5.setText(string);
                break;
            
        }
    }
    public void clear_notification(int x) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("notification "+x);
        switch (x) {
            case 0:
                jLabel2.setVisible(false);
               // jLabel2.setText("Baterry Low");
                break;
            case 1:
                jLabel3.setVisible(false);
            //    jLabel3.setText(string);
                break;
            case 2:
                jLabel4.setVisible(false);
              //  jLabel4.setText(string);
                break;
            case 3:
                jLabel5.setVisible(false);
                //jLabel5.setText(string);
              // jLabel5.setText("Baterry Low");
                break;
            
        }
    }

    public void insulin_check() {
       
             jProgressBar1.setValue(Controller.volume); 
        
       
// glucose = Integer.parseInt(jLabel2.getText());
       if(Controller.volume<65)
        {
         jProgressBar1.setForeground(Color.red);
         jProgressBar1.setBackground(Color.red);
         jLabel2.setVisible(true);
         jLabel2.setText("Low Volume");
        }
        else
        {
         jProgressBar1.setForeground(Color.BLUE);
         jProgressBar1.setBackground(Color.BLUE);
        }
    
  }
}
