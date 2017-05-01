/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import javax.swing.JFrame;

public class MyWindow extends JFrame {
    MyWindow(){
        super("JAVABALL  SERVER");
        setSize(1100,800);
                  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
                  setContentPane(new serverpanel());
    }
}