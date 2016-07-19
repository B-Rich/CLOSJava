/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.Main;

import javax.swing.JFrame;
/**
 *
 * @author ThreadCount
 */
public class GameWho {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame dsplwndw = new JFrame("Castlevania: Lords of Shadows 2");
        dsplwndw.add(new GmPnl());
        dsplwndw.setDefaultCloseOperation(3);
        dsplwndw.setResizable(false);
        dsplwndw.pack();
        dsplwndw.setLocationRelativeTo(null);
        dsplwndw.setVisible(true);
    }
    
}
