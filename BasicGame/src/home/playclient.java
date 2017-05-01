/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
 
public class playclient {
 
   public playclient(int port,String str) throws ClassNotFoundException {
        String serverName = stgl.server;
        try {
 
            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
 
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            
             out.writeUTF(str);
             mygame.Main.encntrl=new mygame.control(in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
}