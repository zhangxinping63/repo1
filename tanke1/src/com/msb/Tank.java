package com.msb;

import com.sun.xml.internal.bind.util.Which;

import java.awt.*;
import java.util.Random;

/**
 * @Author: Zhangxp
 * @Date: 2020-04-24 11:03
 */
public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;

    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

    Rectangle rect = new Rectangle();

    private boolean moving = true;
    private TankFrame tf = null;
    private boolean living = true;
    //tank随机方向
    private Random random = new Random();

    private Group group = Group.BAD;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }





    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

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
        if(!living){
            tf.tanks.remove(this);
        }

        switch (dir){
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y,null);
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


        //自动发生子弹
        if(this.group == Group.BAD && random.nextInt(100) > 95){
            this.fire();
        }
        if(this.group == Group.BAD && random.nextInt(100) > 95){
            randomDir();
        }
       // randomDir();

        //边界检测
        boundsCheck();

        //update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if(this.x < 2) {
            x = 2;
        }
        if(this.y < 28) {
            y = 28;
        }
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) {
            x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        }
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) {
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT -2;
        }


    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    /*private void randomDir() {
    }*/

    public void fire() {
        int bX = this.x + Tank.WIDTH/2 -Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 -Bullet.HEIGHT/2;
       tf.bullets.add( new Bullet(bX, bY, this.dir,this.group, this.tf));

    }

    public void die() {
        this.living = false;
    }
}
