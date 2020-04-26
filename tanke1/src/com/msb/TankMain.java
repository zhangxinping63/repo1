package com.msb;

/**
 * @Author: Zhangxp
 * @Date: 2020-04-24 10:51
 */
public class TankMain {
    public static void main(String[] args) throws Exception{
        TankFrame tf = new TankFrame();
        //初始化敌人tank
        for(int i = 0; i < 5; i++){
            tf.tanks.add(new Tank(50 +i * 80,200,Dir.DOWN,Group.BAD, tf));
        }


        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
