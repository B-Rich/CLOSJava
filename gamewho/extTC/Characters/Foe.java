/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.Characters;

import gamewho.extTC.Characters.MpObj;
import gamewho.extTC.Sound.BeatBox;
import gamewho.extTC.TileMap.TileMp;

/**
 *
 * @author Threadcount
 */
public class Foe extends MpObj {

    protected int hp;
    protected int maxHp;
    protected boolean rip;
    protected int dmg;
    protected boolean out;
    protected boolean flnch;
    protected long flnchcnt;

    public Foe(TileMp TM) {
        super(TM);
        this.out = false;
    }

    public boolean isKill() {
        return this.rip;
    }

    public boolean shouldOut() {
        return this.out;
    }

    public int getDmg() {
        return this.dmg;
    }

    public void hit(int dmg) {
        if ((this.rip) || (this.flnch)) {
            return;
        }
        BeatBox.play(null);
        this.hp -= dmg;
        if (this.hp < 0) {
            this.hp = 0;
        }
        if (this.hp == 0) {
            this.rip = true;
        }
        if(this.rip){
            this.out = true;
        }
        this.flnch = true;
        this.flnchcnt = 0L;
    }
    
    public void update(){}
}
