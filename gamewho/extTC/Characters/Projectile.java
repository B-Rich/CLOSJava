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
import gamewho.extTC.Characters.MpObj;
import gamewho.extTC.TileMap.TileMp;
import java.awt.Graphics2D;


public abstract class Projectile extends MpObj
{
  protected boolean hit;
  protected boolean rmv;
  protected int dmg;
  
  public Projectile(TileMp TM)
  {
    super(TM);
  }
  
  public int getDamage()
  {
    return this.dmg;
  }
  
  public boolean shouldRemove()
  {
    return this.rmv;
  }
  
  public abstract void setHit();
  
  public abstract void update();
  
  public void draw(Graphics2D g)
  {
    super.draw(g);
  }
}

