/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class playdataclient {

    public playdataclient(int port,String str) throws ClassNotFoundException, IOException {
        String serverName = home.stgl.server;
        try {

                Socket client = new Socket(serverName, port + 100);
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);

                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                out.writeUTF(str);
                
                client.close();
            
        } catch (IOException e) {
        }
    }

}
