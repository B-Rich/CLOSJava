/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.Characters;

/**
 *
 * @author Threadcount
 */
import gamewho.extTC.Handlers.Content;
import gamewho.extTC.TileMap.TileMp;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Booms extends MpObj{
  private BufferedImage[] sprts;
  private boolean rmv;
  private Point[] pnts;
  private int spd;
  private double dSpd;
  
  public Booms(TileMp tm, int x, int y)
  {
    super(tm);
    
    this.x = x;
    this.y = y;
    
    this.wdth = 30;
    this.hght = 30;
    
    this.spd = 2;
    this.dSpd = 1.41D;
    
    this.sprts = Content.Booms[0];
    
    this.anm.setFrames(this.sprts);
    this.anm.setDelay(6);
    
    this.pnts = new Point[8];
    for (int i = 0; i < this.pnts.length; i++) {
      this.pnts[i] = new Point(x, y);
    }
  }
  
  public void update()
  {
    this.anm.update();
    if (this.anm.hasPlayedOnce()) {
      this.rmv = true;
    }
    this.pnts[0].x += this.spd; Point 
      tmp46_45 = this.pnts[1];tmp46_45.x = ((int)(tmp46_45.x + this.dSpd)); Point 
      tmp66_65 = this.pnts[1];tmp66_65.y = ((int)(tmp66_65.y + this.dSpd));
    this.pnts[2].y += this.spd; Point 
      tmp104_103 = this.pnts[3];tmp104_103.x = ((int)(tmp104_103.x - this.dSpd)); Point 
      tmp124_123 = this.pnts[3];tmp124_123.y = ((int)(tmp124_123.y + this.dSpd));
    this.pnts[4].x -= this.spd; Point 
      tmp162_161 = this.pnts[5];tmp162_161.x = ((int)(tmp162_161.x - this.dSpd)); Point 
      tmp182_181 = this.pnts[5];tmp182_181.y = ((int)(tmp182_181.y - this.dSpd));
    this.pnts[6].y -= this.spd; Point 
      tmp222_221 = this.pnts[7];tmp222_221.x = ((int)(tmp222_221.x + this.dSpd)); Point 
      tmp243_242 = this.pnts[7];tmp243_242.y = ((int)(tmp243_242.y - this.dSpd));
  }
  
  public boolean shouldRemove()
  {
    return this.rmv;
  }
  
  public void draw(Graphics2D g)
  {
    setMapPosition();
    for (int i = 0; i < this.pnts.length; i++) {
      g.drawImage(
        this.anm.getImage(), 
        (int)(this.pnts[i].x + this.xmap - this.wdth / 2), 
        (int)(this.pnts[i].y + this.ymap - this.hght / 2), 
        null);
    }
  }
}

