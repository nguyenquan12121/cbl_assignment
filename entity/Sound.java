package entity;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Sound implements LineListener {

    boolean isPlaybackCompleted;
    AudioInputStream audioStream;

    @Override
    public void update(LineEvent event) {
        if (LineEvent.Type.START == event.getType()) {
            System.out.println("Playback started.");
        } else if (LineEvent.Type.STOP == event.getType()) {
            isPlaybackCompleted = true;
            System.out.println("Playback completed.");
        }
    }

    public static void playMain(){
        try {
            InputStream menu= Sound.class.getClassLoader().getResourceAsStream("sounds/menu-sound.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(menu);
        Clip audioClip = AudioSystem.getClip();
            Sound sound = new Sound(); 
            audioClip.addLineListener(sound);
            audioClip.open(audioStream);
            audioClip.start();

            // Wait for playback to complete
            while (!sound.isPlaybackCompleted) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    
}
}
