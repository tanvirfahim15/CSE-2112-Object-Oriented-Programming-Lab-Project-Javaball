/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class playserver extends Thread {

    ServerSocket serverSocket;
    int port;
    public playserver(int port) throws IOException {

        this.serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10);
        this.port=port;
    }

    public void run() {
        while (true) {
            try {
                Socket server = serverSocket.accept();
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                DataInputStream in = new DataInputStream(server.getInputStream());
                stgl.playdata.put(port,in.readUTF());
                System.out.println(stgl.playdata.get(port));
                if(stgl.playport.containsKey(port))
                out.writeUTF(stgl.playdata.get(stgl.playport.get(port)));
                else
                    out.writeUTF("0 0 0 0 0 1");
            } catch (SocketTimeoutException s) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
