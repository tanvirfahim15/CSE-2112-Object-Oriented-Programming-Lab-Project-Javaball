/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.net.*;
import java.io.*;

public class SignupClient {

    SignupClient(user us) throws ClassNotFoundException {
        String serverName = stgl.server;
        int port = 3000;
        try {

            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            us.port=Integer.parseInt(in.readUTF().toString());
             out.writeUTF(us.toString());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
