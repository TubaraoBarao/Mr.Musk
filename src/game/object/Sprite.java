package game.object;

import game.Panel;
import javax.swing.*;
import java.awt.*;

public class Sprite implements Drawable {

    public Image image;
    public double x, y;
    public double width, height;
    public Panel panel;
    public boolean alive = true;

    public Sprite(String image, int x, int y, int width, int height) {

        this.x = x+width/2;
        this.y = y+height/2;
        this.width = width;
        this.height = height;
        this.image = new ImageIcon(getClass().getResource(image)).getImage();

    }

    public void action() { }

    public void cleanup() { }

    public void draw(Graphics2D g2){
        g2.drawImage(image, (int) (x-width/2), (int) (y-height/2), null);
    }
    
}
