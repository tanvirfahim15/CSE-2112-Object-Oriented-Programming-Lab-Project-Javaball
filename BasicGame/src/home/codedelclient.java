/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;


import java.net.*;
import java.io.*;

public class codedelclient {
    int val=0;
    codedelclient(String code) throws ClassNotFoundException {
        String serverName = stgl.server;
        int port = 3003;
        try {

            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
             out.writeUTF(code.toString());             
            val=Integer.parseInt(in.readUTF().toString());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

