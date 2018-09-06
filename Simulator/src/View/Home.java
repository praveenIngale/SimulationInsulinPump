/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Model.DBconn;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.Timer;

/**
 *
 * @author Praveen Ingale
 */
public class Home extends javax.swing.JPanel {

    /**
     * Creates new form Home
     */
    int glucose;
    double insulin_dose;
    private static int reading0 = 0;
    private static int reading1 = 0;
    private static int reading2 = 0;
    private static int remainingInsulin = 100;
    private static int reservoirCapacity = 100;
    private static int maxDailyDose = 25;
    private static int maxSingleDose = 4;
    private static int minDose = 1;
    private static int safeMin = 60;
    private static int safeMax = 140;
    private static double compDose = 0;
    DBconn db;
    Timer timer;
    String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
    public Home() {

        db=new DBconn();
        initComponents();
        check_int();
        
        insulin_dose();
        blood_glucose_classify();
        disp_int_check();
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
    }

    private void check_int() {
        if (Controller.show_int) {
            jLabel2.setVisible(true);
            jLabel3.setVisible(true);
            jLabel4.setVisible(true);
            jLabel5.setVisible(true);
        } else {
            jLabel2.setVisible(false);
            jLabel3.setVisible(false);
            jLabel4.setVisible(false);
            jLabel5.setVisible(false);
        }
    }

    public void setLabel_bg() {

        jLabel2.setText(String.valueOf(Controller.glucose));

    }

    public void blood_glucose_classify() {
        
          ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
// glucose = Integer.parseInt(jLabel2.getText());
        glucose = Controller.glucose;
        //Classify the glucose levels 
        if (glucose < 60) {
            jLabel1.setText("CRITICALLY LOW.  TAKE FOOD");
            jLabel1.setBackground(Color.red);
        } else if (glucose >= 60 && glucose < 70) {
            jLabel1.setText("LOW.  TAKE GLUCOSE / FOOD");
            jLabel1.setBackground(Color.yellow);
        } else if (glucose >= 70 && glucose < 100) {
            jLabel1.setText("SAFE");
            Controller.clear_notification(1);
            jLabel1.setBackground(Color.green);
        } else if (glucose >= 100 && glucose < 130) {
            jLabel1.setText("HIGH");
            jLabel1.setBackground(Color.yellow);
        } else if (glucose >= 130) {
            jLabel1.setText("CRITICALLY HIGH");
            jLabel1.setBackground(Color.red);
        }
               

            }

        };
        timer = new Timer(1000, listener);
        timer.start();
       
    }

    //Added by Praveen Ingale
    public int insulin_dose() {
        reading0 = safeMin;
        reading1 = safeMax;
        glucose = Controller.glucose;
            jLabel2.setText(String.valueOf(glucose));
        reading2 = glucose;
        if (glucose < safeMin) {
            compDose = 0.0;
            // If the blood glucose is too low no insulin is injected into patient body
            jLabel5.setText(String.valueOf(compDose));
        } else if (glucose >= safeMin && glucose <= safeMax) {
            if (reading2 <= reading1) {
                compDose = 0;
                jLabel5.setText(String.valueOf(compDose));
            } else {
                // If rate of increase is falling
                if ((reading2 - reading1) < (reading1 - reading0)) {
                    compDose = 0;
                    jLabel5.setText(String.valueOf(compDose));
                } // If rate of increase is increasing
                else if ((reading2 - reading1) >= (reading1 - reading0)) {
                    // If dose is rounded to zero, deliver the min dose
                    if ((reading2 - reading1) / 4 == 0) {
                        //set the amount to deliver to the min dose
                        compDose = minDose;
                        jLabel5.setText(String.valueOf(compDose));
                    } else if ((reading2 - reading1) / 4 > 0) {
                        //Set the amount to deliver
                        compDose = (reading2 - reading1) / 4;
                        jLabel5.setText(String.valueOf(compDose));
                    }

                }
            }
        } else if (glucose > safeMax) {
            // If Sugar level increasing
            if (reading2 > reading1) {
                // If dose is rounded to zero, deliver the min dose
                if ((reading2 - reading1) / 4 == 0) {
                    compDose = minDose;
                    jLabel5.setText(String.valueOf(compDose));
                } else if ((reading2 - reading1) / 4 > 0) {
                    //Set the amount to deliver
                    compDose = (reading2 - reading1) / 4;
                    jLabel5.setText(String.valueOf(compDose));
                }
            } // If the Sugar level is stable
            else if (reading2 == reading1) {
                compDose = minDose;
                jLabel5.setText(String.valueOf(compDose));
            } // If the Sugar level is falling
            else if (reading2 < reading1) {
                // If rate of decrease increasing
                if ((reading2 - reading1) <= (reading1 - reading0)) {
                    compDose = 0;
                    jLabel5.setText(String.valueOf(compDose));
                } // If rate of decrease decreasing
                else {
                    compDose = minDose;
                    jLabel5.setText(String.valueOf(compDose));
                }
            }

        }
        blood_glucose_classify();
        if(compDose != 0)
        {
            if(simulator.bolus_flag)
            {
                db.insert_bolus("Bolus",timeStamp,Controller.getDate1(), Controller.glucose, compDose);
                 jLabel6.setVisible(true);
                jLabel6.setText("BOLUS");
                jLabel7.setVisible(true);
                jLabel7.setText("Manual Mode");
                
            }
            else
            {
                db.insert_bolus("Basal",timeStamp,Controller.getDate1(), Controller.glucose, compDose);
                 jLabel6.setVisible(true);
                  jLabel6.setText("BASAL");
                  jLabel7.setVisible(true);
                jLabel7.setText("Automatic Mode");
            }
        }
        //Adjust the reading values to accomodate for the new reading
        reading1 = reading2;
        reading0 = reading1;
        return (int) compDose;
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(400, 201));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });

        jLabel1.setBackground(java.awt.Color.green);
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SAFE");
        jLabel1.setOpaque(true);

        jLabel2.setText("250");

        jLabel3.setText("Insulin Dosage Value");

        jLabel4.setText("Blood Glucose Value");

        jLabel5.setText("5.5");

        jLabel7.setText("Automatic Mode");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(81, 81, 81))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(51, 51, 51)
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel6)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
        disp_int_check();
    }//GEN-LAST:event_formFocusGained

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange
        // TODO add your handling code here:
        disp_int_check();
    }//GEN-LAST:event_formPropertyChange

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        disp_int_check();        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables

    private void disp_int_check() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (Controller.isShow_int()) {
            jLabel2.setVisible(true);
            jLabel5.setVisible(true);
            jLabel4.setVisible(true);
            jLabel3.setVisible(true);
        } else {
            jLabel2.setVisible(false);
            jLabel5.setVisible(false);
            jLabel4.setVisible(false);
            jLabel3.setVisible(false);
        }
    }
}
