package game.asset;

import java.awt.*;
import game.object.Drawable;

public class CelestialBody implements Drawable {

    public Player player;
    public CelestialBody partner;
    public double x, y, collisionRadius, spriteRadius, gravitationalFieldRadius, gravity, lightFieldRadius, light;
    public Image image;

    public void draw(Graphics2D g2) { g2.drawImage(image, (int) (x-spriteRadius), (int) (y-spriteRadius) , null); }

    public void cleanup() { }
    
}
