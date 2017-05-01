/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanvir
 */
public class stgl {

    static Map<String, user> usermap = new HashMap<String, user>();
    static Map<String, Integer> codemap = new HashMap<String, Integer>();

    static Map<Integer, Integer> playport = new HashMap<Integer, Integer>();
    static Map<Integer, String> playdata = new HashMap<Integer, String>();

    static int playerq = 0;

    static {
        FileReader fin;
        try {
            fin = new FileReader("user.txt");
            Scanner sc = new Scanner(fin);
            while (sc.hasNext()) {
                user us = user.toobject(sc.nextLine());
                usermap.put(us.username, us);
            }

            fin = new FileReader("code.txt");
            sc = new Scanner(fin);
            while (sc.hasNext()) {
                StringTokenizer st = new StringTokenizer(sc.nextLine());
                String code = st.nextToken().toString();
                int day = Integer.parseInt(st.nextToken().toString());
                stgl.codemap.put(code, day);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(stgl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void update() {
        FileReader fin;
        try {
            fin = new FileReader("user.txt");
            Scanner sc = new Scanner(fin);
            while (sc.hasNext()) {
                user us = user.toobject(sc.nextLine());
                usermap.put(us.username, us);
            }

            fin = new FileReader("code.txt");
            sc = new Scanner(fin);
            while (sc.hasNext()) {
                StringTokenizer st = new StringTokenizer(sc.nextLine());
                String code = st.nextToken().toString();
                int day = Integer.parseInt(st.nextToken().toString());
                stgl.codemap.put(code, day);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(stgl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //for (Map.Entry m : usermap.entrySet()) {}

}
