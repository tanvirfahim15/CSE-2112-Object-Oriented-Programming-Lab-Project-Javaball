/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.net.*;
import java.io.*;

public class gameclient {

    int port = 3011;

    public gameclient(String str) throws ClassNotFoundException {
        String serverName = stgl.server;
       
        try {

            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            out.writeUTF(str);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
