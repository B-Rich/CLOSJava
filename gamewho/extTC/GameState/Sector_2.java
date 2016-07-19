/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.GameState;

import gamewho.extTC.Sound.BeatBox;
import gamewho.extTC.Characters.Booms;
import gamewho.extTC.Characters.Foe;
import gamewho.extTC.Characters.Slugs.Gazer;
import gamewho.extTC.Characters.Slugs.GelPop;
import gamewho.extTC.Characters.HUD;
import gamewho.extTC.Characters.HomeBase;
import gamewho.extTC.Characters.Particle;
import gamewho.extTC.Characters.Plr;
import gamewho.extTC.Characters.Projectile;
import gamewho.extTC.Characters.Save;
import gamewho.extTC.Characters.Title;
import gamewho.extTC.Handlers.Keys;
import gamewho.extTC.TileMap.Bckgrnd;
import gamewho.extTC.TileMap.TileMp;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Threadcount
 */
public class Sector_2 extends GameMode {

    private Bckgrnd sky;
    private Bckgrnd clds;
    private Bckgrnd hll;
    private Plr plr;
    private TileMp tm;
    private ArrayList<Foe> enm;
    private ArrayList<Projectile> eprjctl;
    private ArrayList<Particle> eprtcl;
    private ArrayList<Booms> bm;
    private HUD hud;
    private BufferedImage txt;
    private Title ttl;
    private Title sbttl;
    private HomeBase hb;
    private boolean blockInput = false;
    private int eventCount = 0;
    private boolean eventStart;
    private boolean eventFinish;
    private boolean eventDead;
    private ArrayList<Rectangle> tb;

    public Sector_2(ModeManager MM) {
        super(MM);
        init();
    }

    public void init() {
        this.sky = new Bckgrnd(null, 0.0D);
        this.clds = new Bckgrnd(null, 0.1D);
        this.hll = new Bckgrnd(null, 0.2D);
        this.tm = new TileMp(30);
        this.tm.loadTiles("Tilesets/ruins.gif");
        this.tm.loadMap(null);
        this.tm.setPosition(140.0D, 0.0D);
        this.tm.setBounds(this.tm.getWidth() - 1 * this.tm.getTileSize(),
                this.tm.getHeight() - 2 * this.tm.getTileSize(),
                0, 0);
        this.tm.setTwn(1.0D);
        this.plr = new Plr(this.tm);
        //this.plr = new Plr(this.tm); // 2nd plr?
        this.plr.setHealth(Save.getHp());
        this.plr.setLives(Save.getLives());
        this.plr.setTime(Save.getTime());
        this.enm = new ArrayList();
        this.eprjctl = new ArrayList();
        populateEnemies();
        this.eprtcl = new ArrayList();
        this.plr.init(this.enm, this.eprtcl);
        this.bm = new ArrayList();
        this.hud = new HUD(this.plr);
        try {
            this.txt = ImageIO.read(getClass().getResourceAsStream(null));
            this.ttl = new Title(this.txt.getSubimage(0, 0, 170, 20));
            this.ttl.setY(60.0D);
            this.sbttl = new Title(this.txt.getSubimage(0, 20, 82, 13));
            this.sbttl.setY(85.0D);
        } catch (Exception excptn) {
            excptn.printStackTrace();
        }
        this.hb = new HomeBase(this.tm);
        this.hb.setPosition(3700.0D, 131.0D);
        this.eventStart = true;
        this.tb = new ArrayList();
        eventStart();
        BeatBox.load("/SFX/teleport.mp3", "teleport");
        BeatBox.load("/SFX/explode.mp3", "explode");
        BeatBox.load("/SFX/enemyhit.mp3", "enemyhit");
        BeatBox.load("/Music/level1.mp3", "level1");
        //BeatBox.loop("level1", 600, BeatBox.getFrame("level1") - 2200);
    }
        
