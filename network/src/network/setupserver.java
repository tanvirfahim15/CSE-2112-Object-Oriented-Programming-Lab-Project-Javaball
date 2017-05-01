/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Tanvir
 */
public class setupserver {
    
    public static void main(String[] args) throws IOException {
        FileWriter fout = new FileWriter("code.txt");
        fout.write("pass 20");
        fout.append(System.getProperty("line.separator"));
        fout.write("pass1 25");
        fout.append(System.getProperty("line.separator"));
        fout.write("pass2 40");
        fout.append(System.getProperty("line.separator"));
        fout.write("pass3 10");
        fout.append(System.getProperty("line.separator"));
        fout.write("pass4 200");
        fout.append(System.getProperty("line.separator"));
        fout.close();
        fout = new FileWriter("cs.txt");
        fout.write(Integer.toString(3050));
        fout.close();
        fout = new FileWriter("user.txt");
        fout.write("");
        fout.close();
    }
}
