/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Tanvir
 */
public class setupml {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 55; i++) {
            FileWriter fout = new FileWriter(3150+i+ ".txt");
            fout.write("");
            fout.close();

        }
    }
}
