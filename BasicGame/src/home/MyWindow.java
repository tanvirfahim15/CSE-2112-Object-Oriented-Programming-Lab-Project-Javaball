/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.awt.Toolkit;
import javax.swing.JFrame;

public class MyWindow extends JFrame {
    MyWindow(){
        super("JAVABALL");
        setSize(1100,800);
        setIcon();
                  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
                  setContentPane(new Home());
    }

    private void setIcon() {
        super.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ball.png")));
    }
}