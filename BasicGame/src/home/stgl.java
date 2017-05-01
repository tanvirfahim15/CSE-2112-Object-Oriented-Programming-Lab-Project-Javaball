/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanvir
 */
public class stgl {
        public static Map<String, user> usermap = new HashMap<String, user>();
        static Map<String, Integer> codemap = new HashMap<String, Integer>();
        public static String server;
        public static user cuser; 
       static{
            FileReader fin = null;
        try {
            fin = new FileReader("configip.txt");
        } catch (FileNotFoundException ex) {
        }
            Scanner sc=new Scanner(fin);
            server=sc.nextLine();
        }
}
