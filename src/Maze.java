import Pages.Home.HomePage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Maze {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        new HomePage().setVisible(true);
    }
}