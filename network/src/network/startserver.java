/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class startserver extends Thread {

    ServerSocket serverSocket;

    public startserver() throws IOException {

        this.serverSocket = new ServerSocket(3001);
        serverSocket.setSoTimeout(10000);

    }

    public void run() {
        while (true) {
            try {
                Socket server = serverSocket.accept();
                System.out.println("coonnneccttted");
                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                FileReader fin = new FileReader("user.txt");
                Scanner sc = new Scanner(fin);
                
                while(true){
                    if(sc.hasNext())
                    out.writeUTF(sc.nextLine());
                    else{
                        out.writeUTF("0");
                        break;
                    }
                }
                fin=new FileReader("code.txt");
                sc=new Scanner(fin);
                while (true){
                    if(sc.hasNext())
                    out.writeUTF(sc.nextLine());
                    else{
                        out.writeUTF("1");
                        break;
                    }
                }
                
            } catch (SocketTimeoutException s) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
