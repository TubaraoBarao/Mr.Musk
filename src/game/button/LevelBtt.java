package game.button;

import game.Panel;
import game.object.SoundPlayer;
import javax.swing.*;
import java.awt.*;

public class LevelBtt extends Btt {

    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;
    public final String[] Wlist = new String[]{"/game/image/WB1.png", "/game/image/WB2.png", "/game/image/WB3.png", "/game/image/WB4.png", "/game/image/WB5.png"};
    public final String[] Glist = new String[]{"/game/image/GB1.png", "/game/image/GB2.png", "/game/image/GB3.png", "/game/image/GB4.png", "/game/image/GB5.png"};
    public final String[] Ylist = new String[]{"/game/image/YB1.png", "/game/image/YB2.png", "/game/image/YB3.png", "/game/image/YB4.png", "/game/image/YB5.png"};
    public final String[] Blist = new String[]{"/game/image/BB1.png", "/game/image/BB2.png", "/game/image/BB3.png", "/game/image/BB4.png", "/game/image/BB5.png"};

    public Image yellowImage, brownImage, lockedImage = new ImageIcon(getClass().getResource("/game/image/locked.png")).getImage();
    public int level;

    public LevelBtt(Panel panel, int level, int x, int y, int width, int height) {
        this.x = x+width/2;
        this.y = y+height/2;
        this.width = width;
        this.height = height;
        this.panel = panel;
        Thread thread = new Thread(this);
        thread.start();
        panel.addMouseListener(buttonPressKey);

        this.level = level;

        if (level==1) back = true;
        else if(panel.notepad.read().charAt(level-2)=='1') back = true;
        here = (panel.notepad.read().charAt(level-1)=='1');

        this.whiteImage = new ImageIcon(getClass().getResource(Wlist[level-1])).getImage();
        this.grayImage = new ImageIcon(getClass().getResource(Glist[level-1])).getImage();
        this.yellowImage = new ImageIcon(getClass().getResource(Ylist[level-1])).getImage();
        this.brownImage = new ImageIcon(getClass().getResource(Blist[level-1])).getImage();
    }

    public void action() {
        super.action();
        panel.setRoom(level);
    }

    public boolean back;
    public boolean here;

    public boolean inThere = false;
    public void run() {
        while (alive) {

            long startTime = System.nanoTime();

            double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
            double mouseY = MouseInfo.getPointerInfo().getLocation().getY();

            if(back) {
                if (mouseX > x - width / 2 && mouseX < x + width / 2 && mouseY > y - height / 2 && mouseY < y + height / 2) {
                    if (!here) image = grayImage;
                    else image = brownImage;
                } else {
                    if (!here) image = whiteImage;
                    else image = yellowImage;
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
            }
            else {
                image = lockedImage;
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