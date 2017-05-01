/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mygame.Main.us1;
import static mygame.Main.us2;

/**
 *
 * @author TANVIR
 */
public class datath extends Thread{
    String st,st2;
    datath(String st,String st2){
        this.st=st;
        this.st2=st2;
    }
    public void run(){
        try {
            new playdataclient(us1.port,st);
        } catch (ClassNotFoundException ex) {
        } catch (IOException ex) {
        }
        try {
            new playdataclient(us2.port,st2);
        } catch (ClassNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
}
