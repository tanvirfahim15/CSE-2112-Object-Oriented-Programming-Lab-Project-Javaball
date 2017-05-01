/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

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
        super("Search for Stores");
        setSize(1100, 800);//setIcon();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new searchhome());
    }
    private void setIcon(){
                super.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ball.png")));

    }

}
class dataFreq implements Comparable<dataFreq> {

    String str1;
    String str2;
    int frequency;

    public dataFreq(String str1, String str2, int frequency) {
        this.str1 = str1;
        this.str2 = str2;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(dataFreq o) {
        if (this.frequency == o.frequency) {
            return 0;
        } else if (this.frequency < o.frequency) {
            return 1;
        } else {
            return -1;
        }
    }

}

class topFiveData implements Comparable<topFiveData> {

    String str;
    double dis;

    public topFiveData(String str, double dis) {
        this.str = str;
        this.dis = dis;
    }

    @Override
    public int compareTo(topFiveData o) {
        if (this.dis == o.dis) {
            return 0;
        } else if (this.dis > o.dis) {
            return 1;
        } else {
            return -1;
        }
    }
}

public class Search {

    /*
     *
     * @param args the command line arguments
     */
    static Map<String, String> locmap = new HashMap<String, String>();

    static int getdist(String str, String str2) {
        int arr[] = new int[26];
        int arr2[] = new int[26];
        for (int i = 0; i < 26; i++) {
            arr[i] = 0;
            arr2[i] = 0;
        }
        str = str.toLowerCase();
        str2 = str2.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int x = c - 'a';
            if(i==0)
                arr[x]+=5;
            arr[x]++;
        }

        for (int i = 0; i < str2.length(); i++) {
            char c = str2.charAt(i);
            int x = c - 'a';
            if(i==0)
                arr2[x]+=5;
            arr2[x]++;
        }
        int dist = 0;
        for (int i = 0; i < 26; i++) {
            dist += (arr[i] - arr2[i]) * (arr[i] - arr2[i]);
        }
        return dist;
    }

    static PriorityQueue<topFiveData> locreturn(String str) {
        PriorityQueue<topFiveData> pqueue = new PriorityQueue<topFiveData>();
        //int dist=-1;
        //String ret=null;
        for (Map.Entry m : locmap.entrySet()) {
            // System.out.println(m.getKey()+" "+m.getValue());
            String str2 = (String) m.getValue();
            topFiveData temp = new topFiveData(str2, Search.getdist(str, (String) m.getKey()));
            pqueue.add(temp);
            /*
            if(dist<0)
            {
                dist=Search.getdist(str, str2);
                ret=str2;
            }
            else if(Search.getdist(str, str2)<dist)
            {
                dist=Search.getdist(str, str2);
                ret=str2;
            }*/
        }
        return pqueue;
    }

    static {
        locmap = new HashMap<String, String>();
        Vector<dataFreq> datafreq = new Vector<dataFreq>();
        FileReader fin = null;
        try {
            fin = new FileReader("location.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner sc = new Scanner(fin);
        while (sc.hasNext()) {
            String str1 = sc.next();
            String str2 = sc.next();
            //System.out.println(str1+" "+str2);
            int i = 0;
            boolean found = false;
            for (dataFreq start : datafreq) {
                if (start.str1.compareTo(str1) == 0 && start.str2.compareTo(str2) == 0) {
                    found = true;
                    datafreq.remove(start);
                    //start.setFrequency(start.getFrequency()+1);
                    start.frequency++;
                    datafreq.add(i, start);
                    //System.out.println(str1+" "+str2);
                } else {
                }
                if (found) {
                    break;
                }
                i++;
            }
            if (!found) {
                datafreq.add(new dataFreq(str1, str2, 1));
            }

        }
        PriorityQueue<dataFreq> df = new PriorityQueue<>();
        for (dataFreq start : datafreq) {
            //System.out.println(start.str1+" "+start.str2 + " "+ start.frequency);
            df.add(start);
        }
        while (true) {
            dataFreq temp1 = df.poll();
            if (temp1 != null && !locmap.containsKey(temp1.str1)) {
                if (!locmap.containsValue(temp1.str2)) {
                    locmap.put(temp1.str1, temp1.str2);
                }

            } else if (temp1 != null && locmap.containsKey(temp1.str1)) {
                continue;
            } else {
                break;
            }
        }
    }
    static void mapupdate()
    {
        locmap = new HashMap<String, String>();
        Vector<dataFreq> datafreq = new Vector<dataFreq>();
        FileReader fin = null;
        try {
            fin = new FileReader("location.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner sc = new Scanner(fin);
        while (sc.hasNext()) {
            String str1 = sc.next();
            String str2 = sc.next();
            //System.out.println(str1+" "+str2);
            int i = 0;
            boolean found = false;
            for (dataFreq start : datafreq) {
                if (start.str1.compareTo(str1) == 0 && start.str2.compareTo(str2) == 0) {
                    found = true;
                    datafreq.remove(start);
                    //start.setFrequency(start.getFrequency()+1);
                    start.frequency++;
                    datafreq.add(i, start);
                    //System.out.println(str1+" "+str2);
                } else {
                }
                if (found) {
                    break;
                }
                i++;
            }
            if (!found) {
                datafreq.add(new dataFreq(str1, str2, 1));
            }

        }
        PriorityQueue<dataFreq> df = new PriorityQueue<>();
        for (dataFreq start : datafreq) {
            //System.out.println(start.str1+" "+start.str2 + " "+ start.frequency);
            df.add(start);
        }
        while (true) {
            dataFreq temp1 = df.poll();
            if (temp1 != null && !locmap.containsKey(temp1.str1)) {
                if (!locmap.containsValue(temp1.str2)) {
                    locmap.put(temp1.str1, temp1.str2);
                }

            } else if (temp1 != null && locmap.containsKey(temp1.str1)) {
                continue;
            } else {
                break;
            }
        }
    }
    public static MyWindow frame = new MyWindow();

    public static void main(String[] args) throws FileNotFoundException {
        
        frame.setVisible(true);

    }

}
