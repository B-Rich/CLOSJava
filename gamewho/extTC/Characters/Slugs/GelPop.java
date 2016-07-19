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
import gamewho.extTC.Characters.Foe;
import gamewho.extTC.Characters.Plr;
import gamewho.extTC.Handlers.Content;
import gamewho.extTC.TileMap.TileMp;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GelPop extends Foe{
  private BufferedImage[] sprts;
  private Plr player;
  private boolean active;
  
  public GelPop(TileMp tm, Plr p)
  {
    super(tm);
    this.player = p;
    
    this.hp = (this.maxHp = 1);
    
    this.wdth = 25;
    this.hght = 25;
    this.cwdth = 20;
    this.chght = 18;
    
    this.dmg = 1;
    this.moveSpeed = 0.8D;
    this.fallSpeed = 0.15D;
    this.maxFallSpeed = 4.0D;
    this.jumpStart = -5.0D;
    
    this.sprts = Content.GelPop[0];
    
    this.anm.setFrames(this.sprts);
    this.anm.setDelay(4);
    
    this.left = true;
    this.facingRight = false;
  }
  
  private void getNextPosition()
  {
    if (this.left) {
      this.dx = (-this.moveSpeed);
    } else if (this.right) {
      this.dx = this.moveSpeed;
    } else {
      this.dx = 0.0D;
    }
    if (this.falling)
    {
      this.dy += this.fallSpeed;
      if (this.dy > this.maxFallSpeed) {
        this.dy = this.maxFallSpeed;
      }
    }
    if ((this.jumping) && (!this.falling)) {
      this.dy = this.jumpStart;
    }
  }
  
  public void update()
  {
    if (!this.active)
    {
      if (Math.abs(this.player.getX() - this.x) < 320.0D) {
        this.active = true;
      }
      return;
    }
    if (this.flnch)
    {
      this.flnchcnt += 1L;
      if (this.flnchcnt == 6L) {
        this.flnch = false;
      }
    }
    getNextPosition();
    checkTileMpCollision();
    calculateCorners(this.x, this.ydest + 1.0D);
    if (!this.bottomLeft)
    {
      this.left = false;
      this.right = (this.facingRight = true);
    }
    if (!this.bottomRight)
    {
      this.left = true;
      this.right = (this.facingRight = false);
    }
    setPosition(this.xtemp, this.ytemp);
    if (this.dx == 0.0D)
    {
      this.left = (!this.left);
      this.right = (!this.right);
      this.facingRight = (!this.facingRight);
    }
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
