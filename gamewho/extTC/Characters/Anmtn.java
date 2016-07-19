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

import java.awt.image.BufferedImage;

public class Anmtn
{
  private BufferedImage[] frms;
  private int crrnt;
  private int nmfrms;
  private int cnt;
  private int dl;
  private int tmpld;
  
  public Anmtn()
  {
    this.tmpld = 0;
  }
  
  public void setFrames(BufferedImage[] frms)
  {
    this.frms = frms;
    this.crrnt = 0;
    this.cnt = 0;
    this.tmpld = 0;
    this.dl = 2;
    this.nmfrms = frms.length;
  }
  
  public void setDelay(int i)
  {
    this.dl = i;
  }
  
  public void setFrame(int i)
  {
    this.crrnt = i;
  }
  
  public void setNumFrames(int i)
  {
    this.nmfrms = i;
  }
  
  public void update()
  {
    if (this.dl == -1) {
      return;
    }
    this.cnt += 1;
    if (this.cnt == this.dl)
    {
      this.crrnt += 1;
      this.cnt = 0;
    }
    if (this.crrnt == this.nmfrms)
    {
      this.crrnt = 0;
      this.tmpld += 1;
    }
  }
  
  public int getFrame()
  {
    return this.crrnt;
  }
  
  public int getCount()
  {
    return this.cnt;
  }
  
  public BufferedImage getImage()
  {
    return this.frms[this.crrnt];
  }
  
  public boolean hasPlayedOnce()
  {
    return this.tmpld > 0;
  }
  
  public boolean hasPlayed(int i)
  {
    return this.tmpld == i;
  }
}

