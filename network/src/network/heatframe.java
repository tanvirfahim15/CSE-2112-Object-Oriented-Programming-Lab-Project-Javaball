/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class label extends JLabel {

    label(int x, int y) {
        super("");
        ImageIcon ic;
        ic = new ImageIcon(getClass().getResource("dot.jpg"));
        super.setIcon(ic);
        super.setBounds(x, y, x + 48, y + 48);
        super.setForeground(new java.awt.Color(255, 255, 255));
    }
    label(int x, int y,int x1,int y1,String s) {
        super("");
        ImageIcon ic;
        ic = new ImageIcon(getClass().getResource(s));
        super.setIcon(ic);
        super.setBounds(x, y, x1, y1);
    }
}


public class heatframe {

    public static void main(int port) throws IOException {
        heatmap.main(port);JFrame frame;
        if(port==-1)frame = new JFrame("HEATMAP OF BEST PLAYER");
        else frame = new JFrame("HEATMAP of PLAYER #"+Integer.toString(port));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton jb=new JButton("BACK TO SERVER");
        jb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frame.setVisible(false);
                Main.frame.setVisible(true);
            }
        });
        jb.setBounds(380, 10, 520, 50);
        JPanel panel = (JPanel) frame.getContentPane();
        panel.add(jb);
        panel.setLayout(null);
        label l[] = new label[8];
        for (int i = 0; i < 16; i += 2) {

            int x = (int) ((heatmap.x[i] + 11) * 50)+100;
            int y = (int) ((heatmap.x[i+1] + 5) * 50);
            l[i/2] = new label(x, y);
            l[i/2].setText("player#"+(i/2+1));
            panel.add(l[i/2]);
        }
        label fl=new label(20,50,1200,700,"fld.png");
        panel.add(fl);
        label jLabel2=new label(0,0,1250,800,"b.jpg");
        
        panel.add(jLabel2);
        frame.setSize(1250, 800);
        frame.setVisible(true);

    }
}
