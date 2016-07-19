/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.Main;

import gamewho.extTC.Handlers.Keys;
import gamewho.extTC.GameState.ModeManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 *
 * @author ThreadCount
 */
public class GmPnl extends JPanel implements Runnable, KeyListener{
    public static final int x = 320;
    public static final int y = 240;
    public static final int upscl = 2;
    private Thread thrd;
    private boolean rnnng;
    private int FPS = 60;
    private long tm = 1000/this.FPS;
    private BufferedImage img;
    private Graphics2D grphc;
    private ModeManager MM;
    private boolean rcrdng = false;
    private int rcrdngcnt = 0;
    private boolean scrnsht;
    
    public GmPnl(){
        setPreferredSize(new Dimension(640,480));
        setFocusable(true);
        requestFocus();
    }
    
    public void addNotify(){
        super.addNotify();
        if(this.thrd == null){
            this.thrd = new Thread(this);
            addKeyListener(this);
            this.thrd.start();
        }
    }
    
    private void init(){
        this.img = new BufferedImage(320,240,1);
        this.grphc = ((Graphics2D)this.img.getGraphics());
        this.rnnng = true;
        this.MM = new ModeManager();
        //
    }
    
    public void update(){
      this.MM.update();
      Keys.update();
    }
    
    public void draw(){
        this.MM.draw(this.grphc);
    }

    public void keyTyped(KeyEvent k){}
    
    public void keyPressed(KeyEvent k){
        if(k.isControlDown()){
            if(k.getKeyCode() ==  85){
              this.rcrdng = (!this.rcrdng);
              return;
            }
            
            if(k.getKeyCode() ==  86){
              this.scrnsht = true;
              return;
            }
        }
        Keys.keySet(k.getKeyCode(), true);
    }
    
    public void keyReleased(KeyEvent k){
      Keys.keySet(k.getKeyCode(), false);
    }
    
    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(this.img, 0, 0, 640, 480, null);
        g.dispose();
        if(this.scrnsht){
            this.scrnsht = false;
            try{
                File o = new File("ScreenShot" + System.nanoTime() + ".jpg");
                ImageIO.write(this.img, "jpg", o);
            }
            catch(Exception expctn){}
            if(!this.rcrdng)
                return;
            try{
                File o = new File("C:\\o\\vd" + this.rcrdngcnt + ".jpg");
                ImageIO.write(this.img, "jpg", o);
                this.rcrdngcnt++;
            }
            catch(Exception expctn){}
        }
    }
    

    public void run(){
        init();
        while(this.rnnng){
            long strt = System.nanoTime();
            
            update();
            draw();
            drawToScreen();
            
            long elpsd = System.nanoTime() - strt;
            long wait = this.tm - elpsd / 1000000l;
            if(wait < 0l)
                wait = 5l;
            try{
                Thread.sleep(wait);
            }
            catch(Exception excptn){
                excptn.printStackTrace();
            }
        }
    }
}
