package com.msb;

import java.awt.*;

/**
 * @Author: Zhangxp
 * @Date: 2020-04-24 14:09
 */
public class Explode {

    //炮弹宽度，高度
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    //子弹位置
    private int x,y;

    //定义子弹活着
    private boolean living = true;
    TankFrame tf = null;

    private int step = 0;


    //构造方法
    public Explode(int x, int y,TankFrame tf){
        this.x = x;
        this.y = y;
        this.tf = tf;


       // new Audio("audio/explode.wav").run();
    }
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if(step >= ResourceMgr.explodes.length){
           // step = 0;
            tf.explodes.remove(this);
        }


    }
}
