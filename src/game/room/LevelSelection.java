package game.room;

import game.Panel;
import game.button.*;
import game.object.*;
import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class LevelSelection extends Room {

    private CopyOnWriteArrayList<Drawable> objects;

    public void init(Panel panel) {
        objects = new CopyOnWriteArrayList<>();
        objects.add(new Sprite("/game/image/WBniveis.png", 500, 300, 294, 70));
        objects.add(new Bcutscene(panel, "/game/image/YBcutscene.png", "/game/image/BBcutscene.png", 500, 400, 154, 154));
        objects.add(new LevelBtt(panel, 1, 660, 400, 154, 154));
        objects.add(new LevelBtt(panel, 2, 820, 400, 154, 154));
        objects.add(new LevelBtt(panel, 3, 980, 400, 154, 154));
        objects.add(new LevelBtt(panel, 4, 1140, 400, 154, 154));
        objects.add(new LevelBtt(panel, 5, 1300, 400, 154, 154));
        objects.add(new VoltarAoMenu(panel, "/game/image/WBvoltarAoMenu.png", "/game/image/GBvoltarAoMenu.png", 500, 650, 616, 70));
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