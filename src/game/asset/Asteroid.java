package game.asset;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Asteroid extends CelestialBody implements Runnable{

    public double speed = 5;
    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;

    public double xFrom, yFrom, direction;
    public volatile boolean alive = true;
    public Player player;

    public Asteroid(Player player, String spritePath, double xFrom, double yFrom, double direction, double spriteRadius, double collisionRadius, double speed) {
        this.image = new ImageIcon(getClass().getResource(spritePath)).getImage();
        this.xFrom = xFrom;
        this.yFrom = yFrom;
        this.x = xFrom;
        this.y = yFrom;
        this.spriteRadius = spriteRadius;
        this.direction = -direction;
        this.collisionRadius = collisionRadius;
        this.player = player;
        this.speed *= speed;
        player.collisionList.add(this);
        Thread thread = new Thread(this);
        thread.start();
    }

    public void checkOutZone() {
        if(x>2500 || x< -500 || y>1500 || y< -500) {
            x = xFrom;
            y = yFrom;
        }
    }

    public void cleanup() {
        alive = false;
    }

    public synchronized void draw(Graphics2D g2){
        AffineTransform oldTransform = g2.getTransform();
        g2.translate(x, y);
        g2.rotate(Math.toRadians(direction), 8, 8);
        g2.drawImage(image, -8, -8, null);
        g2.setTransform(oldTransform);
    }

    public void run() {

        while(alive){

            long startTime = System.nanoTime();

            if(player.paused) { }
            else {
                checkOutZone();
                this.x += Math.cos(Math.toRadians(direction)) * speed;
                this.y += Math.sin(Math.toRadians(direction)) * speed;
            }

            long time = System.nanoTime() - startTime;
            if (time < TARGET_TIME) {
                long sleep = (TARGET_TIME - time) / 1000000;
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}