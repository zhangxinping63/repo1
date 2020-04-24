package com.msb;

/**
 * @Author: Zhangxp
 * @Date: 2020-04-24 10:51
 */
public class TankMain {
    public static void main(String[] args) throws Exception{
        TankFrame tf = new TankFrame();
        while (true){
            Thread.sleep(50);
            tf.repaint();
            System.out.println("1");
        }
    }
}
