import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Sounds {
        String click = "resources/sounds/click.wav";
        String hover = "resources/sounds/hover.wav";
        String bgm = "resources/sounds/bgm.wav";

        String win_sfc = "resources/sounds/correct.wav";
        String wro_sfc = "resources/sounds/wrong.wav";

        String win_bgm = "resources/sounds/win.wav";
        String lose_bgm = "resources/sounds/lose.wav";

        public void play(String path, boolean loop) {
            new Thread(() -> {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sounds.class.getResource(path));
                    clip.open(inputStream);
                    if (loop) {
                        clip.loop(99);
                    } else {
                        clip.start();
                    }
                    while(clip.isOpen()) {
                        try { Thread.sleep(2000); } catch(InterruptedException ie) {}
                        if(!clip.isActive()) break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
        }

        public void click_() {
            new Thread(() -> {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sounds.class.getResource(click));
                    clip.open(inputStream);
                    clip.start();

                    while(clip.isOpen()) {
                        try { Thread.sleep(2000); } catch(InterruptedException ie) {}
                        if(!clip.isActive()) break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
    }
        public void hover_() {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sounds.class.getResource(hover));
                clip.open(inputStream);
                clip.start();

                while(clip.isOpen()) {
                    try { Thread.sleep(2000); } catch(InterruptedException ignored) {}
                    if(!clip.isActive()) break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }
}
