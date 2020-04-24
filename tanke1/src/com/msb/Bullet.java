package com.msb;

import java.awt.*;

/**
 * @Author: Zhangxp
 * @Date: 2020-04-24 14:09
 */
public class Bullet {
    //子弹速度
    private static final int SPEED = 10;
    //炮弹宽度，高度
    private static final int WIDTH = 20, HEIGHT = 20;
    //子弹位置
    private int x,y;
    //子弹方向
    private Dir dir;
    //定义子弹活着
    private boolean live = true;
    TankFrame tf = null;
    //构造方法
    public  Bullet(int x, int y, Dir dir,TankFrame tf){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }
    public void paint(Graphics g) {

        if(!live){
            tf.bullets.remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);
        move();
    }
    private void move() {
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            default:
                break;
        }
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;
    }
}
