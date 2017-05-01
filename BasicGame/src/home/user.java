/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.io.FileNotFoundException;
import java.util.StringTokenizer;

/**
 *
 * @author Tanvir
 */
public class user {

   public  String username;
   public  String password;
   public  String name;
   public  String mobno;
   public  int days;
   public   int matches;
   public  int won;
    public int lost;
    public int goal;
    public int conceded;
    public int port;
    public int fport;

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
