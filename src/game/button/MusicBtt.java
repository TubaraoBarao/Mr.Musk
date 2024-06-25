package game.button;

import game.Panel;
import game.object.SoundPlayer;

import javax.swing.*;
import java.awt.*;

public class MusicBtt extends Btt {

    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;
    public final Image WBwithNotification = new ImageIcon(getClass().getResource("/game/image/WBwithMusic.png")).getImage();
    public final Image GBwithNotification = new ImageIcon(getClass().getResource("/game/image/GBwithMusic.png")).getImage();
    public final Image WBwithoutNotification = new ImageIcon(getClass().getResource("/game/image/WBwithoutMusic.png")).getImage();
    public final Image GBwithoutNotification = new ImageIcon(getClass().getResource("/game/image/GBwithoutMusic.png")).getImage();

    public MusicBtt(Panel panel, int x, int y, int width, int height) {
        this.x = x+width/2;
        this.y = y+height/2;
        this.width = width;
        this.height = height;
        this.panel = panel;
        Thread thread = new Thread(this);
        thread.start();
        panel.addMouseListener(buttonPressKey);
    }

    public void action() {
        super.action();
        panel.musicStatus = !panel.musicStatus;
        if(panel.musicStatus && panel.soundStatus) panel.music.setVolume(-20.0f);
        else panel.music.mute();
    }

    public boolean inThere = false;
    public void run() {
        while (alive) {

            long startTime = System.nanoTime();

            double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
            double mouseY = MouseInfo.getPointerInfo().getLocation().getY();

            if (mouseX > x - width / 2 && mouseX < x + width / 2 && mouseY > y - height / 2 && mouseY < y + height / 2) {
                if(panel.musicStatus && panel.soundStatus) image = GBwithNotification;
                else image = GBwithoutNotification;
            }
            else {
                if(panel.musicStatus && panel.soundStatus)  image = WBwithNotification;
                else image = WBwithoutNotification;
            }

            if (mouseX > x - width / 2 && mouseX < x + width / 2 && mouseY > y - height / 2 && mouseY < y + height / 2 && !inThere) {
                SoundPlayer soundPlayer = new SoundPlayer("press.wav", true, panel.soundStatus);
                new Thread(() -> {
                    soundPlayer.play();
                }).start();
                inThere = true;
            }
            if (!(mouseX > x - width / 2 && mouseX < x + width / 2 && mouseY > y - height / 2 && mouseY < y + height / 2) && inThere) {
                SoundPlayer soundPlayer = new SoundPlayer("press.wav", true, panel.soundStatus);
                new Thread(() -> {
                    soundPlayer.play();
                }).start();
                inThere = false;
            }

            long time = System.nanoTime() - startTime;
            if (time < TARGET_TIME) {
                long sleep = (TARGET_TIME - time) / 1000000;
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
