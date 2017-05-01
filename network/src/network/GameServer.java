/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GameServer extends Thread {

    ServerSocket serverSocket = new ServerSocket(3011);

    public GameServer() throws IOException {

        serverSocket.setSoTimeout(10);

    }

    public void run() {
        while (true) {
            try {
                Socket server = serverSocket.accept();
                DataInputStream in = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                String recieved = in.readUTF().toString();
                System.out.println(recieved);
                user us = user.toobject(recieved);
                stgl.usermap.put(us.username, us);
                FileWriter fout = new FileWriter("user.txt");
                for (Map.Entry m : stgl.usermap.entrySet()) {
                    fout.append(m.getValue().toString());
                    fout.append(System.getProperty("line.separator"));
                }
                fout.close();
            } catch (SocketTimeoutException s) {
                // System.out.println("Socket timed out!");
            } catch (IOException e) {
            }
        }
    }

}
