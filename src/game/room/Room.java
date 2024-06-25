package game.room;

import game.Panel;
import java.awt.Graphics2D;

public abstract class Room {

    public abstract void init(Panel panel);

    public abstract void draw(Graphics2D g2);

    public abstract void cleanup();

}