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
import gamewho.extTC.Sound.BeatBox;
import gamewho.extTC.TileMap.TileMp;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Plr extends MpObj
{
  private ArrayList<Foe> enemies;
  private int lives;
  private int hp;
  private int maxHp;
  private int dmg;
  private int chrgDmg;
  private boolean knckbck;
  private boolean flnch;
  private long flnchCnt;
  private int scr;
  private boolean doubleJump;
  private boolean alreadyDoubleJump;
  private double doubleJumpStart;
  private ArrayList<Particle> ePrtcls;
  private long tm;
  private boolean dshng;
  private boolean attckng;
  private boolean upattckng;
  private boolean chrgng;
  private int chrgngTick;
  private boolean teleporting;
  private ArrayList<BufferedImage[]> sprts;
  private final int[] NUMFRAMES = {
    1, 8, 5, 3, 3, 5, 3, 8, 3, 1, 3 };
  private final int[] FRAMEWIDTHS = {
    40, 40, 80, 40, 40, 40, 80, 40, 40, 40, 40 };
  private final int[] FRAMEHEIGHTS = {
    40, 40, 40, 40, 40, 80, 40, 40, 40, 40, 40 };
  private final int[] SPRITEDELAYS = {
    -1, 3, 2, 6, 5, 2, 2, 2, 1, -1, 1 };
  private Rectangle ar;
  private Rectangle aur;
  private Rectangle cr;
  private static final int IDLE = 0;
  private static final int WALKING = 1;
  private static final int ATTACKING = 2;
  private static final int JUMPING = 3;
  private static final int FALLING = 4;
  private static final int UpATTACKING = 5;
  private static final int CHARGING = 6;
  private static final int DASHING = 7;
  private static final int KNOCKBACK = 8;
  private static final int DEAD = 9;
  private static final int TELEPORTING = 10;
  private BufferedImage confused;
  private BufferedImage surprised;
  public static final int NONE = 0;
  public static final int CONFUSED = 1;
  public static final int SURPRISED = 2;
  private int emote = 0;
  
  public Plr(TileMp tm)
  {
    super(tm);
    
    this.ar = new Rectangle(0, 0, 0, 0);
    this.ar.width = 30;
    this.ar.height = 20;
    this.aur = new Rectangle((int)this.x - 15, (int)this.y - 45, 30, 30);
    this.cr = new Rectangle(0, 0, 0, 0);
    this.cr.width = 50;
    this.cr.height = 40;
    
    this.wdth = 30;
    this.hght = 30;
    this.cwdth = 15;
    this.chght = 38;
    
    this.moveSpeed = 1.6D;
    this.maxSpeed = 1.6D;
    this.stopSpeed = 1.6D;
    this.fallSpeed = 0.15D;
    this.maxFallSpeed = 4.0D;
    this.jumpStart = -4.8D;
    this.stopJumpSpeed = 0.3D;
    this.doubleJumpStart = -3.0D;
    
    this.dmg = 2;
    this.chrgDmg = 1;
    
    this.facingRight = true;
    
    this.lives = 3;
    this.hp = (this.maxHp = 5);
    try
    {
      BufferedImage sprtsheet = ImageIO.read(
        getClass().getResourceAsStream(
        "/Sprites/Player/DraculSprites.gif"));
      //BufferedImage sprtsheet = ImageIO.read(new FileInputStream ("Sprites/Player/DraculSprites.gif"));
      int count = 0;
      this.sprts = new ArrayList();
      for (int i = 0; i < this.NUMFRAMES.length; i++)
      {
        BufferedImage[] bi = new BufferedImage[this.NUMFRAMES[i]];
        for (int j = 0; j < this.NUMFRAMES[i]; j++) {
          bi[j] = sprtsheet
            .getSubimage(j * this.FRAMEWIDTHS[i], count, this.FRAMEWIDTHS[i], this.FRAMEHEIGHTS[i]);
        }
        this.sprts.add(bi);
        count += this.FRAMEHEIGHTS[i];
      }
      sprtsheet = ImageIO.read(getClass().getResourceAsStream(
        "/HUD/Emotes.gif"));
      
      this.confused = sprtsheet
        .getSubimage(0, 0, 14, 17);
      
      this.surprised = sprtsheet
        .getSubimage(14, 0, 14, 17);
    }
    catch (Exception excptn)
    {
      excptn.printStackTrace();
    }
    this.ePrtcls = new ArrayList();
    
    setAnmtn(0);
    /*BeatBox.load("/SFX/playerjump.mp3", "playerjump");
    BeatBox.load("/SFX/playerlands.mp3", "playerlands");
    BeatBox.load("/SFX/playerattack.mp3", "playerattack");
    BeatBox.load("/SFX/playerhit.mp3", "playerhit");
    BeatBox.load("/SFX/playercharge.mp3", "playercharge");*/
  }
  
  public void init(ArrayList<Foe> enemies, ArrayList<Particle> ePrtcls)
  {
    this.enemies = enemies;
    this.ePrtcls = ePrtcls;
  }
  
  public int getHealth()
  {
    return this.hp;
  }
  
  public int getMaxHealth()
  {
    return this.maxHp;
  }
  
  public void setEmote(int i)
  {
    this.emote = i;
  }
  
  public void setTeleporting(boolean b)
  {
    this.teleporting = b;
  }
  
  public void setJumping(boolean b)
  {
    if (this.knckbck) {
      return;
    }
    if ((b) && (!this.jumping) && (this.falling) && (!this.alreadyDoubleJump)) {
      this.doubleJump = true;
    }
    this.jumping = b;
  }
  
  public void setAttacking()
  {
    if (this.knckbck) {
      return;
    }
    if (this.chrgng) {
      return;
    }
    if ((this.up) && (!this.attckng)) {
      this.upattckng = true;
    } else {
      this.attckng = true;
    }
  }
  
  public void setCharging()
  {
    if (this.knckbck) {
      return;
    }
    if ((!this.attckng) && (!this.upattckng) && (!this.chrgng))
    {
      this.chrgng = true;
      //BeatBox.play("playercharge");
      this.chrgngTick = 0;
    }
  }
  
  public void setDashing(boolean b)
  {
    if (!b) {
      this.dshng = false;
    } else if ((b) && (!this.falling)) {
      this.dshng = true;
    }
  }
  
  public boolean isDashing()
  {
    return this.dshng;
  }
  
  public void setDead()
  {
    this.hp = 0;
    stop();
  }
  
  public String getTimeToString()
  {
    int minutes = (int)(this.tm / 3600L);
    int seconds = (int)(this.tm % 3600L / 60L);
    return minutes + ":" + seconds;
  }
  
  public long getTime()
  {
    return this.tm;
  }
  
  public void setTime(long t)
  {
    this.tm = t;
  }
  
  public void setHealth(int i)
  {
    this.hp = i;
  }
  
  public void setLives(int i)
  {
    this.lives = i;
  }
  
  public void gainLife()
  {
    this.lives += 1;
  }
  
  public void loseLife()
  {
    this.lives -= 1;
  }
  
  public int getLives()
  {
    return this.lives;
  }
  
  public void increaseScore(int scr)
  {
    this.scr += scr;
  }
  
  public int getScore()
  {
    return this.scr;
  }
  
  public void hit(int dmg)
  {
    if (this.flnch) {
      return;
    }
    //BeatBox.play("playerhit");
    stop();
    this.hp -= dmg;
    if (this.hp < 0) {
      this.hp = 0;
    }
    this.flnch = true;
    this.flnchCnt = 0L;
    if (this.facingRight) {
      this.dx = -1.0D;
    } else {
      this.dx = 1.0D;
    }
    this.dy = -3.0D;
    this.knckbck = true;
    this.falling = true;
    this.jumping = false;
  }
  
  public void reset()
  {
    this.hp = this.maxHp;
    this.facingRight = true;
    this.currentAction = -1;
    stop();
  }
  
  public void stop()
  {
    this.left = (this.right = this.up = this.down = this.flnch = 
      this.dshng = this.jumping = this.attckng = this.upattckng = this.chrgng = false);
  }
  
  private void getNextPosition()
  {
    if (this.knckbck)
    {
      this.dy += this.fallSpeed * 2.0D;
      if (!this.falling) {
        this.knckbck = false;
      }
      return;
    }
    double maxSpeed = this.maxSpeed;
    if (this.dshng) {
      maxSpeed *= 1.75D;
    }
    if (this.left)
    {
      this.dx -= this.moveSpeed;
      if (this.dx < -maxSpeed) {
        this.dx = (-maxSpeed);
      }
    }
    else if (this.right)
    {
      this.dx += this.moveSpeed;
      if (this.dx > maxSpeed) {
        this.dx = maxSpeed;
      }
    }
    else if (this.dx > 0.0D)
    {
      this.dx -= this.stopSpeed;
      if (this.dx < 0.0D) {
        this.dx = 0.0D;
      }
    }
    else if (this.dx < 0.0D)
    {
      this.dx += this.stopSpeed;
      if (this.dx > 0.0D) {
        this.dx = 0.0D;
      }
    }
    if (((this.attckng) || (this.upattckng) || (this.chrgng)) && 
      (!this.jumping) && (!this.falling)) {
      this.dx = 0.0D;
    }
    if (this.chrgng)
    {
      this.chrgngTick += 1;
      if (this.facingRight) {
        this.dx = (this.moveSpeed * (3.0D - this.chrgngTick * 0.07000000000000001D));
      } else {
        this.dx = (-this.moveSpeed * (3.0D - this.chrgngTick * 0.07000000000000001D));
      }
    }
    if ((this.jumping) && (!this.falling))
    {
      this.dy = this.jumpStart;
      this.falling = true;
      //BeatBox.play("playerjump");
    }
    if (this.doubleJump)
    {
      this.dy = this.doubleJumpStart;
      this.alreadyDoubleJump = true;
      this.doubleJump = false;
      //BeatBox.play("playerjump");
      for (int i = 0; i < 6; i++) {
        this.ePrtcls.add(
          new Particle(
          this.tileMap, 
          this.x, 
          this.y + this.chght / 4, 
          Particle.Down));
      }
    }
    if (!this.falling) {
      this.alreadyDoubleJump = false;
    }
    if (this.falling)
    {
      this.dy += this.fallSpeed;
      if ((this.dy < 0.0D) && (!this.jumping)) {
        this.dy += this.stopJumpSpeed;
      }
      if (this.dy > this.maxFallSpeed) {
        this.dy = this.maxFallSpeed;
      }
    }
  }
  
  private void setAnmtn(int i)
  {
    this.currentAction = i;
    this.anm.setFrames((BufferedImage[])this.sprts.get(this.currentAction));
    this.anm.setDelay(this.SPRITEDELAYS[this.currentAction]);
    this.wdth = this.FRAMEWIDTHS[this.currentAction];
    this.hght = this.FRAMEHEIGHTS[this.currentAction];
  }
  
  public void update()
  {
    this.tm += 1L;
    if (this.teleporting) {
      this.ePrtcls.add(
        new Particle(this.tileMap, this.x, this.y, Particle.Up));
    }
    boolean isFalling = this.falling;
    getNextPosition();
    checkTileMpCollision();
    setPosition(this.xtemp, this.ytemp);
    if ((isFalling) && (!this.falling)) {
      //BeatBox.play("playerlands");
    }
    if (this.dx == 0.0D) {
      this.x = ((int)this.x);
    }
    if (this.flnch)
    {
      this.flnchCnt += 1L;
      if (this.flnchCnt > 120L) {
        this.flnch = false;
      }
    }
    for (int i = 0; i < this.ePrtcls.size(); i++)
    {
      ((Particle)this.ePrtcls.get(i)).update();
      if (((Particle)this.ePrtcls.get(i)).shouldRemove())
      {
        this.ePrtcls.remove(i);
        i--;
      }
    }
    if (((this.currentAction == 2) || 
      (this.currentAction == 5)) && 
      (this.anm.hasPlayedOnce()))
    {
      this.attckng = false;
      this.upattckng = false;
    }
    if (this.currentAction == 6)
    {
      if (this.anm.hasPlayed(5)) {
        this.chrgng = false;
      }
      this.cr.y = ((int)this.y - 20);
      if (this.facingRight) {
        this.cr.x = ((int)this.x - 15);
      } else {
        this.cr.x = ((int)this.x - 35);
      }
      if (this.facingRight) {
        this.ePrtcls.add(
          new Particle(
          this.tileMap, 
          this.x + 30.0D, 
          this.y, 
          Particle.Right));
      } else {
        this.ePrtcls.add(
          new Particle(
          this.tileMap, 
          this.x - 30.0D, 
          this.y, 
          Particle.Left));
      }
    }
    for (int i = 0; i < this.enemies.size(); i++)
    {
      Foe e = (Foe)this.enemies.get(i);
      if ((this.currentAction == 2) && 
        (this.anm.getFrame() == 3) && (this.anm.getCount() == 0) && 
        (e.intersects(this.ar))) {
        e.hit(this.dmg);
      }
      if ((this.currentAction == 5) && 
        (this.anm.getFrame() == 3) && (this.anm.getCount() == 0) && 
        (e.intersects(this.aur))) {
        e.hit(this.dmg);
      }
      if ((this.currentAction == 6) && 
        (this.anm.getCount() == 0) && 
        (e.intersects(this.cr))) {
        e.hit(this.chrgDmg);
      }
      if ((!e.isKill()) && (intersects(e)) && (!this.chrgng)) {
        hit(e.getDmg());
      }
      if (e.isKill()) {
        BeatBox.play("explode", 2000);
      }
    }
    if (this.teleporting)
    {
      if (this.currentAction != 10) {
        setAnmtn(10);
      }
    }
    else if (this.knckbck)
    {
      if (this.currentAction != 8) {
        setAnmtn(8);
      }
    }
    else if (this.hp == 0)
    {
      if (this.currentAction != 9) {
        setAnmtn(9);
      }
    }
    else if (this.upattckng)
    {
      if (this.currentAction != 5)
      {
        //BeatBox.play("playerattack");
        setAnmtn(5);
        this.aur.x = ((int)this.x - 15);
        this.aur.y = ((int)this.y - 50);
      }
      else if ((this.anm.getFrame() == 4) && (this.anm.getCount() == 0))
      {
        for (int c = 0; c < 3; c++) {
          this.ePrtcls.add(
            new Particle(
            this.tileMap, 
            this.aur.x + this.aur.width / 2, 
            this.aur.y + 5, 
            Particle.Up));
        }
      }
    }
    else if (this.attckng)
    {
      if (this.currentAction != 2)
      {
        //BeatBox.play("playerattack");
        setAnmtn(2);
        this.ar.y = ((int)this.y - 6);
        if (this.facingRight) {
          this.ar.x = ((int)this.x + 10);
        } else {
          this.ar.x = ((int)this.x - 40);
        }
      }
      else if ((this.anm.getFrame() == 4) && (this.anm.getCount() == 0))
      {
        for (int c = 0; c < 3; c++) {
          if (this.facingRight) {
            this.ePrtcls.add(
              new Particle(
              this.tileMap, 
              this.ar.x + this.ar.width - 4, 
              this.ar.y + this.ar.height / 2, 
              Particle.Right));
          } else {
            this.ePrtcls.add(
              new Particle(
              this.tileMap, 
              this.ar.x + 4, 
              this.ar.y + this.ar.height / 2, 
              Particle.Left));
          }
        }
      }
    }
    else if (this.chrgng)
    {
      if (this.currentAction != 6) {
        setAnmtn(6);
      }
    }
    else if (this.dy < 0.0D)
    {
      if (this.currentAction != 3) {
        setAnmtn(3);
      }
    }
    else if (this.dy > 0.0D)
    {
      if (this.currentAction != 4) {
        setAnmtn(4);
      }
    }
    else if ((this.dshng) && ((this.left) || (this.right)))
    {
      if (this.currentAction != 7) {
        setAnmtn(7);
      }
    }
    else if ((this.left) || (this.right))
    {
      if (this.currentAction != 1) {
        setAnmtn(1);
      }
    }
    else if (this.currentAction != 0) {
      setAnmtn(0);
    }
    this.anm.update();
    if ((!this.attckng) && (!this.upattckng) && (!this.chrgng) && (!this.knckbck))
    {
      if (this.right) {
        this.facingRight = true;
      }
      if (this.left) {
        this.facingRight = false;
      }
    }
  }
  
  public void draw(Graphics2D grphcs)
  {
    if (this.emote == 1) {
      grphcs.drawImage(this.confused, (int)(this.x + this.xmap - this.cwdth / 2), (int)(this.y + this.ymap - 40.0D), null);
    } else if (this.emote == 2) {
      grphcs.drawImage(this.surprised, (int)(this.x + this.xmap - this.cwdth / 2), (int)(this.y + this.ymap - 40.0D), null);
    }
    for (int i = 0; i < this.ePrtcls.size(); i++) {
      ((Particle)this.ePrtcls.get(i)).draw(grphcs);
    }
    if ((this.flnch) && (!this.knckbck) && 
      (this.flnchCnt % 10L < 5L)) {
      return;
    }
    super.draw(grphcs);
  }
}
