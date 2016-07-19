/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.Sound;

import java.util.HashMap;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.Clip;

/**
 *
 * @author ThreadCount
 */
public class BeatBox {
    private static HashMap<String, Clip> clps;
    private static int gp;
    private static boolean mte = false; //muting

public static void init(){
    clps = new HashMap();
    gp = 0;
}

public static void load(String strng, String lst){
    if(clps.get(lst) != null){
        return;
    }
    try{
        AudioInputStream sfx = AudioSystem.getAudioInputStream(BeatBox.class.getResourceAsStream(strng));
        AudioFormat bsfrmt = sfx.getFormat();
        AudioFormat dcdfrmt = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                 bsfrmt.getSampleRate(),
                 16,
                 bsfrmt.getChannels(),
                 bsfrmt.getChannels()*2,
                 bsfrmt.getSampleRate(),
                 false);
        AudioInputStream dcdsfx = AudioSystem.getAudioInputStream(dcdfrmt,sfx);
        Clip clp = AudioSystem.getClip();
        clp.open(dcdsfx);
        clps.put(lst,clp);
    }
    catch(Exception exptn){
        exptn.printStackTrace(); // bybayen el error elly 7asal kan fein
    }
}
public static void play(String strng, int t){
    if(mte)
        return;
    Clip x = (Clip)clps.get(strng);
    if(x == null)
        return;
    if(x.isRunning())
        x.stop();
    x.setFramePosition(t);
    while(!(x.isRunning())){
        x.start();
    }   
}

public static void play(String strng){
    play(strng, gp);
}

public static void stop(String strng){
    if(clps.get(strng) == null)
        return;
    if(((Clip)clps.get(strng)).isRunning())
        ((Clip)clps.get(strng)).stop();
}

public static void resume(String strng){
    if(mte) 
        return;
    if(((Clip)clps.get(strng)).isRunning())
        return;
    ((Clip)clps.get(strng)).start();
}

public static void loop(String strng, int f, int strt, int end){
    stop(strng);
    if(mte)
        return;
    ((Clip)clps.get(strng)).setLoopPoints(strt, end);
    ((Clip)clps.get(strng)).setFramePosition(f);
    ((Clip)clps.get(strng)).loop(-1);
}

public static void setPosition(String strng, int f){
    ((Clip)clps.get(strng)).setFramePosition(f);
}

public static void getPosition(String strng){
    ((Clip)clps.get(strng)).getFramePosition();
}

public static void setFrame(String strng){
    ((Clip)clps.get(strng)).getFrameLength();
}

public static void getFrame(String strng){
    ((Clip)clps.get(strng)).getFrameLength();
}

public static void loop(String strng){
    loop(strng, gp, gp, ((Clip)clps.get(strng)).getFrameLength() - 1);
}

public static void loop(String strng, int f){
    loop(strng, f, gp, ((Clip)clps.get(strng)).getFrameLength() - 1);
}

public static void loop(String strng, int strt, int end){
    loop(strng, gp, strt, end);
}

public static void close(String strng){
    stop(strng);
    ((Clip)clps.get(strng)).close();
}

}
