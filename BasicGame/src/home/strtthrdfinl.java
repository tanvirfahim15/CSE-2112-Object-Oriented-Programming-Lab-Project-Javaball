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
public class strtthrdfinl  extends Thread{
    public void run(){
        String[] args = null;
        try {
            mygame.finl.main(args);
        } catch (ClassNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
}
