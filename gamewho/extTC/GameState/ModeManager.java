/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.GameState;

import gamewho.extTC.Sound.BeatBox;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Threadcount
 */
public class ModeManager {
    private GameMode[] Modes;
    private int crrnt;
    private Pause p;
    private boolean Paused;
    public static final int States = 16;
    public static final int Menu = 0;
    public static final int Sector_1 = 2;
    public static final int Sector_2 = 3;
    public static final int Sector_3 = 4;
    public static final int Freeze = 15;
    
    public void LoadMode(int M){
        if(M == 0){
            this.Modes[M] = new Menu(this);
        }
        else if (M == 2){
            this.Modes[M] = new Sector_1(this);
        }
        else if (M == 3){
            this.Modes[M] = new Sector_2(this);
        }
        else if (M == 4){
            this.Modes[M] = new Sector_3(this);
        }
        else if (M == 15){
            this.Modes[M] = new Freeze(this);
        }
    }
    
    public void ClearMode(int M){
        this.Modes[M] = null;
    }
    
    public void setMode(int M){
        ClearMode(this.crrnt);
        this.crrnt = M;
        LoadMode(this.crrnt);
    }
    
    public void Pawzd(boolean b){
        this.Paused = b;
    }
    
    public void update(){
        if(this.Paused){
            this.p.update();
            return;
        }
        if(this.Modes[this.crrnt] != null){
            this.Modes[this.crrnt].update();
        }
    }
    
    public void draw(Graphics2D grphcs){
         if(this.Paused){
            this.p.draw(grphcs);
            return;
        }
        if(this.Modes[this.crrnt] != null){
            this.Modes[this.crrnt].draw(grphcs);
        }
        else{
            grphcs.setColor(Color.GRAY);
            grphcs.fillRect(0,0,320,240);
        }
    }
    
    public ModeManager(){
        BeatBox.init();
        this.Modes = new GameMode[16];
        this.p = new Pause(this);
        this.Paused = false;
        this.crrnt = 0;
        LoadMode(this.crrnt);
    }
    
}
