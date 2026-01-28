package ui.util;

import javax.sound.sampled.*;
import java.io.InputStream;

public class SoundPlayer {

    public static void play(String caminho) {
        try {
            InputStream is = SoundPlayer.class.getResourceAsStream(caminho);
            if (is == null) return;

            AudioInputStream audio = AudioSystem.getAudioInputStream(is);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
