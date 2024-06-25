package game.asset;

import javax.swing.*;
import game.object.Drawable;
import java.awt.*;

public class Earth implements Drawable{

    private final double x=0, y=952;
    private final Image image = new ImageIcon(getClass().getResource("/game/image/earth.png")).getImage();

    public void draw(Graphics2D g2){
        g2.drawImage(image, (int) x, (int) y, null);
    }

    public void cleanup() { }

}