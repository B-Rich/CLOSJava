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
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Title
{
  public BufferedImage img;
  public int cnt;
  private boolean dn;
  private boolean rmv;
  private double x;
  private double y;
  private double dx;
  private int wdth;
  
  public Title(String strng)
  {
    try
    {
      this.img = ImageIO.read(getClass().getResourceAsStream(strng));
      this.wdth = this.img.getWidth();
      this.x = (-this.wdth);
      this.dn = false;
    }
    catch (Exception excptn)
    {
      excptn.printStackTrace();
    }
  }
  
  public Title(BufferedImage img)
  {
    this.img = img;
    this.wdth = img.getWidth();
    this.x = (-this.wdth);
    this.dn = false;
  }
  
  public void setY(double y)
  {
    this.y = y;
  }
  
  public void begin()
  {
    this.dx = 10.0D;
  }
  
  public boolean shouldRemove()
  {
    return this.rmv;
  }
  
  public void update()
  {
    if (!this.dn)
    {
      if (this.x >= (320 - this.wdth) / 2)
      {
        this.x = ((320 - this.wdth) / 2);
        this.cnt += 1;
        if (this.cnt >= 120) {
          this.dn = true;
        }
      }
      else
      {
        this.x += this.dx;
      }
    }
    else
    {
      this.x += this.dx;
      if (this.x > 320.0D) {
        this.rmv = true;
      }
    }
  }
  
  public void draw(Graphics2D grphcs)
  {
    grphcs.drawImage(this.img, (int)this.x, (int)this.y, null);
  }
}
