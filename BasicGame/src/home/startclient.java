/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.net.*;
import java.io.*;
import java.util.StringTokenizer;

public class startclient {

    startclient() throws ClassNotFoundException {
        String serverName = stgl.server;
        int port = 3001;
        try {

            Socket client = new Socket(serverName, port);

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            String str=in.readUTF().toString();
            while(!str.equals("0")){
                user us=user.toobject(str);
                stgl.usermap.put(us.username, us);
                str=in.readUTF().toString();
            }
            
            while(true){
                str=in.readUTF().toString();
                if(str.equals("1"))
                    break;
                StringTokenizer st=new StringTokenizer(str);
                String code=st.nextToken().toString();
                int day=Integer.parseInt(st.nextToken().toString());
                stgl.codemap.put(code, day);
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
