/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.TileMap;

import java.awt.image.BufferedImage;

/**
 *
 * @author Threadcount
 */
public class Tile {
    private BufferedImage img;
    private int typ;
    public static final int Normal = 0;
    public static final int Blocked = 1;
    
    public Tile(BufferedImage i, int t){
        this.img = i;
        this.typ = t;
    }
    
    public BufferedImage getImage(){
        return this.img;
    }
    
    public int getType(){
        return this.typ;
    }
    
}
