/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;
import home.tplay;
import home.user;
/**
 *
 * @author Tanvir
 */
public class semi1returntrhd extends Thread{
    user us1,us2,us3,us4,us5;
    semi1returntrhd(user us1,user us2,user us3,user us4,user us5){
        this.us1=us1;
        this.us2=us2;
        this.us3=us3;
        this.us4=us4;
        this.us5=us5;
    }
    public void run(){
        tplay t=new tplay(us1, us2,us3,us4,us5);
        home.Main.frame.setContentPane(t);

    }
}
