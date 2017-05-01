/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;
import java.util.StringTokenizer;

/**
 *
 * @author TANVIR
 */
public class controls {

    int s, d, up, down, left, right;

    public controls() {
    }

    public controls(String str) {
        StringTokenizer st = new StringTokenizer(str);
        controls cntrl = new controls();
        this.s = Integer.parseInt(st.nextToken());
        this.d = Integer.parseInt(st.nextToken());
        this.up = Integer.parseInt(st.nextToken());
        this.down = Integer.parseInt(st.nextToken());
        this.left = Integer.parseInt(st.nextToken());
        this.right = Integer.parseInt(st.nextToken());

    }

    @Override
    public String toString() {
        return s + " " + d + " " + up + " " + down + " " + left + " " + right;
    }

    static controls toobject(String str) {
        StringTokenizer st = new StringTokenizer(str);
        controls cntrl = new controls();
        cntrl.s = Integer.parseInt(st.nextToken());
        cntrl.d = Integer.parseInt(st.nextToken());
        cntrl.up = Integer.parseInt(st.nextToken());
        cntrl.down = Integer.parseInt(st.nextToken());
        cntrl.left = Integer.parseInt(st.nextToken());
        cntrl.right = Integer.parseInt(st.nextToken());
        return cntrl;
    }

}