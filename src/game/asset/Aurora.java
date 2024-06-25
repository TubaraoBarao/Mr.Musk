package game.asset;

import javax.swing.*;

import game.object.Drawable;

import java.awt.*;

public class Aurora implements Drawable {

    private final double x=1792, y=0;
    private final Image image = new ImageIcon(getClass().getResource("/game/image/aurora.png")).getImage();

    public void cleanup() { }

    public void draw(Graphics2D g2){
        g2.drawImage(image, (int) x, (int) y, null);
    }

}