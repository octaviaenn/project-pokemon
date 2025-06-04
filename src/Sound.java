import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    public static void play(String filepath) {
        try {
            File bgmFile = new File(filepath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bgmFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}