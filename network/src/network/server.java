package network;

import java.net.*;
import java.io.*;

public class server {

    public static void main(String[] args) {
        stgl stGl = new stgl();
        try {
            Thread t = new GameServer();
            Thread t1 = new Signupserver();
            Thread t2 = new startserver();
            Thread t3 = new codedelserver();

            t.start();
            t1.start();
            t2.start();
            t3.start();
            Thread p[] = new Thread[50];
            Thread q[] = new Thread[50];

            int i;
            for (i = 0; i < 50; i++) {
                p[i] = new playserver(3050 + i);
                p[i].start();
                q[i] = new playdata(3150 + i);
                q[i].start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
