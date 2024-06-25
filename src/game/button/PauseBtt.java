package game.button;

import game.Panel;
import game.asset.Player;
import game.object.SoundPlayer;
import javax.swing.*;
import java.awt.*;

public class PauseBtt extends Btt{

    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;

    public Player player;
    public Image nullImage = new ImageIcon(getClass().getResource("/game/image/null.png")).getImage();

    public PauseBtt(Player player, Panel panel, String whiteImage, String grayImage, int x, int y, int width, int height) {
        super(panel, whiteImage, grayImage, x, y, width, height);
        image = nullImage;
        this.player = player;
    }

    public boolean inThere = false;
    public void run() {
        while (alive) {

            long startTime = System.nanoTime();

            if(player!=null && player.paused) {
                double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
                double mouseY = MouseInfo.getPointerInfo().getLocation().getY();

                if (mouseX > x - width / 2 && mouseX < x + width / 2 && mouseY > y - height / 2 && mouseY < y + height / 2) {
                    image = grayImage;
                } else image = whiteImage;

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
            }
            else image = nullImage;

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