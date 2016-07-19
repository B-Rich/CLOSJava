/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Threadcount
 */
public class Bckgrnd {
    private double x;
    private double y;
    private double dx;
    private double dy;
    private int wdth;
    private int hght;
    private double xscl;
    private double yscl;
    private BufferedImage img;
    
    public Bckgrnd(String strng, double dbl1, double dbl2){
        try{
            this.img = ImageIO.read(getClass().getResourceAsStream(strng));
            //this.img = ImageIO.read(new FileInputStream(strng));
            this.wdth = this.img.getWidth();
            this.hght = this.img.getHeight();
            this.xscl = dbl1;
            this.yscl = dbl2;
        }
        catch(Exception excptn){
            excptn.printStackTrace();
        }
    }
    
    public Bckgrnd(String strng, double s, int x, int y, int w, int h){
        try{
            this.img = ImageIO.read(getClass().getResourceAsStream(strng));
            //this.img = ImageIO.read(new FileInputStream(strng));
            this.img = this.img.getSubimage(x,y,w,h);
            this.wdth = this.img.getWidth();
            this.hght = this.img.getHeight();
            this.xscl = s;
            this.yscl = s;
        }
        catch(Exception excptn){
            excptn.printStackTrace();
        }
    }
    
    public Bckgrnd(String strng, double dbl){
        this(strng, dbl, dbl);
    }
    
    public Bckgrnd(String strng){
        this(strng, 0.1D);
    }
    
    public void setPosition(double x, double y){
        this.x = (x * this.xscl % this.wdth);
        this.y = (y * this.yscl % this.hght);
    }
    
    public void setVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }
    
    public void setScale(double xscl, double yscl){
        this.xscl = xscl;
        this.yscl = yscl;
    }
    
    public void setDimensions(int i1, int i2){
        this.wdth = i1;
        this.hght = i2;
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
    
    public void update(){
        this.x += this.dx;
        while(this.x <= -this.wdth){
            this.x += this.wdth;
        }
        while(this.x >= this.wdth){
            this.x -= this.wdth;
        }
        this.y += this.dy;
        while(this.y <= -this.hght){
            this.y += this.hght;
        }
        while(this.y >= this.hght){
            this.y -= this.hght;
        }
    }
    
    public void draw(Graphics2D grphcs){
        grphcs.drawImage(this.img, (int)this.x, (int)this.y, null);
        if(this.x < 0.0D){
            grphcs.drawImage(this.img, (int)this.x + 320, (int)this.y, null);
        }
        if(this.x > 0.0D){
            grphcs.drawImage(this.img, (int)this.x - 320, (int)this.y, null);
        }
        if(this.y < 0.0D){
            grphcs.drawImage(this.img, (int)this.x , (int)this.y + 240, null);
        }
        if(this.y > 0.0D){
            grphcs.drawImage(this.img, (int)this.x, (int)this.y - 240, null);
        }
    }
}
