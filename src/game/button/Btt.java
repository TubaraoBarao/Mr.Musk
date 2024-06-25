package game.button;

import game.Panel;
import game.listener.ButtonPressKey;
import game.object.Drawable;
import game.object.SoundPlayer;
import javax.swing.*;
import java.awt.*;

public class Btt implements Drawable, Runnable {

    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;

    public Image image, whiteImage, grayImage;
    public double x, y;
    public double width, height;
    public Panel panel;
    public ButtonPressKey buttonPressKey = new ButtonPressKey(this);
    public volatile boolean alive = true;

    public Btt() { }
    public Btt(Panel panel, String whiteImage, String grayImage, int x, int y, int width, int height) {

        this.x = x+width/2;
        this.y = y+height/2;
        this.width = width;
        this.height = height;
        this.whiteImage = new ImageIcon(getClass().getResource(whiteImage)).getImage();
        this.grayImage = new ImageIcon(getClass().getResource(grayImage)).getImage();
        this.image = this.whiteImage;
        this.panel = panel;
        Thread thread = new Thread(this);
        thread.start();
        panel.addMouseListener(buttonPressKey);
    }

    public void action() {
        SoundPlayer soundPlayer = new SoundPlayer("aim.wav", true, panel.soundStatus);
        new Thread(() -> {
            soundPlayer.play();
        }).start();
    }

    public void cleanup() {
        panel.removeMouseListener(buttonPressKey);
        alive = false;
    }

    public void draw(Graphics2D g2){
        g2.drawImage(image, (int) (x-width/2), (int) (y-height/2), null);
    }

    public boolean inThere = false;
    public void run() {
        while (alive) {

            long startTime = System.nanoTime();

            double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
            double mouseY = MouseInfo.getPointerInfo().getLocation().getY();

            if(mouseX>x-width/2 && mouseX<x + width/2 && mouseY>y-height/2 && mouseY<y + height/2) { image = grayImage; }
            else image = whiteImage;

            if(mouseX>x-width/2 && mouseX<x + width/2 && mouseY>y-height/2 && mouseY<y + height/2 && !inThere) {
                SoundPlayer soundPlayer = new SoundPlayer("press.wav", true, panel.soundStatus);
                new Thread(() -> {
                    soundPlayer.play();
                }).start();
                inThere = true;
            }
            if(!(mouseX>x-width/2 && mouseX<x + width/2 && mouseY>y-height/2 && mouseY<y + height/2) && inThere) {
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

