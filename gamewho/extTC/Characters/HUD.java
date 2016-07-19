/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.Characters;
/**
 *
 * @author ThreadCount
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class HUD
{
  private Plr plr;
  private BufferedImage hlth;
  private BufferedImage hp;
  
  public HUD(Plr p)
  {
    this.plr = p;
    try
    {
      BufferedImage image = ImageIO.read(
        getClass().getResourceAsStream(
        "/HUD/Hud.gif"));
      

      this.hlth = image.getSubimage(0, 0, 13, 12);
      this.hp = image.getSubimage(0, 12, 12, 11);
    }
    catch (Exception excptn)
    {
      excptn.printStackTrace();
    }
  }
  
  public void draw(Graphics2D g)
  {
    for (int i = 0; i < this.plr.getHealth(); i++) {
      g.drawImage(this.hlth, 10 + i * 15, 10, null);
    }
    for (int i = 0; i < this.plr.getLives(); i++) {
      g.drawImage(this.hp, 10 + i * 15, 25, null);
    }
    g.setColor(Color.WHITE);
    g.drawString(this.plr.getTimeToString(), 290, 15);
  }
}
