/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 Stage exit*/
package gamewho.extTC.Characters;

import gamewho.extTC.TileMap.TileMp;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Threadcount
 */
public class HomeBase extends MpObj{
    
    private BufferedImage[] sprts;
    
    public HomeBase(TileMp TM){
        super(TM);
        this.facingRight = true;
        this.wdth = (this.hght = 40);
        this.cwdth = 20;
        this.chght = 40;
        try{
            //BufferedImage sprtsht = ImageIO.read(getClass().getResourceAsStream(null));
            BufferedImage sprtsht = ImageIO.read(new FileInputStream("Sprites/Player/Teleport.gif"));
            this.sprts = new BufferedImage[9]; // 3'yaro
            for(int i = 0 ; i < this.sprts.length ; i++){
                this.sprts[i] = sprtsht.getSubimage(i * this.wdth, 0, this.wdth, this.hght);
            }
            this.anm.setFrames(this.sprts);
            this.anm.setDelay(1);
        }
        catch(Exception excptn){
            excptn.printStackTrace();
        }
    }
    
    public void update(){
        this.anm.update();
    }
    
    public void draw(Graphics2D grphcs){
        super.draw(grphcs);
    }
}
