package game.room;

import game.Panel;
import game.button.Niveis;
import game.object.*;
import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cutscene extends Room {

    private CopyOnWriteArrayList<Drawable> objects;

    public void init(Panel panel) {
        objects = new CopyOnWriteArrayList<>();
        objects.add(new Sprite("/game/image/newspaper.png", 393, 0, 1134, 1080));
        objects.add(new Niveis(panel, "/game/image/WBniveis.png", "/game/image/GBniveis.png", 1575, 980, 616, 70));
    }

    public synchronized void draw(Graphics2D g2) {
        for (Drawable obj : objects) {
            obj.draw(g2);
        }
    }

    public synchronized void cleanup() {
        for(int i=objects.size()-1; i>=0; i--) {
            objects.get(i).cleanup();
            objects.remove(i);
        }
    }

}