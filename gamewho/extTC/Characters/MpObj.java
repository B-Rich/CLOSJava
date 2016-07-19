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
import gamewho.extTC.Characters.Anmtn;
import gamewho.extTC.TileMap.TileMp;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class MpObj
{
  protected TileMp tileMap;
  protected int tileSize;
  protected double xmap;
  protected double ymap;
  protected double x;
  protected double y;
  protected double dx;
  protected double dy;
  protected int wdth;
  protected int hght;
  protected int cwdth;
  protected int chght;
  protected int currRow;
  protected int currCol;
  protected double xdest;
  protected double ydest;
  protected double xtemp;
  protected double ytemp;
  protected boolean topLeft;
  protected boolean topRight;
  protected boolean bottomLeft;
  protected boolean bottomRight;
  protected Anmtn anm;
  protected int currentAction;
  protected int previousAction;
  protected boolean facingRight;
  protected boolean left;
  protected boolean right;
  protected boolean up;
  protected boolean down;
  protected boolean jumping;
  protected boolean falling;
  protected double moveSpeed;
  protected double maxSpeed;
  protected double stopSpeed;
  protected double fallSpeed;
  protected double maxFallSpeed;
  protected double jumpStart;
  protected double stopJumpSpeed;
  
  public MpObj(TileMp tm)
  {
    this.tileMap = tm;
    this.tileSize = tm.getTileSize();
    this.anm = new Anmtn();
    this.facingRight = true;
  }
  
  public boolean intersects(MpObj o)
  {
    Rectangle r1 = getRectangle();
    Rectangle r2 = o.getRectangle();
    return r1.intersects(r2);
  }
  
  public boolean intersects(Rectangle r)
  {
    return getRectangle().intersects(r);
  }
  
  public boolean contains(MpObj o)
  {
    Rectangle r1 = getRectangle();
    Rectangle r2 = o.getRectangle();
    return r1.contains(r2);
  }
  
  public boolean contains(Rectangle r)
  {
    return getRectangle().contains(r);
  }
  
  public Rectangle getRectangle()
  {
    return new Rectangle(
      (int)this.x - this.cwdth / 2, 
      (int)this.y - this.chght / 2, 
      this.cwdth, 
      this.chght);
  }
  
  public void calculateCorners(double x, double y)
  {
    int leftTile = (int)(x - this.cwdth / 2) / this.tileSize;
    int rightTile = (int)(x + this.cwdth / 2 - 1.0D) / this.tileSize;
    int topTile = (int)(y - this.chght / 2) / this.tileSize;
    int bottomTile = (int)(y + this.chght / 2 - 1.0D) / this.tileSize;
    if ((topTile < 0) || (bottomTile >= this.tileMap.getNumRows()) || 
      (leftTile < 0) || (rightTile >= this.tileMap.getNumCols()))
    {
      this.topLeft = this.topRight = this.bottomLeft = this.bottomRight = false;
      return;
    }
    int tl = this.tileMap.getType(topTile, leftTile);
    int tr = this.tileMap.getType(topTile, rightTile);
    int bl = this.tileMap.getType(bottomTile, leftTile);
    int br = this.tileMap.getType(bottomTile, rightTile);
    this.topLeft = (tl == 1);
    this.topRight = (tr == 1);
    this.bottomLeft = (bl == 1);
    this.bottomRight = (br == 1);
  }
  
  public void checkTileMpCollision()
  {
    this.currCol = ((int)this.x / this.tileSize);
    this.currRow = ((int)this.y / this.tileSize);
    
    this.xdest = (this.x + this.dx);
    this.ydest = (this.y + this.dy);
    
    this.xtemp = this.x;
    this.ytemp = this.y;
    
    calculateCorners(this.x, this.ydest);
    if (this.dy < 0.0D) {
      if ((this.topLeft) || (this.topRight))
      {
        this.dy = 0.0D;
        this.ytemp = (this.currRow * this.tileSize + this.chght / 2);
      }
      else
      {
        this.ytemp += this.dy;
      }
    }
    if (this.dy > 0.0D) {
      if ((this.bottomLeft) || (this.bottomRight))
      {
        this.dy = 0.0D;
        this.falling = false;
        this.ytemp = ((this.currRow + 1) * this.tileSize - this.chght / 2);
      }
      else
      {
        this.ytemp += this.dy;
      }
    }
    calculateCorners(this.xdest, this.y);
    if (this.dx < 0.0D) {
      if ((this.topLeft) || (this.bottomLeft))
      {
        this.dx = 0.0D;
        this.xtemp = (this.currCol * this.tileSize + this.cwdth / 2);
      }
      else
      {
        this.xtemp += this.dx;
      }
    }
    if (this.dx > 0.0D) {
      if ((this.topRight) || (this.bottomRight))
      {
        this.dx = 0.0D;
        this.xtemp = ((this.currCol + 1) * this.tileSize - this.cwdth / 2);
      }
      else
      {
        this.xtemp += this.dx;
      }
    }
    if (!this.falling)
    {
      calculateCorners(this.x, this.ydest + 1.0D);
      if ((!this.bottomLeft) && (!this.bottomRight)) {
        this.falling = true;
      }
    }
  }
  
  public int getX()
  {
    return (int)this.x;
  }
  
  public int getY()
  {
    return (int)this.y;
  }
  
  public int getWidth()
  {
    return this.wdth;
  }
  
  public int getHeight()
  {
    return this.hght;
  }
  
  public int getCWidth()
  {
    return this.cwdth;
  }
  
  public int getCHeight()
  {
    return this.chght;
  }
  
  public boolean isFacingRight()
  {
    return this.facingRight;
  }
  
  public void setPosition(double x, double y)
  {
    this.x = x;
    this.y = y;
  }
  
  public void setVector(double dx, double dy)
  {
    this.dx = dx;
    this.dy = dy;
  }
  
  public void setMapPosition()
  {
    this.xmap = this.tileMap.getX();
    this.ymap = this.tileMap.getY();
  }
  
  public void setLeft(boolean b)
  {
    this.left = b;
  }
  
  public void setRight(boolean b)
  {
    this.right = b;
  }
  
  public void setUp(boolean b)
  {
    this.up = b;
  }
  
  public void setDown(boolean b)
  {
    this.down = b;
  }
  
  public void setJumping(boolean b)
  {
    this.jumping = b;
  }
  
  public boolean notOnScreen()
  {
    return (this.x + this.xmap + this.wdth < 0.0D) || (this.x + this.xmap - this.wdth > 320.0D) || (this.y + this.ymap + this.hght < 0.0D) || (this.y + this.ymap - this.hght > 240.0D);
  }
  
  public void draw(Graphics2D g)
  {
    setMapPosition();
    if (this.facingRight) {
      g.drawImage(
        this.anm.getImage(), 
        (int)(this.x + this.xmap - this.wdth / 2), 
        (int)(this.y + this.ymap - this.hght / 2), 
        null);
    } else {
      g.drawImage(
        this.anm.getImage(), 
        (int)(this.x + this.xmap - this.wdth / 2 + this.wdth), 
        (int)(this.y + this.ymap - this.hght / 2), 
        -this.wdth, 
        this.hght, 
        null);
    }
  }
}

