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

public class playdata extends Thread {

    ServerSocket serverSocket;
    int port;

    public playdata(int port) throws IOException {

        this.serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10);
        this.port = port;
    }

    public void run() {
        while (true) {
            try {
                Socket server = serverSocket.accept();
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                DataInputStream in = new DataInputStream(server.getInputStream());
                String st = Integer.toString(port);
                System.out.println(port);
                FileWriter fout = new FileWriter(st + "tmp.txt");
                fout.write("");
                FileReader fin = new FileReader(st + ".txt");
                Scanner sc = new Scanner(fin);
                while (sc.hasNext()) {
                    fout.append(sc.nextLine());
                    fout.append(System.getProperty("line.separator"));
                }
                fout.write(in.readUTF());
                fout.close();
                fout = new FileWriter(st + ".txt");
                fin = new FileReader(st + "tmp.txt");
                sc = new Scanner(fin);
                while (sc.hasNext()) {
                    fout.write(sc.nextLine());
                    fout.append(System.getProperty("line.separator"));

                }
                fout.close();
            } catch (SocketTimeoutException s) {
            } catch (IOException e) {
            }
        }
    }

}
