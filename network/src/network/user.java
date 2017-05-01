/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Tanvir
 */
public class user {

    String username;
    String password;
    String name;
    String mobno;
    int days;
    int matches;
    public int won;
    int lost;
    int goal;
    int conceded;
    int port;
    

    public user(String username, String password, String name, String mobno, int days) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.mobno = mobno;
        this.days = days;
    }

    public user(String username, String password, String name, String mobno, int days, int matches, int won, int lost, int goal, int conceded, int port) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.mobno = mobno;
        this.days = days;
        this.matches = matches;
        this.won = won;
        this.lost = lost;
        this.goal = goal;
        this.conceded = conceded;
        this.port = port;
    }
    
    @Override
    public String toString() {
        return  username + " " + password + " " + name + " " + mobno + " " + days + " " + matches + " " + won + " " + lost + " " + goal + " " + conceded + " " + port  ;
    }
    static user toobject(String str) throws FileNotFoundException{
        
        
        StringTokenizer st=new StringTokenizer(str);
        user us=new user(st.nextToken().toString(),st.nextToken().toString(),st.nextToken().toString(),st.nextToken().toString(),Integer.parseInt(st.nextToken().toString()),Integer.parseInt(st.nextToken().toString()),Integer.parseInt(st.nextToken().toString()),Integer.parseInt(st.nextToken().toString()),Integer.parseInt(st.nextToken().toString()),Integer.parseInt(st.nextToken().toString()),Integer.parseInt(st.nextToken().toString()));
        return us;
    }
    
    
}
