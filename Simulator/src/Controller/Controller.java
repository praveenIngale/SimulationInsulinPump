/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import static Controller.Controller.home;
import Model.*;
import View.*;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author Praveen Ingale
 */
public class Controller {

    /**
     *
     */
    public static DBconn db;
    public static int glucose;
    public static boolean grahpics = false, show_int = false, supervisor = false,injection = true;

    public static void main(String[] args) throws IOException {

        simulator s = new simulator();
        glucose = s.getGlucose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new BasicFrame();

                GraphicsConfiguration gc = frame.getGraphicsConfiguration();
                Rectangle bounds = gc.getBounds();
                Dimension size = frame.getPreferredSize();
                frame.setLocation(50, (int) ((bounds.height / 2) - (size.getHeight() / 2) - 200));
                frame.setVisible(true);
                add_cards();

                change_card();
                simulator1 = new simulator();

                simulator1.setLocation((int) ((size.getWidth() + 100)), (int) ((bounds.height / 2) - (size.getHeight() / 2) - 200));
                simulator1.setVisible(true);
                
            }

            private void add_cards() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                home = new Home();
                start = new Startup();
                menu = new Menu();
                mode = new Mode();
                baset = new basal_set();
                boset = new bolus_set();
                bawiz = new basal_wizard();
                bowiz = new bolus_wizard();
                date = new datetime();
                graph1 = new graph();
                his = new history();
                set = new settings();
                sec_set1 = new sec_set();
                dis_set1 = new dis_set();
                auth1 = new auth();
                about1 =new about();

                frame.addcard(start, "start");
                frame.addcard(home, "home");
                frame.addcard(menu, "menu");
                frame.addcard(mode, "mode");
                frame.addcard(baset, "baset");
                frame.addcard(bawiz, "bawiz");
                frame.addcard(boset, "boset");
                frame.addcard(bowiz, "bowiz");
                frame.addcard(date, "date");
                frame.addcard(graph1, "graph1");
                frame.addcard(his, "his");
                frame.addcard(set, "set");
                frame.addcard(auth1, "auth1");
                frame.addcard(sec_set1, "sec_set1");
                frame.addcard(dis_set1, "dis_set1");
                frame.addcard(about1,"about1");
            }
        });
        db = new DBconn();

        int delay = 9000;

        Timer timer3;
        timer3 = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monitor();

            }
        });
        timer3.setRepeats(true);
        timer3.start();

    }
 private static void monitor()
{
    
    glucose = simulator1.getGlucose();
    System.out.println("Monitoring: Value found: "+ glucose);
    
    insulin = home.insulin_dose();
    
    if(injection&&volume>0){
        
        frame.clear_notification(1);
       if(volume>insulin){
           volume-=insulin;
        simulator1.inject(insulin);
       
       }
       else{
           simulator1.inject(volume);
           insulin-=volume;
           volume = 0;
           manualinject();
       }
     frame.insulin_check();
    }
    else {
        manualinject();
    }
    home.blood_glucose_classify();
    
}
 private static void manualinject(){
     
     if(insulin >0)
        frame.notification(("Inject "+insulin+" units of Insulin"),1);
 }
    /**
     *
     * @param s
     * @param x
     * @param y
     */
    
    public static void bolus_in(String S2,String s1,String s, int x, Double y) {

        db.insert_bolus(S2,s1,s, x, y);
        //index = 2;

        //change_card();

    }

    public static String get_pwd()
    {
        return db.getpassword();
    }
    public static String get_sup_pwd()
    {
        return db.getsuperpassword();
    }
    public static void save_pwd(String pwd_new)
    {
        db.savepassword(pwd_new);
                
    }
    public static void getchart() {
        db.GenerateChart();
   // index = 2;

        //   change_card();
    }

    public static void basal_in(String a, String s, int x, float y) {

        db.insert_basal(a, s, x, y);
        index = 2;

        change_card();

    }

  /* public static void fillbolusdata()
     {
     db.filldata();
     }*/

    public static String getDate() {
        dnow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss a");
        return ft.format(dnow);
    }
    public static String getDate1() {
        dnow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm a");
        return ft.format(dnow);
    }
      

    public static void change_card() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        frame.select_card();
    }

    public static boolean isGrahpics() {
        return grahpics;
    }

    public static void setGrahpics(boolean grahpics) {
        Controller.grahpics = grahpics;
    }

    public static boolean isShow_int() {
        return show_int;
    }

    public static void setShow_int(boolean show_int) {
        Controller.show_int = show_int;
    }

    public static boolean isSupervisor() {
        return supervisor;
    }

    public static void setSupervisor(boolean supervisor) {
        Controller.supervisor = supervisor;
    }

    public static void authentication_failed() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        frame.auth_fail();
    }

    public static void authentication_succeed() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        supervisor = true;
        frame.auth_succ();
    }

    public static void clear_notification(int x) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        frame.clear_notification(x);
    }

//Declaration
    public static int volume = 100;

   static int insulin = 0;
    //Transitions

    /**
     *
     */
    public static int index = 0;
    public static String[] cards = {"home", "menu", "set", "his", "date", "boset", "mode", "baset", "bowiz", "bawiz", "graph1", "auth1", "sec_set1", "dis_set1","about1"};

    //Declaration ends
    //Create instance for all views
    static BasicFrame frame;
    static Home home;//0
    static Menu menu;//1
    static Mode mode;//6
    static basal_set baset;//7
    static basal_wizard bawiz;//9
    static bolus_set boset;//5
    static bolus_wizard bowiz;//8
    static datetime date;//4
    static graph graph1;//10
    static history his;//3
    static settings set;//2
    static Startup start;
    static simulator simulator1;
    static sec_set sec_set1;//12
    static auth auth1;//11
    static dis_set dis_set1;//13
    static about about1;//14

    //End of declaration
    static Date dnow;

}
