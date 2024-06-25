package game.object;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {

    private Clip clip;

    public SoundPlayer(String fileName, boolean isSoundEffect, boolean isAudioOn) {
        try {

            String prefix = "src/game/sound/";
            File soundFile = new File(prefix.concat(fileName));
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);

        } catch (UnsupportedAudioFileException | IOException e){
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        this.setVolume(-10.0f);
        if(isSoundEffect && !isAudioOn) { this.mute(); }
    }

    public synchronized void setVolume(float volume) {
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
        }
    }

    public synchronized void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public synchronized void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public synchronized void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public synchronized void mute() {
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volumeControl.getMinimum());
        }
    }

    public synchronized void unmute() {
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volumeControl.getMaximum());
        }
    }

    public synchronized void close() {
        if (clip != null) {
            clip.close();
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

}
