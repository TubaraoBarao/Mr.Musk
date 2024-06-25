package game.asset;

import javax.swing.*;
import game.object.Drawable;
import java.awt.*;

public class Space implements Drawable {

    public final double x=0, y=0;
    public final Image image = new ImageIcon(getClass().getResource("/game/image/space.png")).getImage();

    public void cleanup() { }

    public void draw(Graphics2D g2){
        g2.drawImage(image, (int) x, (int) y, null);
    }

}