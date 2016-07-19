/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.Characters.Slugs;

/**
 *
 * @author Threadcount
 */
import gamewho.extTC.Characters.Anmtn;
import gamewho.extTC.Characters.Foe;
import gamewho.extTC.Handlers.Content;
import gamewho.extTC.TileMap.TileMp;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Gazer extends Foe {
  private BufferedImage[] idlSprts;
  private int tick;
  private double a;
  private double b;
  
  public Gazer(TileMp tm)
  {
    super(tm);
    
    this.hp = (this.maxHp = 2);
    
    this.wdth = 39;
    this.hght = 20;
    this.cwdth = 25;
    this.chght = 15;
    
    this.dmg = 1;
    this.moveSpeed = 5.0D;
    
    this.idlSprts = Content.Gazer[0];
    
    this.anm.setFrames(this.idlSprts);
    this.anm.setDelay(4);
    
    this.tick = 0;
    this.a = (Math.random() * 0.06D + 0.07000000000000001D);
    this.b = (Math.random() * 0.06D + 0.07000000000000001D);
  }
  
  public void update()
  {
    if (this.flnch)
    {
      this.flnchcnt += 1L;
      if (this.flnchcnt == 6L) {
        this.flnch = false;
      }
    }
    this.tick += 1;
    this.x = (Math.sin(this.a * this.tick) + this.x);
    this.y = (Math.sin(this.b * this.tick) + this.y);
    

    this.anm.update();
  }
  
  public void draw(Graphics2D g)
  {
    if ((this.flnch) && (
      (this.flnchcnt == 0L) || (this.flnchcnt == 2L))) {
      return;
    }
    super.draw(g);
  }
}

