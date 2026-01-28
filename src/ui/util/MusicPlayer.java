package ui.util;

import javax.sound.sampled.*;

public class MusicPlayer {

    private static Clip clip;

    // ===============================
    // MÚSICA EM LOOP (menu, intro etc)
    // ===============================
    public static void playLoop(String caminho, float volume) {
        try {
            if (clip != null && clip.isRunning()) return;

            var url = MusicPlayer.class.getResource(caminho);
            if (url == null) {
                System.out.println("❌ Áudio não encontrado: " + caminho);
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(url);

            clip = AudioSystem.getClip();
            clip.open(audio);

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gain =
                        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

                float volumeSeguro = Math.max(
                        gain.getMinimum(),
                        Math.min(volume, gain.getMaximum())
                );

                gain.setValue(volumeSeguro);
            }

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
        }
    }

    // ===============================
    //  SOM CURTO
    // ===============================
    public static void playOnce(String caminho) {
        try {
            var url = MusicPlayer.class.getResource(caminho);
            if (url == null) {
                System.out.println("❌ Som não encontrado: " + caminho);
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            Clip efeito = AudioSystem.getClip();
            efeito.open(audio);
            efeito.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
