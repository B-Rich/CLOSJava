/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.GameState;

import gamewho.extTC.Handlers.Keys;
import java.awt.Font;
import java.awt.Graphics2D;
import javafx.scene.paint.Color;

/**
 *
 * @author Threadcount
 */
public class Pause extends GameMode {
    private Font f;
    
    public Pause(ModeManager MM){
        super(MM);
        this.f = new Font("Calibri", 0, 14);
    }
    
    public void init(){}
    
    public void update(){
        ModeHandler();
    }
    
    public void draw(Graphics2D grphcs){
        grphcs.setColor(java.awt.Color.GRAY);
        grphcs.fillRect(0, 0, 320, 240);
        grphcs.setColor(java.awt.Color.WHITE);
        grphcs.setFont(this.f);
        grphcs.drawString("Game Paused", 90, 90);
    }
    
    public void ModeHandler(){
        if(Keys.isPressed(Keys.Escape)){
            this.MdMngr.Pawzd(false);
        }
        if(Keys.isPressed(Keys.btna)){
            this.MdMngr.Pawzd(false);
            this.MdMngr.setMode(0);
        }
    }
}
