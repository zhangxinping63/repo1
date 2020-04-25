package com.msb;

import com.sun.xml.internal.bind.util.Which;

import java.awt.*;

/**
 * @Author: Zhangxp
 * @Date: 2020-04-24 11:03
 */
public class Tank {
    private int x = 200, y = 200;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private TankFrame tf = null;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private boolean moving = false;



    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public static int getSPEED() {
        return SPEED;
    }

    //画坦克
    public void paint(Graphics g) {
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
        }
        //Color c = g.getColor();

       // g.setColor(Color.YELLOW);

       // g.fillRect(x,y,50,50);
       // g.setColor(c);
        move();
    }

    private void move() {
        if(!moving) return;
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
    }

    public void fire() {
       tf.bullets.add( new Bullet(this.x,this.y,this.dir,this.tf));

    }
}
