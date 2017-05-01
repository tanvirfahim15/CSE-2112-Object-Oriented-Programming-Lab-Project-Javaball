package home;

import com.jme3.system.AppSettings;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import mygame.*;
import static sun.net.www.http.HttpClient.New;

public class Main {

    public static MyWindow frame = new MyWindow();

    public static void update(String str) throws ClassNotFoundException, FileNotFoundException {
        StringTokenizer st=new StringTokenizer(str);
        int goal=Integer.parseInt(st.nextToken());
        int con=Integer.parseInt(st.nextToken());
         try{
             stgl.cuser.goal+=goal;
         stgl.cuser.conceded+=con;
         if(goal>con)
             stgl.cuser.won++;
         if(goal<con)
             stgl.cuser.lost++;
         stgl.cuser.matches++;
         }catch(Exception e){
             
         }
         frame.removeAll();
              
        
        new gameclient(stgl.cuser.toString());
    }

    public static int close() throws IOException, ClassNotFoundException {

        FileWriter fout = new FileWriter("access.txt");
        fout.write("0");
        fout.close();
        //System.exit(0);
        return 0;
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        
        close();
        new startclient();
        new sound().start();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
