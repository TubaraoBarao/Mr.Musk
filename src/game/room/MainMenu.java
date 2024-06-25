package game.room;

import game.Panel;
import game.button.*;
import game.object.*;
import java.awt.Graphics2D;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainMenu extends Room {

    private CopyOnWriteArrayList<Drawable> objects;

    public void init(Panel panel) {

        objects = new CopyOnWriteArrayList<>();

        objects.add(new Sprite("/game/image/mrMusk.png", 561, 45, 798, 469));

        if(Objects.equals(panel.notepad.read(), "00000")) {
            objects.add(new NovoJogo(panel, "/game/image/WBnovoJogo.png", "/game/image/GBnovoJogo.png", 748, 565, 413, 70));
            objects.add(new Bcontroles(panel, "/game/image/WBcontroles.png", "/game/image/GBcontroles.png", 740, 665, 441, 70));
            objects.add(new SairDoJogo(panel, "/game/image/WBsairDoJogo.png", "/game/image/GBsairDoJogo.png", 701, 765, 518, 70));
        }
        else {
            objects.add(new Continuar(panel, "/game/image/WBcontinuar.png", "/game/image/GBcontinuar.png", 740, 565, 441, 70));
            objects.add(new NovoJogo(panel, "/game/image/WBnovoJogo.png", "/game/image/GBnovoJogo.png", 748, 665, 413, 70));
            objects.add(new Bcontroles(panel, "/game/image/WBcontroles.png", "/game/image/GBcontroles.png", 740, 765, 441, 70));
            objects.add(new SairDoJogo(panel, "/game/image/WBsairDoJogo.png", "/game/image/GBsairDoJogo.png", 701, 865, 518, 70));
        }

        objects.add(new NotificationBtt(panel, 1800, 20, 64, 68));
        objects.add(new MusicBtt(panel, 1720, 20, 64, 68));
        objects.add(new SoundBtt(panel, 1640, 20, 64, 68));

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