package game.asset;

import javax.swing.*;
import game.object.Drawable;
import java.awt.*;

public class GrayBack implements Drawable, Runnable{

    public Image activeImage = new ImageIcon(getClass().getResource("/game/image/grayBack.png")).getImage();
    public Image nullImage = new ImageIcon(getClass().getResource("/game/image/null.png")).getImage();
    public Image image = activeImage;
    public Player player;

    public GrayBack(Player player) {
        new Thread(this).start();
        this.player = player;
    }

    public void cleanup() { }

    public void draw(Graphics2D g2) { 
        g2.drawImage(image, 0, 0, null); 
    }

    public void run() {
        while(true) {
            if(player.paused) image = activeImage;
            else image = nullImage;
        }
    }

}
