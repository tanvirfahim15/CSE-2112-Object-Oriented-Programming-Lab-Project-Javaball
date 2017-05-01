/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Tanvir
 */
public class updateserver {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileReader fin = new FileReader("user.txt");
        Scanner sc = new Scanner(fin);
        FileWriter fout = new FileWriter("usertmpp.txt");
        while (sc.hasNext()) {
            user us = user.toobject(sc.nextLine());
            if(us.days>0)
                us.days--;
            fout.append(us.toString());
            fout.append(System.getProperty("line.separator"));
        }
        fout.close();
        fin = new FileReader("usertmpp.txt");
        sc = new Scanner(fin);
        fout = new FileWriter("user.txt");
        while (sc.hasNext()) {
            user us = user.toobject(sc.nextLine());
            fout.append(us.toString());
            fout.append(System.getProperty("line.separator"));
        }
        fout.close();

    }
}
