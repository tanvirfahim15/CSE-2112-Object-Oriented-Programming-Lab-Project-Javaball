/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;
import home.user;
import home.userhome;
import static mygame.Main.us1;
import static mygame.Main.us2;
/**
 *
 * @author Tanvir
 */
public class gamereturntrhd extends Thread{
    user us1,us2;
    gamereturntrhd(user us1,user us2){
        this.us1=us1;
        this.us2=us2;
    }
    public void run(){
        userhome u=new userhome(us1, us2);
        home.Main.frame.removeAll();
        home.Main.frame.setContentPane(u);
    }
}
