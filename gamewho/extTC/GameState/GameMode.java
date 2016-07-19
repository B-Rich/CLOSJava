/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.GameState;

import java.awt.Graphics2D;

/**
 *
 * @author Threadcount
 */
public abstract class GameMode {
     public ModeManager MdMngr;
     
     public GameMode(ModeManager M){
         this.MdMngr = M;
     }
     
     public abstract void init();
     
     public abstract void update();
     
      public abstract void draw(Graphics2D g2d);
      
       public abstract void ModeHandler();
      
}
