package Model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import View.bolus_wizard;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.Layer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Praveen Ingale
 */
public class DBconn {

     Connection conn;

    public DBconn() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:bolus");
            System.out.println("Opened database successfully");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBconn.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *@param s
     * @param x
     * @param y
     * @return 
     */
  
    public String getpassword()
    {
        
          ResultSet result;
          String password = null;
          try
          {
            Statement st = conn.createStatement();
            result = st.executeQuery("select * from password" );
             password= result.getString("password");
                //conn.close();
          }
            catch (SQLException ex) {
            Logger.getLogger(DBconn.class.getName()).log(Level.SEVERE, null, ex);
            }
       return password;
    }
    public String getsuperpassword()
    {
        
          ResultSet result;
          String password = null;
          try
          {
            Statement st = conn.createStatement();
            result = st.executeQuery("select * from supervisor" );
             password= result.getString("password");
                //conn.close();
          }
            catch (SQLException ex) {
            Logger.getLogger(DBconn.class.getName()).log(Level.SEVERE, null, ex);
            }
       return password;
    }
    public void savepassword(String newpwd)
    {
            PreparedStatement prep;
             try {
                    prep = conn.prepareStatement("update supervisor set password = '"+ newpwd + "';");
                    prep.executeUpdate();
                  //   conn.close();
           
                
                } catch (SQLException ex) {
            Logger.getLogger(DBconn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public void GenerateChart()
    {
        
        XYSeries series = new XYSeries("Blood Glucose");
       
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
         ResultSet result;
         String x;
         int y;
         int count=0;
         int i=0;
         Double d;
         try
         {
            
            Statement st = conn.createStatement();
            result = st.executeQuery("select * from bolus order by date DESC, time DESC" );
             
            
            while(result.next()){
              count = count+1;
              System.out.print(result.getInt("blood_glucose")+"\n");
              System.out.print(result.getString("time")+"\n");
              x=result.getString("time");
              
              y=result.getInt("blood_glucose");
              if(count <= 6)
              {
              dataset1.addValue(result.getInt("blood_glucose"), "Blood_Glucose", result.getString("time"));
              System.out.println("Test");
              System.out.println(result.getInt("blood_glucose"));
              System.out.println(result.getString("time"));
              //series.add(Integer.parseInt(x.substring(0, 1)), result.getInt("blood_glucose"));
              }
              //series.add(Integer.parseInt(x.substring(0, 4)), result.getInt("blood_glucose"));
           
            }
            
         }catch (SQLException ex) {
            Logger.getLogger(DBconn.class.getName()).log(Level.SEVERE, null, ex);
        }
             
  
  
        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(series);
        // Generate the graph
        /*JFreeChart chart = ChartFactory.createXYLineChart(
        "Blood Glucose", // Title
        "Time", // x-axis Label
        "Blood Glucose", // y-axis Label
        dataset, // Dataset
        PlotOrientation.VERTICAL, // Plot Orientation
        true, // Show Legend
        true, // Use tooltips
        false // Configure chart to generate URLs?
        );*/
        
        final JFreeChart chart = ChartFactory.createLineChart(
            "Blood Glucose Chart",       // chart title
            "Time",                    // domain axis label
            "Blood_Glucose Value",                   // range axis label
            dataset1,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );
        
        /*XYPlot xyp = chart.getXYPlot();
        BasicStroke bs = new BasicStroke(1);
        xyp.addRangeMarker(new ValueMarker(70, Color.BLACK, bs));
        xyp.addRangeMarker(new ValueMarker(130, Color.BLUE, bs));
        xyp.addRangeMarker(new ValueMarker(230, Color.RED, bs));*/
        
         ValueMarker zeroMarker =
              new ValueMarker(70.00, Color.BLACK, new BasicStroke(2.0f));
         ValueMarker zeroMarker1 =
              new ValueMarker(130.00, Color.GREEN, new BasicStroke(2.0f));
         ValueMarker zeroMarker2 =
              new ValueMarker(180.00, Color.RED, new BasicStroke(2.0f));
         
        CategoryPlot plot= chart.getCategoryPlot();
        
        plot.addRangeMarker(zeroMarker);
         plot.addRangeMarker(zeroMarker1);
         plot.addRangeMarker(zeroMarker2);
         
       //  CategoryAxis c = (CategoryAxis)plot.getDomainAxis();
       // BasicStroke bs = new BasicStroke(1);
        //plot.addRangeMarker(new ValueMarker(70, Color.BLACK, bs));
        //plot.addRangeMarker(i, null, Layer.FOREGROUND);
      //   NumberFormat currency = NumberFormat.getCurrencyInstance();
NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
rangeAxis.setLabelFont(new Font("Helvetica", Font.BOLD, 14));
       
        
        try {
            ChartPanel chartpanel = new ChartPanel(chart);
           
            ChartUtilities.saveChartAsJPEG(new File("E:\\new.jpg"), chart, 500,500);
            
            
            //jPanel2.add(chartpanel, BorderLayout.CENTER);
            // jLabel.setIcon(new javax.swing.ImageIcon("E:\\chart.jpg")); 
           
            

            } catch (IOException e) {
                System.err.println("Problem occurred creating chart.");
            }
    }
//End */
   // public void insert(Float x, Float y) {
    
    /**
     *
     * @param stage
     * @param s
     * @param x
     * @param y
     */
    
    public void insert_bolus(String w,String q,String s,int x,Double y) {
        PreparedStatement prep;
        try {
           Statement create = conn.createStatement();
           
           // create.execute("CREATE TABLE if not exists graph( points integer primary key asc autoincrement, xaxis real not null, yaxis real not null);");
             create.execute("CREATE TABLE if not exists bolus( insulin_type text,date text, time text, blood_glucose integer, insulin_dose real not null);");

           prep = conn.prepareStatement("insert into bolus(insulin_type,date, time, blood_glucose, insulin_dose) values (?,?, ?, ?, ?);");
            
           prep.setString(1, w);
            prep.setString(2, q);
           prep.setString(3, s);
           prep.setInt(4, x);
           prep.setDouble(5, y);
            
            
            ResultSet result;
            prep.executeUpdate();
            Statement st = conn.createStatement();
            result = st.executeQuery("select * from bolus;");
            while(result.next()){
               
              System.out.print(result.getInt("blood_glucose")+"\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBconn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    public void insert_basal(String g,String s,int x,float y) {
        PreparedStatement prep;
        try {
         Statement create = conn.createStatement();
           // create.execute("CREATE TABLE if not exists graph( points integer primary key asc autoincrement, xaxis real not null, yaxis real not null);");
         create.execute("CREATE TABLE if not exists basal( start_time text,end_time text, blood_glucose integer, insulin_dose real not null);");

           prep = conn.prepareStatement("insert into basal(start_time, end_time, blood_glucose, insulin_dose) values (?, ?, ?, ?);");
            
           prep.setString(1, g);
           prep.setString(2, s);
           prep.setInt(3, x);
           prep.setFloat(4,y);
            
            
            ResultSet result;
            prep.executeUpdate();
            Statement st = conn.createStatement();
            result = st.executeQuery("select * from bolus;");
            while(result.next()){
               
              System.out.print(result.getInt("blood_glucose")+"\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBconn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBconn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
    
  /*  public void filldata()
    {
         PreparedStatement prep;
        try
        {
            ResultSet result;
            int count=0;
            Statement st = conn.createStatement();
            result = st.executeQuery("select * from bolus;");
            while(result.next()){
                count++;
                View.bolus_wizard bz=new View.bolus_wizard();
               
                bz.getdata(count,result.getString("time"), result.getInt("blood_glucose"), result.getFloat("insulin_dose"));
               // BolusTable ts=new BolusTable();
                System.out.println("Test");
                bz.getdata(count,result.getString("time"), result.getInt("blood_glucose"), result.getFloat("insulin_dose"));
                  System.out.println(count);
                    System.out.println(result.getString("time"));
                   
                
               // ts.getdata(count,result.getInt("blood_glucose"), result.getFloat("insulin_dose"));
               
            }
                
        }catch (SQLException ex) {
            Logger.getLogger(DBconn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    }
    

