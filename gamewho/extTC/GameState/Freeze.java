/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.GameState;

import gamewho.extTC.Handlers.Keys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author Threadcount
 */
public class Freeze extends GameMode {
    private float hue;
    private Color clr;
    private double angl;
    private BufferedImage img;
    
    public Freeze(ModeManager MM){
        super(MM);
        try{
            //this.img = ImageIO.read(getClass().getResourceAsStream(null)).getSubimage();
        }
        catch(Exception excptn){}
    }
    
    public void init(){}
    
    public void ModeHandler(){
        if(Keys.isPressed(Keys.Escape)){
            this.MdMngr.setMode(0);
        }
    }
    
    public void update(){
        ModeHandler();
        this.clr = Color.getHSBColor(this.hue, 1.0F, 1.0F);
        this.hue = ((float)(this.hue + 0.01D));
        if(this.hue > 1.0F){
            this.hue = 0.0F;
        }
        this.angl += 0.1D;
    }
    
    public void draw(Graphics2D grphcs){
        grphcs.setColor(this.clr);
        grphcs.fillRect(0, 0, 320, 240);
        AffineTransform at = new AffineTransform();
        at.translate(160.0D, 120.0D);
        at.rotate(this.angl);
        grphcs.drawImage(this.img, at, null);
    }
    
}
