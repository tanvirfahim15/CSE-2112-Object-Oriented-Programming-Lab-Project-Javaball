/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TANVIR
 */
public class MAIN {

    public static void updatedata() throws IOException {
        FileWriter fout = new FileWriter("data.txt");
        for (Map.Entry m : datamap.entrySet()) {
            fout.append(m.getKey().toString());
            fout.append(System.getProperty("line.separator"));
            fout.append(m.getValue().toString());
            fout.append(System.getProperty("line.separator"));
        }
        fout.close();

    }

    public static String getans(String str) {
        String ans = null;
        str = str.replace("?", " ");
        str = str.replace(".", " ");

        StringTokenizer st = new StringTokenizer(str);
        int i1 = 0;
        String[] ar1 = new String[100];
        while (st.hasMoreTokens()) {
            ar1[i1++] = st.nextToken();
        }
        int val = 0;
        for (Map.Entry m : datamap.entrySet()) {
            String[] ar2 = new String[100];
            int i2 = 0;
            String str2 = (String) m.getKey();

            str2 = str2.replace("?", " ");
            str2 = str2.replace(".", " ");

            st = new StringTokenizer(str2);
            while (st.hasMoreTokens()) {
                ar2[i2++] = st.nextToken();
            }
            int tmp = 0;
            for (int i = 0; i < i1; i++) {
                for (int j = 0; j < i2; j++) {
                    if (ar1[i].equals(ar2[j])) {
                        tmp++;
                    }
                }
            }
            if (tmp > val) {
                val = tmp;
                ans = (String) m.getValue();
            }

        }

        return ans;
    }
    static Map<String, String> datamap = new HashMap<String, String>();

    static {
        FileReader fin = null;
        try {
            fin = new FileReader("data.txt");
        } catch (FileNotFoundException ex) {
        }
        Scanner sc = new Scanner(fin);
        while (sc.hasNext()) {
            String str1 = sc.nextLine();
            String str2 = sc.nextLine();
            datamap.put(str1, str2);
        }

    }

    public static void main(String[] args) {

        MyWindow frame = new MyWindow();
        frame.setVisible(true);
    }
}
