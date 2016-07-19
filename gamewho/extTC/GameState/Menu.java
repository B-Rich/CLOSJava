/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.GameState;

import gamewho.extTC.Sound.BeatBox;
import gamewho.extTC.Characters.Save;
import gamewho.extTC.Handlers.Keys;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Threadcount
 */
public class Menu extends GameMode {

    private BufferedImage h;
    private int crrnt = 0;
    private String[] optns = {"Start", "Quit"};
    private Color c;
    private Font F;
    private Font f1;
    private Font f2;
    private BufferedImage bk;

    public Menu(ModeManager MM) {
        super(MM);
        try {
            this.h = ImageIO.read(getClass().getResourceAsStream("/HUD/Hud.gif")).getSubimage(0, 12, 12, 11);
            this.bk = ImageIO.read(new FileInputStream("HUD/Load.gif"));
            this.c = Color.WHITE;
            this.F = new Font("Calibri", 0, 20);
            this.f1 = new Font("Arial", 0, 14);
            this.f2 = new Font("Arial", 0, 10);
            //BeatBox.load("/SFX/menuoption.mp3", "menuoption");
            //BeatBox.load("/SFX/menuselect.mp3", "menuselect");
        } catch (Exception expctn) {
            expctn.printStackTrace();
        }
    }

    public void init() {
    }

    public void update() {
        ModeHandler();
    }

    public void draw(Graphics2D grphcs) {
        grphcs.setColor(java.awt.Color.BLACK);
        grphcs.fillRect(0, 0, 320, 240);
        grphcs.setColor(java.awt.Color.WHITE);
        grphcs.setFont(this.F);
        grphcs.drawImage(this.bk, 70, 20, null);
        grphcs.drawString("Castlevania: Lords of Shadows 2", 35, 90);
        grphcs.drawString("Start", 145, 165);
        grphcs.drawString("Exit", 145, 185);

        if (this.crrnt == 0) {
            grphcs.drawImage(this.h, 125, 154, null);
        } else if (this.crrnt == 1) {
            grphcs.drawImage(this.h, 125, 174, null);
        }
        grphcs.setFont(this.f2);
        grphcs.drawString("2015 O.M.Ghozlan ", 10, 232);
    }

    public void select() {
        if (this.crrnt == 0) {
            BeatBox.play("menuselect");
            Save.init();
            this.MdMngr.setMode(2);
        } else if (this.crrnt == 1) {
            System.exit(0);
        }
    }

    public void ModeHandler() {
        if (Keys.isPressed(Keys.Enter)) {
            select();
        }
        if ((Keys.isPressed(Keys.Up)) && (this.crrnt > 0)) {
            BeatBox.play("menuoption", 0);
            this.crrnt -= 1;
        }
        if ((Keys.isPressed(Keys.Dwn)) && (this.crrnt < this.optns.length - 1)) {
            BeatBox.play("menuoption", 0);
            this.crrnt += 1;
        }

    }
}
