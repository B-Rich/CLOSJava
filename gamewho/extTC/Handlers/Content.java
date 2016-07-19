/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.Handlers;

/**
 *
 * @author Threadcount
 */
import java.awt.image.BufferedImage;
import java.io.PrintStream;
import javax.imageio.ImageIO;

public class Content
{
  public static BufferedImage[][] Particle = load("/Sprites/Player/EnergyParticle.gif", 5, 5);
  public static BufferedImage[][] Booms = load("/Sprites/Enemies/Explosion.gif", 30, 30);
  public static BufferedImage[][] Gazer = load("/Sprites/Enemies/Gazer.gif", 39, 20);
  public static BufferedImage[][] Tengu = load("/Sprites/Enemies/Tengu.gif", 30, 30);
  public static BufferedImage[][] GelPop = load("/Sprites/Enemies/GelPop.gif", 25, 25);
  public static BufferedImage[][] DarkEnergy = load("/Sprites/Enemies/DarkEnergy.gif", 20, 20);
  
  public static BufferedImage[][] load(String strng, int w, int h)
  {
    try
    {
      BufferedImage sprtsht = ImageIO.read(Content.class.getResourceAsStream(strng));
      int wdth = sprtsht.getWidth() / w;
      int hght = sprtsht.getHeight() / h;
      BufferedImage[][] ret = new BufferedImage[hght][wdth];
      for (int i = 0; i < hght; i++) {
        for (int j = 0; j < wdth; j++) {
          ret[i][j] = sprtsht.getSubimage(j * w, i * h, w, h);
        }
      }
      return ret;
    }
    catch (Exception excptn)
    {
      excptn.printStackTrace();
      System.out.println("Error loading graphics !.");
      System.exit(0);
    }
    return null;
  }
}
