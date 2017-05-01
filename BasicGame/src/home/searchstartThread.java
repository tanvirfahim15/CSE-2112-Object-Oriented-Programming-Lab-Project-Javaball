/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanvir
 */
public class searchstartThread extends Thread {
    public void run(){
        home.Main.frame.setVisible(false);
        String []args=null;
        try {
            search.Search.main(args);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(searchstartThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
