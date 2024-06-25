package game.asset;

import javax.swing.*;
import game.object.Drawable;
import game.object.SoundPlayer;
import java.awt.*;

public class Notification implements Drawable, Runnable{

    public Player player;
    public Image image;
    public double yFrom = -150;
    public double yTo = 20;
    public double x=362, y=yFrom;
    public game.Panel panel;
    public volatile boolean alive = true;

    public Notification(Player player, String spritePath) {
        this.image = new ImageIcon(getClass().getResource(spritePath)).getImage();
        this.player = player;
        this.panel = player.panel;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void cleanup() { 
        alive = false;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, (int) x, (int) y, null);
    }

    public void run() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(alive) {
            SoundPlayer soundPlayer = new SoundPlayer("notification.wav", true, panel.soundStatus);
            new Thread(() -> {
                soundPlayer.play();
            }).start();
        }

        while (y < yTo) {
            y += 1;
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (y > yFrom) {
            y -= 1;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}