      private void populateEnemies() {
        this.enm.clear();    
        GelPop gp = new GelPop(this.tm, this.plr);
        gp.setPosition(1300.0D, 100.0D);
        this.enm.add(gp);
        gp = new GelPop(this.tm, this.plr);
        gp.setPosition(1320.0D, 100.0D);
        this.enm.add(gp);
        gp = new GelPop(this.tm, this.plr);
        gp.setPosition(1340.0D, 100.0D);
        this.enm.add(gp);
        gp = new GelPop(this.tm, this.plr);
        gp.setPosition(1660.0D, 100.0D);
        this.enm.add(gp);
        gp = new GelPop(this.tm, this.plr);
        gp.setPosition(1680.0D, 100.0D);
        this.enm.add(gp);
        gp = new GelPop(this.tm, this.plr);
        gp.setPosition(1700.0D, 100.0D);
        this.enm.add(gp);
        gp = new GelPop(this.tm, this.plr);
        gp.setPosition(2177.0D, 100.0D);
        this.enm.add(gp);
        gp = new GelPop(this.tm, this.plr);
        gp.setPosition(2960.0D, 100.0D);
        this.enm.add(gp);
        gp = new GelPop(this.tm, this.plr);
        gp.setPosition(2980.0D, 100.0D);
        this.enm.add(gp);
        gp = new GelPop(this.tm, this.plr);
        gp.setPosition(3000.0D, 100.0D);
        this.enm.add(gp);
        Gazer g = new Gazer(this.tm);
        g.setPosition(2600.0D, 100.0D);
        this.enm.add(g);
        g = new Gazer(this.tm);
        g.setPosition(3500.0D, 100.0D);
        this.enm.add(g);
    }

    public void update() {
        ModeHandler();
        if (this.hb.contains(this.plr)) {
            this.eventFinish = (this.blockInput = true);
        }
        if ((this.plr.getHealth() == 0) || (this.plr.getY() > this.tm.getHeight())) {
            this.eventDead = (this.blockInput = true);
        }
        if (this.eventStart) {
            eventStart();
        }
        if (this.eventDead) {
            eventDead();
        }
        if (this.eventFinish) {
            eventFinish();
        }
        if (this.ttl != null) {
            this.ttl.update();
            if (this.ttl.shouldRemove()) {
                this.ttl = null;
            }
        }
        if (this.sbttl != null) {
            this.sbttl.update();
            if (this.sbttl.shouldRemove()) {
                this.sbttl = null;
            }
        }
        this.clds.setPosition(this.tm.getX(), this.tm.getY());
        this.hll.setPosition(this.tm.getX(), this.tm.getY());

        this.plr.update();

        this.tm.setPosition(
                160 - this.plr.getX(),
                120 - this.plr.getY());

        this.tm.update();
        this.tm.fixBounds();
        for (int i = 0; i < this.enm.size(); i++) {
            Foe e = (Foe) this.enm.get(i);
            e.update();
            if (e.isKill()) {
                this.enm.remove(i);
                i--;
                this.bm.add(new Booms(this.tm, e.getX(), e.getY()));
            }
        }
        for (int i = 0; i < this.eprjctl.size(); i++) {
            Projectile ep = (Projectile) this.eprjctl.get(i);
            ep.update();
            if (ep.shouldRemove()) {
                this.eprjctl.remove(i);
                i--;
            }
        }
        for (int i = 0; i < this.bm.size(); i++) {
            ((Booms) this.bm.get(i)).update();
            if (((Booms) this.bm.get(i)).shouldRemove()) {
                this.bm.remove(i);
                i--;
            }
        }
        this.hb.update();
    }

    public void draw(Graphics2D g) {
        this.sky.draw(g);
        this.clds.draw(g);
        this.hll.draw(g);

        this.tm.draw(g);
        for (int i = 0; i < this.enm.size(); i++) {
            ((Foe) this.enm.get(i)).draw(g);
        }
        for (int i = 0; i < this.eprjctl.size(); i++) {
            ((Projectile) this.eprjctl.get(i)).draw(g);
        }
        for (int i = 0; i < this.bm.size(); i++) {
            ((Booms) this.bm.get(i)).draw(g);
        }
        this.plr.draw(g);

        this.hb.draw(g);

        this.hud.draw(g);
        if (this.ttl != null) {
            this.ttl.draw(g);
        }
        if (this.sbttl != null) {
            this.sbttl.draw(g);
        }
        g.setColor(Color.BLACK);
        for (int i = 0; i < this.tb.size(); i++) {
            g.fill((Shape) this.tb.get(i));
        }
    }

