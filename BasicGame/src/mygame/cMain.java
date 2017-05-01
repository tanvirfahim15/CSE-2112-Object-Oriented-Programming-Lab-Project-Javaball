/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import jssc.SerialPort;
import jssc.SerialPortException;

public class cMain extends Thread{

    public static int x;

    public void run() {
        SerialPort serialPort = new SerialPort("COM3");
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(9600, 8, 1, 0);//Set params.
            while (true) {
                String buffer = serialPort.readString();//Read 10 bytes from serial port
                if (buffer != null) {
                    buffer = buffer.trim();
                    cMain.x = buffer.charAt(0)-48;
                    System.out.println(x);

                }
            }
            //serialPort.closePort();//Close serial port
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }catch(StringIndexOutOfBoundsException e){
            
        }
    }
}
