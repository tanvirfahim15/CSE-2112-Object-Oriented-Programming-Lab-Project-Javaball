/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.IOException;
import server.*;
import javax.swing.JFrame;

public class MyWindow extends JFrame {

    MyWindow() throws IOException {
        super("HELPLINE");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new user());
    }
}