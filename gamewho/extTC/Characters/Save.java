/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.Characters;

/**
 *
 * @author Threadcount
 */
public class Save {
    private static int lives = 3;
    private static int hp = 5;
    private static long t = 0L;
    
    public static void init(){
     lives = 3;
     hp = 5;
     t = 0L;  
    }
    
    public static int getLives(){
        return lives;
    }
    
    public static void setLives(int x){
        lives = x ;
    }
    
    public static int getHp(){
        return hp;
    }
    
    public static void setHp(int x){
        hp = x ;
    }
    
    public static long getTime(){
        return t;
    }
    
    public static void setTime(long z){
        t = z ;
    }
}