    public void ModeHandler() {
        if (Keys.isPressed(Keys.Escape)) {
            this.MdMngr.Pawzd(true);
        }
        if ((this.blockInput) || (this.plr.getHealth() == 0)) {
            return;
        }
        this.plr.setUp(Keys.kystta[Keys.Up]);
        this.plr.setLeft(Keys.kystta[Keys.Lft]);
        this.plr.setDown(Keys.kystta[Keys.Dwn]);
        this.plr.setRight(Keys.kystta[Keys.Rght]);
        this.plr.setJumping(Keys.kystta[Keys.btna]);
        this.plr.setDashing(Keys.kystta[Keys.btnb]);
        if (Keys.isPressed(Keys.btnc)) {
            this.plr.setAttacking();
        }
        if (Keys.isPressed(Keys.btnd)) {
            this.plr.setCharging();
        }
    }

    private void reset() {
        this.plr.reset();
        this.plr.setPosition(300.0D, 161.0D);
        populateEnemies();
        this.blockInput = true;
        this.eventCount = 0;
        this.tm.setShaking(false, 0);
        this.eventStart = true;
        eventStart();
        this.ttl = new Title(this.txt.getSubimage(0, 0, 178, 20));
        this.ttl.setY(60.0D);
        this.sbttl = new Title(this.txt.getSubimage(0, 33, 91, 13));
        this.sbttl.setY(85.0D);
    }

    private void eventStart() {
        this.eventCount += 1;
        if (this.eventCount == 1) {
            this.tb.clear();
            this.tb.add(new Rectangle(0, 0, 320, 120));
            this.tb.add(new Rectangle(0, 0, 160, 240));
            this.tb.add(new Rectangle(0, 120, 320, 120));
            this.tb.add(new Rectangle(160, 0, 160, 240));
        }
        if ((this.eventCount > 1) && (this.eventCount < 60)) {
            ((Rectangle) this.tb.get(0)).height -= 4;
            ((Rectangle) this.tb.get(1)).width -= 6;
            ((Rectangle) this.tb.get(2)).y += 4;
            ((Rectangle) this.tb.get(3)).x += 6;
        }
        if (this.eventCount == 30) {
            this.ttl.begin();
        }
        if (this.eventCount == 60) {
            this.eventStart = (this.blockInput = false);
            this.eventCount = 0;
            this.sbttl.begin();
            this.tb.clear();
        }
    }

    private void eventDead() {
        this.eventCount += 1;
        if (this.eventCount == 1) {
            this.plr.setDead();
            this.plr.stop();
        }
        if (this.eventCount == 60) {
            this.tb.clear();
            this.tb.add(new Rectangle(
                    160, 120, 0, 0));
        } else if (this.eventCount > 60) {
            ((Rectangle) this.tb.get(0)).x -= 6;
            ((Rectangle) this.tb.get(0)).y -= 4;
            ((Rectangle) this.tb.get(0)).width += 12;
            ((Rectangle) this.tb.get(0)).height += 8;
        }
        if (this.eventCount >= 120) {
            if (this.plr.getLives() == 0) {
                this.MdMngr.setMode(0);
            } else {
                this.eventDead = (this.blockInput = false);
                this.eventCount = 0;
                this.plr.loseLife();
                reset();
            }
        }
    }

    private void eventFinish() {
        this.eventCount += 1;
        if (this.eventCount == 1) {
            BeatBox.play("hb");
            this.plr.setTeleporting(true);
            this.plr.stop();
        } else if (this.eventCount == 120) {
            this.tb.clear();
            this.tb.add(new Rectangle(
                    160, 120, 0, 0));
        } else if (this.eventCount > 120) {
            ((Rectangle) this.tb.get(0)).x -= 6;
            ((Rectangle) this.tb.get(0)).y -= 4;
            ((Rectangle) this.tb.get(0)).width += 12;
            ((Rectangle) this.tb.get(0)).height += 8;
            BeatBox.stop("hb");
        }
        if (this.eventCount == 180) {
            Save.setHp(this.plr.getHealth());
            Save.setLives(this.plr.getLives());
            Save.setTime(this.plr.getTime());
            this.MdMngr.setMode(3);
        }
    }
}

