/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

/**
 *
 * @author Tanvir
 */
public class homestartthread extends Thread{
    public void run(){
        search.Search.frame.setVisible(false);
        home.Main.frame.setVisible(true);
    }
}
