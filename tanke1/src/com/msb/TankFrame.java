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
public class  TankFrame extends Frame {

   Tank myTank = new Tank(200,200,Dir.DOWN,this);
   Bullet bullet = new Bullet(300,300,Dir.DOWN);

   static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public TankFrame(){

        setSize(GAME_WIDTH,GAME_HEIGHT);
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

    Image offScreenImage = null;

    @Override
    //用双缓冲解决闪烁问题
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage =  this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics goffScreen = offScreenImage.getGraphics();
        Color c = goffScreen.getColor();
        goffScreen.setColor(Color.BLACK);
        goffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        goffScreen.setColor(c);
        paint(goffScreen);
        g.drawImage(offScreenImage,0,0,null);



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

                case KeyEvent.VK_CONTROL:
                    myTank.fire();
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
