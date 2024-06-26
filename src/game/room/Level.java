package game.room;

import game.Panel;
import game.asset.*;
import game.button.*;
import game.object.*;

import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.Graphics2D;

public class Level extends Room {

    private CopyOnWriteArrayList<Drawable> objects;
    public int index;
    public Panel panel;

    public Level(int index) {
        this.index = index;
    }

    public void init(Panel panel) {
        objects = new CopyOnWriteArrayList<>();
        this.panel = panel;

        switch(this.index) {
            case 1:
                Level1();
                break;
            case 2:
                Level2();
                break;
            case 3:
                Level3();
                break;
            case 4:
                Level4();
                break;
            case 5:
                Level5();
                break;
            default: break;

        }

    }

    public void Level1() {

        Player player = new Player(panel);
        objects.add(new Space());
        objects.add(player);
        objects.add(new Earth());
        objects.add(new Aurora());

        objects.add(new Planet(player, "/game/image/planet2-2.png", 400, 400, 64, 32, 500, 5));
        objects.add(new Planet(player, "/game/image/planet1-3.png", 1000, 800, 32, 16, 500, 5));
        objects.add(new Planet(player, "/game/image/planet2-3.png", 1500, 600, 32, 32, 500, 5));

        objects.add(new Fuel(player));
        objects.add(new Sprite("/game/image/cap.png", 26, 796, 28, 108));

        objects.add(new GrayBack(player));
        objects.add(new PauseTentarDeNovo(player, panel, "/game/image/WBtentarDeNovo.png", "/game/image/GBtentarDeNovo.png", 600, 300, 616, 70));
        objects.add(new PauseNiveis(player, panel, "/game/image/WBniveis.png", "/game/image/GBniveis.png", 600, 400, 294, 70));
        objects.add(new PauseVoltarAoMenu(player, panel, "/game/image/WBvoltarAoMenu.png", "/game/image/GBvoltarAoMenu.png", 600, 500, 616, 70));
        objects.add(new PauseSairDoJogo(player, panel, "/game/image/WBsairDoJogo.png", "/game/image/GBsairDoJogo.png", 600, 600, 518, 70));

        if(panel.notificationStatus) objects.add(new Notification(player,"/game/image/n1.png" ));

    }

    public void Level2() {

        Player player = new Player(panel);
        objects.add(new Space());
        objects.add(player);
        objects.add(new Star(player, "/game/image/purpleStar.png", 700, 200, 500, 32, 500, 20, 500, -1));
        objects.add(new Star(player, "/game/image/yellowStar.png", 1400, 700, 500, 32, 500, 20, 500, 1));
        objects.add(new Earth());
        objects.add(new Aurora());
        objects.add(new Planet(player, "/game/image/planet2-1.png", 400, 600, 64, 32, 300, 5));
        objects.add(new Planet(player, "/game/image/planet1-2.png", 1000, 800, 32, 16, 300, 5));

        objects.add(new Fuel(player));
        objects.add(new Sprite("/game/image/cap.png", 26, 796, 28, 108));

        objects.add(new GrayBack(player));
        objects.add(new PauseTentarDeNovo(player, panel, "/game/image/WBtentarDeNovo.png", "/game/image/GBtentarDeNovo.png", 600, 300, 616, 70));
        objects.add(new PauseNiveis(player, panel, "/game/image/WBniveis.png", "/game/image/GBniveis.png", 600, 400, 294, 70));
        objects.add(new PauseVoltarAoMenu(player, panel, "/game/image/WBvoltarAoMenu.png", "/game/image/GBvoltarAoMenu.png", 600, 500, 616, 70));
        objects.add(new PauseSairDoJogo(player, panel, "/game/image/WBsairDoJogo.png", "/game/image/GBsairDoJogo.png", 600, 600, 518, 70));

        if(panel.notificationStatus) objects.add(new Notification(player,"/game/image/n2.png" ));

    }

    public void Level3() {

        Player player = new Player(panel);
        objects.add(new Space());
        objects.add(player);
        objects.add(new Star(player, "/game/image/purpleStar.png", 700, 200, 500, 32, 500, 20, 500, -1));
        objects.add(new Earth());
        objects.add(new Aurora());
        objects.add(new Planet(player, "/game/image/planet2-1.png", 400, 600, 64, 32, 500, 5));
        objects.add(new Planet(player, "/game/image/planet1-2.png", 1000, 800, 32, 16, 500, 5));
        objects.add(new Asteroid(player, "/game/image/asteroid.png", 1000, 1144, 120, 32, 32, 1));
        objects.add(new Asteroid(player, "/game/image/asteroid.png", 2000, 400, 150, 32, 32, 1));
        objects.add(new Asteroid(player, "/game/image/asteroid.png", -100, 500, 30, 32, 32, 1));

        objects.add(new Fuel(player));
        objects.add(new Sprite("/game/image/cap.png", 26, 796, 28, 108));

        objects.add(new GrayBack(player));
        objects.add(new PauseTentarDeNovo(player, panel, "/game/image/WBtentarDeNovo.png", "/game/image/GBtentarDeNovo.png", 600, 300, 616, 70));
        objects.add(new PauseNiveis(player, panel, "/game/image/WBniveis.png", "/game/image/GBniveis.png", 600, 400, 294, 70));
        objects.add(new PauseVoltarAoMenu(player, panel, "/game/image/WBvoltarAoMenu.png", "/game/image/GBvoltarAoMenu.png", 600, 500, 616, 70));
        objects.add(new PauseSairDoJogo(player, panel, "/game/image/WBsairDoJogo.png", "/game/image/GBsairDoJogo.png", 600, 600, 518, 70));

        if(panel.notificationStatus) objects.add(new Notification(player,"/game/image/n3.png" ));

    }

