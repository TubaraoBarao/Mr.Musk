package game.object;

import java.awt.*;

public interface Drawable {

    void draw(Graphics2D g2);
    
    void cleanup();

}
