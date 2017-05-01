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
import java.util.logging.Level;
import java.util.logging.Logger;

public class codedelserver extends Thread {

    ServerSocket serverSocket;

    public codedelserver() throws IOException {

        this.serverSocket = new ServerSocket(3003);
        serverSocket.setSoTimeout(10000);

    }

    public void run() {
        while (true) {
            try {
                Socket server = serverSocket.accept();
                DataInputStream in = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                String code = in.readUTF().toString();
                int x;
                if (stgl.codemap.containsKey(code)) {
                    x = stgl.codemap.get(code);
                    stgl.codemap.remove(code);
                    FileWriter fout = new FileWriter("code.txt");
                    fout.write("");

                    for (Map.Entry m : stgl.codemap.entrySet()) {
                        fout.append(m.getKey() + " " + m.getValue());
                        fout.append(System.getProperty("line.separator"));
                    }
                    fout.close();
                } else {
                    x = 0;
                }
                out.writeUTF(Integer.toString(x));

            } catch (SocketTimeoutException s) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
