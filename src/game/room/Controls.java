package game.room;

import game.Panel;
import game.button.VoltarAoMenu;
import game.object.*;
import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class Controls extends Room {

    private CopyOnWriteArrayList<Drawable> objects;

    public void init(Panel panel) {
        objects = new CopyOnWriteArrayList<>();
        objects.add(new Sprite("/game/image/controles.png", 50, 50, 1260, 560));
        objects.add(new VoltarAoMenu(panel, "/game/image/WBvoltarAoMenu.png", "/game/image/GBvoltarAoMenu.png", 1250, 980, 616, 70));
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