    public void Level4() {

        Player player = new Player(panel);
        objects.add(new Space());
        objects.add(player);
        objects.add(new Earth());
        objects.add(new Aurora());
        objects.add(new Star(player, "/game/image/purpleStar.png", 950, 900, 500, 32, 500, 20, 500, -1));
        WhiteHole whiteHole = new WhiteHole(player, "/game/image/whiteHole.png", 1600, 300, 32, 32, 300, -10);
        objects.add(whiteHole);
        objects.add(new BlackHole(player, whiteHole, "/game/image/blackHole.png", 400, 400, 64, 32, 300, 50));

        objects.add(new Planet(player, "/game/image/planet2-1.png", 1124, 80, 64, 32, 500, 0.00001));
        objects.add(new Planet(player, "/game/image/planet2-3.png", 1200, 200, 32, 32, 500, 0.00001));
        objects.add(new Planet(player, "/game/image/planet1-2.png", 1300, 400, 32, 16, 500, 0.00001));
        objects.add(new Planet(player, "/game/image/planet2-2.png", 1500, 500, 64, 32, 500, 0.00001));
        objects.add(new Planet(player, "/game/image/planet1-3.png", 1750, 600, 16, 32, 500, 0.00001));

        objects.add(new Fuel(player));
        objects.add(new Sprite("/game/image/cap.png", 26, 796, 28, 108));

        objects.add(new GrayBack(player));
        objects.add(new PauseTentarDeNovo(player, panel, "/game/image/WBtentarDeNovo.png", "/game/image/GBtentarDeNovo.png", 600, 300, 616, 70));
        objects.add(new PauseNiveis(player, panel, "/game/image/WBniveis.png", "/game/image/GBniveis.png", 600, 400, 294, 70));
        objects.add(new PauseVoltarAoMenu(player, panel, "/game/image/WBvoltarAoMenu.png", "/game/image/GBvoltarAoMenu.png", 600, 500, 616, 70));
        objects.add(new PauseSairDoJogo(player, panel, "/game/image/WBsairDoJogo.png", "/game/image/GBsairDoJogo.png", 600, 600, 518, 70));

        if(panel.notificationStatus) objects.add(new Notification(player,"/game/image/n4.png" ));

    }

    public void Level5() {

        Player player = new Player(panel);
        objects.add(new Space());
        objects.add(player);

        objects.add(new Star(player, "/game/image/purpleStar.png", 300, 700, 500, 32, 500, 20, 500, -1));
        objects.add(new Star(player, "/game/image/yellowStar.png", 1300, 200, 500, 32, 500, 30, 500, -1));

        objects.add(new Earth());
        objects.add(new Aurora());


        WhiteHole whiteHole = new WhiteHole(player, "/game/image/whiteHole.png", 1400, 800, 32, 32, 300, -2);
        objects.add(whiteHole);
        objects.add(new BlackHole(player, whiteHole, "/game/image/blackHole.png", 500, 400, 64, 32, 300, 50));

        objects.add(new Planet(player, "/game/image/planet2-1.png", 1200, 500, 64, 32, 500, 8));


        objects.add(new Asteroid(player, "/game/image/asteroid.png", 1100, 1144, 120, 32, 32, 2));
        objects.add(new Asteroid(player, "/game/image/asteroid.png", 1150, 1144, 120, 32, 32, 1.3));
        objects.add(new Asteroid(player, "/game/image/asteroid.png", 1200, 1144, 120, 32, 32, 1));
        objects.add(new Asteroid(player, "/game/image/asteroid.png", 1250, 1144, 120, 32, 32, 0.7));
        objects.add(new Asteroid(player, "/game/image/asteroid.png", 1300, 1144, 120, 32, 32, 1.1));

        objects.add(new Fuel(player));
        objects.add(new Sprite("/game/image/cap.png", 26, 796, 28, 108));

        objects.add(new GrayBack(player));
        objects.add(new PauseTentarDeNovo(player, panel, "/game/image/WBtentarDeNovo.png", "/game/image/GBtentarDeNovo.png", 600, 300, 616, 70));
        objects.add(new PauseNiveis(player, panel, "/game/image/WBniveis.png", "/game/image/GBniveis.png", 600, 400, 294, 70));
        objects.add(new PauseVoltarAoMenu(player, panel, "/game/image/WBvoltarAoMenu.png", "/game/image/GBvoltarAoMenu.png", 600, 500, 616, 70));
        objects.add(new PauseSairDoJogo(player, panel, "/game/image/WBsairDoJogo.png", "/game/image/GBsairDoJogo.png", 600, 600, 518, 70));

        if(panel.notificationStatus) objects.add(new Notification(player,"/game/image/n5.png" ));

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