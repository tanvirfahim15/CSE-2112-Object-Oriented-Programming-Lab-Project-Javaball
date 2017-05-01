/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import java.util.StringTokenizer;

/**
 *
 * @author TANVIR
 */
public class control {

    public int pass;
    public int tackle;
    public int up;
    public int down;
    public int le;
    public int ri;

    @Override
    public String toString() {
        return pass + " " + tackle + " " + up + " " + down + " " + le + " " + ri ;
    }

    public control() {
    }
    public control(String str) {
        StringTokenizer st=new StringTokenizer(str);
        pass=Integer.parseInt(st.nextToken());
        tackle=Integer.parseInt(st.nextToken());
        up=Integer.parseInt(st.nextToken());
        down=Integer.parseInt(st.nextToken());
        le=Integer.parseInt(st.nextToken());
        ri=Integer.parseInt(st.nextToken());
    }
    
    

}
