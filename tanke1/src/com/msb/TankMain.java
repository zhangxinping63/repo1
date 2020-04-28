package com.msb;

import java.util.Properties;

/**
 * @Author: Zhangxp
 * @Date: 2020-04-24 10:51
 */
public class TankMain {
    public static void main(String[] args) throws Exception{
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt( (String) PropertyMgr.get("initTankCount"));
        //初始化敌人tank
       for(int i = 0; i < initTankCount; i++){
            tf.tanks.add(new Tank(50 +i * 80,200,Dir.DOWN,Group.BAD, tf));
        }

       new Thread(()->new Audio("audio/war1.wav").loop()).start();


        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
