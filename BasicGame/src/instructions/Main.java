/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package instructions;

import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Tanvir
 */
class MyWindow extends JFrame {

   MyWindow() {
        super("Instructions");
        setSize(1100, 950);             //   super.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ball.png")));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new InstructionMainPage());
       
             
    }

}
public class Main{
    public static MyWindow frame = new MyWindow();

    public static void main(String[] args) throws FileNotFoundException {
        
        frame.setVisible(true);

    }

}