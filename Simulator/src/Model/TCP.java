/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Praveen Ingale
 * 
 * 
 * 
 */
public class TCP {

    Socket skt;
    BufferedReader in;
    PrintWriter out;
    int glucose;
    

    public TCP() throws IOException {
        skt = new Socket("localhost", 2110);
        in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
        System.out.print("Received string: '");
        out = new PrintWriter(skt.getOutputStream(), true);
        while (!in.ready()) {
        }
        System.out.println(in.readLine()); // Read one line and output it

        System.out.print("Simulator available");
        testcase();
                
    }

    int read() throws IOException {
        out.print("read");
        glucose = Integer.parseInt(in.readLine());
        return glucose;

    }
    void inject(int dose)
    {
        out.print(dose);
    }

    void close() throws IOException {
        out.print("close");
        in.close();
    }

    private void testcase() {
        try {       
            System.out.println("Testing sample data:");
            System.out.println(read());
            inject(0);
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            Logger.getLogger(TCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
