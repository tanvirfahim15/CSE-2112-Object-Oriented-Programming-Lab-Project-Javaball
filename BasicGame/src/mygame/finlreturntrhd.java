/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import home.tplay;
import home.twin;
import home.user;

/**
 *
 * @author Tanvir
 */
public class finlreturntrhd extends Thread {

    user us1;

    finlreturntrhd(user us1) {
        this.us1 = us1;


    }

    public void run() {
        twin t=new twin(us1);
        home.Main.frame.setContentPane(t);

    }
}
