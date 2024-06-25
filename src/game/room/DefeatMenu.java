package game.room;

import game.Panel;
import game.button.*;
import game.object.*;
import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefeatMenu extends Room {

    private CopyOnWriteArrayList<Drawable> objects;

    public void init(Panel panel) {
        objects = new CopyOnWriteArrayList<>();
        objects.add(new Sprite("/game/image/desastre.png", 1100, 340, 476, 70));
        objects.add(new Sprite("/game/image/doidao.png", 100, 400, 900, 315));
        objects.add(new TentarDeNovo(panel, "/game/image/WBtentarDeNovo.png", "/game/image/GBtentarDeNovo.png", 1100, 440, 616, 70));
        objects.add(new Niveis(panel, "/game/image/WBniveis.png", "/game/image/GBniveis.png", 1100, 540, 294, 70));
        objects.add(new VoltarAoMenu(panel, "/game/image/WBvoltarAoMenu.png", "/game/image/GBvoltarAoMenu.png", 1100, 640, 616, 70));
        objects.add(new SairDoJogo(panel, "/game/image/WBsairDoJogo.png", "/game/image/GBsairDoJogo.png", 1100, 740, 518, 70));
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