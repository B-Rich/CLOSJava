/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.Handlers;

/**
 *
 * @author Threadcount
 */
public class Keys {

    public static final int kys = 16;
    public static boolean[] kystta = new boolean[16];
    public static boolean[] kysttb = new boolean[16];
    public static int Up = 0;
    public static int Lft = 1;
    public static int Dwn = 2;
    public static int Rght = 3;
    public static int btna = 4;
    public static int btnb = 5;
    public static int btnc = 6;
    public static int btnd = 7;
    public static int Enter = 8;
    public static int Escape = 9;

    public static void keySet(int x, boolean y) {
        if (x == 38) {
            kystta[Up] = y;
        } else if (x == 37) {
            kystta[Lft] = y;
        } else if (x == 40) {
            kystta[Dwn] = y;
        } else if (x == 39) {
            kystta[Rght] = y;
        } else if (x == 87) {
            kystta[btna] = y;
        }
         else if (x == 69) {
            kystta[btnb] = y;
        }
         else if (x == 82) {
            kystta[btnc] = y;
        }
         else if (x == 70) {
            kystta[btnd] = y;
        }
         else if (x == 10) {
            kystta[Enter] = y;
        }
         else if(x == 27) {
            kystta[Escape] = y;
        }
    }
    
    public static void update(){
        System.arraycopy(kystta, 0, kysttb, 0, 16);
    }
    
    public static boolean isPressed(int x){
        return (kystta[x] != false) && (kysttb[x] == false);
    }
    
    public static boolean anyPresses(){
        for(int k = 0 ; k < 16 ; k++){
            if(kystta[k] != false){
                return true;
            }
        }
        return false;
    }
}
