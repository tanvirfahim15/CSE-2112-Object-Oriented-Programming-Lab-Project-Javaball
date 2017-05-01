/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Tanvir
 */
public class heatmap {
    
    static float[] x = new float[16];

    public static void main(int p) throws IOException {
        int won = 0;
        int port = 3049+p;
        if(p==-1){
            for (Map.Entry m : stgl.usermap.entrySet()) {
            user us = user.toobject(m.getValue().toString());
            if (us.won > won) {
                won = us.won;
                port = us.port;
            }
        }
        }
        port += 100;
        
        System.out.println(port);
        int n = 0;
        FileReader fin = new FileReader(port + ".txt");
        Scanner sc = new Scanner(fin);
        while (sc.hasNext()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            int i = 0;
            while (st.hasMoreTokens()) {

                x[i] += Float.parseFloat(st.nextToken());
                i++;
            }
            n++;
        }
        for(int i=0;i<16;i++)
        {
            x[i]/=n;
        }
    }
}
