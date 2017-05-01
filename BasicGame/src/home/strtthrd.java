/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TANVIR
 */
public class strtthrd  extends Thread{
    public void run(){
        String[] args = null;
        try {
            mygame.Main.main(args);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(strtthrd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(strtthrd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
