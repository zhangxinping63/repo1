package com.msb;

import java.awt.*;
import java.util.HashMap;

/**
 * @Author: Zhangxp
 * @Date: 2020-04-24 14:09
 */
public class Bullet {
    //子弹速度
    private static final int SPEED = 10;
    //炮弹宽度，高度
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    //子弹位置
    private int x,y;
    //子弹方向
    private Dir dir;

    public boolean isLive() {
        return living;
    }

    public void setLive(boolean live) {
        this.living = live;
    }
    //定义子弹活着
    private boolean living = true;
    TankFrame tf = null;



    private Group group = Group.BAD;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    //构造方法
    public  Bullet(int x, int y, Dir dir,Group group,TankFrame tf){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }
    public void paint(Graphics g) {
        if(!living){
            tf.bullets.remove(this);
        }

        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }
        /*if(!live){
            tf.bullets.remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);*/
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
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
    }

    //碰撞检测
    public void collideWith(Tank tank) {

        if(this.group == tank.getGroup()){
            return;
        }

        //TOOD:用一个rect来记录子弹的位置
        Rectangle rect1 = new Rectangle(this.x,this.y,WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT );
        if(rect1.intersects(rect2)){
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH/2 -Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 -Explode.HEIGHT/2;
            tf.explodes.add(new Explode(eX, eY, tf));
        }
    }

    private void die() {
        this.living = false;
    }
}
