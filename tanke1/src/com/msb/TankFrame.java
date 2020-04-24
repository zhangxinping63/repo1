package com.msb;

import com.sun.org.apache.bcel.internal.generic.FASTORE;
import javafx.beans.binding.BooleanExpression;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: Zhangxp
 * @Date: 2020-04-23 21:03
 */
public class TankFrame extends Frame {

   Tank myTank = new Tank(200,200,Dir.DOWN);
   Bullet bullet = new Bullet(300,300,Dir.DOWN);

    public TankFrame(){

        setSize(800,600);
        setResizable(false);
        setTitle("Tank war");
        setVisible(true);

        //调用内部类MyKeyListener  自己定义的一个键盘类
        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //坦克移动坐标
    @Override
    public void paint(Graphics g){
        myTank.paint(g);
        //画出子弹
        bullet.paint(g);


    }

    class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        //键盘摁下去调用
        @Override
        public void keyPressed(KeyEvent e) {
           int key = e.getKeyCode();
           switch (key){
               case KeyEvent.VK_LEFT:
                   bL = true;
                   break;
               case KeyEvent.VK_UP:
                   bU = true;
                   break;
               case KeyEvent.VK_RIGHT:
                   bR = true;
                   break;
               case KeyEvent.VK_DOWN:
                   bD = true;
                   break;
                   default:
                       break;
           }
           setMainTankDir();
        }
        //键盘抬起时调用
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                    default:
                        break;
            }
            setMainTankDir();
        }

        //定义方向键
        public void setMainTankDir(){
            if(!bL && !bU && !bR && !bD){
                myTank.setMoving(false);
            }else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}
