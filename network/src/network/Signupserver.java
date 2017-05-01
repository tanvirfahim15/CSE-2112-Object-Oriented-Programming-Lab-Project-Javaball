/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanvir
 */
public class Signupserver extends Thread {

    ServerSocket serverSocket;
    int x = 0;

    public Signupserver() throws IOException {

        this.serverSocket = new ServerSocket(3000);
        serverSocket.setSoTimeout(10000);
        FileReader fin = new FileReader("cs.txt");
        Scanner sc = new Scanner(fin);
        x = sc.nextInt();

    }

    public void run() {
        while (true) {
            try {
                Socket server = serverSocket.accept();
                DataInputStream in = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF(Integer.toString(x));
                x++;
                FileWriter fout = new FileWriter("cs.txt");
                fout.write(Integer.toString(x));
                fout.close();
                FileReader fin = new FileReader("user.txt");
                Scanner sc = new Scanner(fin);
                fout = new FileWriter("usertmp.txt");
                while(sc.hasNext()){
                    fout.write(sc.nextLine());
                    fout.append(System.getProperty("line.separator"));
                }
                fout.close();
                fin = new FileReader("usertmp.txt");
                fout = new FileWriter("user.txt");
                sc = new Scanner(fin);
                fout.write(in.readUTF().toString());
                fout.append(System.getProperty("line.separator"));
                while(sc.hasNext()){
                    fout.write(sc.nextLine());
                    fout.append(System.getProperty("line.separator"));
                }
                fout.close();
                stgl.update();
            } catch (SocketTimeoutException s) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
