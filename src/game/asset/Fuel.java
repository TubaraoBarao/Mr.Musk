package game.asset;

import javax.swing.*;
import game.object.Drawable;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Fuel implements Drawable, Runnable{

    public final double originalHeight = 100;
    public final double x=30, y=800;

    public volatile boolean alive = true;
    public Player player;
    public Image image = new ImageIcon(getClass().getResource("/game/image/fuel.png")).getImage();
    public double height = 100;

    public Fuel(Player player) {
        this.player = player;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void cleanup() {
        alive = false;
    }

    public synchronized void draw(Graphics2D g2) {
        AffineTransform oldTransform = g2.getTransform();
        double scaleY = height / originalHeight;
        g2.translate(x, y+(originalHeight-height));
        g2.scale(1, scaleY);
        g2.drawImage(image, 0, 0, null);
        g2.setTransform(oldTransform);
    }

    public void run(){
        while(alive){
            height = 1 + player.currentFuel * (originalHeight - 1) / player.maxFuel;
        }
    }

}