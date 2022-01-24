import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URISyntaxException;

public class Sound {

    private AudioClip audio;
    public Sound(String audio)  {

        this.audio = new AudioClip(this.getClass().getResource(audio).toExternalForm());
    }

    public void play(Sound sound){
        sound.audio.play();
    }
}
