/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.Characters;

import gamewho.extTC.Characters.MpObj;
import gamewho.extTC.Handlers.Content;
import gamewho.extTC.TileMap.TileMp;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Threadcount
 */
public class Particle extends MpObj {

    private int cnt;
    private boolean rmv;
    private BufferedImage[] sprts;
    public static int Up = 0;
    public static int Left = 1;
    public static int Down = 2;
    public static int Right = 3;

    public Particle(TileMp TM, double x, double y, int drctn) {
        super(TM);
        this.x = x;
        this.y = y;
        double dbl1 = Math.random() * 2.5D - 1.25D;
        double dbl2 = -Math.random() - 0.8D;
        switch (drctn) {
            case (0):
                this.dx = dbl1;
                this.dy = dbl2;
                break;
            case (1):
                this.dx = dbl2;
                this.dy = dbl1;
                break;
            case (2):
                this.dx = dbl1;
                this.dy = -(dbl2);
                break;
            case (3):
                this.dx = -(dbl2);
                this.dy = dbl1;
                break;
        }

        /*if(d = 0){
         this.dx = dbl1;
         this.dy = dbl2;
         }
         else if (d = 1){
         this.dx = dbl2;
         this.dy = dbl1;
         }
         else if(d = 2){
         this.dx = dbl1;
         this.dy = -(dbl2);
         }
         else{
         this.dx = -(dbl2);
         this.dy = dbl1;
         }*/
        this.cnt = 0;
        this.sprts = Content.Particle[0];
        this.anm.setFrames(this.sprts);
        this.anm.setDelay(-1);
    }

    public void update() {
        this.x += this.dx;
        this.y += this.dy;
        this.cnt += 1;
        if (this.cnt == 60) {
            this.rmv = true;
        }
    }

    public boolean shouldRemove() {
        return this.rmv;
    }

    public void draw(Graphics2D grphcs) {
        super.draw(grphcs);
    }

}
