package game.room;

import game.Panel;
import game.button.*;
import game.object.*;
import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class VictoryMenu extends Room {

    private CopyOnWriteArrayList<Drawable> objects;
    public int level;

    public VictoryMenu(int level) {
        this.level = level;
    }

    public void init(Panel panel) {

        objects = new CopyOnWriteArrayList<>();

        if(level==5) {
            objects.add(new Sprite("/game/image/sucesso.png", 500, 390, 427, 70));
            objects.add(new Niveis(panel, "/game/image/WBniveis.png", "/game/image/GBniveis.png", 500, 490, 294, 70));
            objects.add(new VoltarAoMenu(panel, "/game/image/WBvoltarAoMenu.png", "/game/image/GBvoltarAoMenu.png", 500, 590, 616, 70));
            objects.add(new SairDoJogo(panel, "/game/image/WBsairDoJogo.png", "/game/image/GBsairDoJogo.png", 500, 690, 518, 70));
        }
        else {
            objects.add(new Sprite("/game/image/sucesso.png", 500, 340, 427, 70));
            objects.add(new ProximoNivel(panel, "/game/image/WBproximoNivel.png", "/game/image/GBproximoNivel.png", 500, 440, 602, 70));
            objects.add(new Niveis(panel, "/game/image/WBniveis.png", "/game/image/GBniveis.png", 500, 540, 294, 70));
            objects.add(new VoltarAoMenu(panel, "/game/image/WBvoltarAoMenu.png", "/game/image/GBvoltarAoMenu.png", 500, 640, 616, 70));
            objects.add(new SairDoJogo(panel, "/game/image/WBsairDoJogo.png", "/game/image/GBsairDoJogo.png", 500, 740, 518, 70));
        }

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