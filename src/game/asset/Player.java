package game.asset;

import game.Panel;
import game.listener.*;
import game.object.Drawable;
import game.object.SoundPlayer;

import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player implements Drawable, Runnable {


    public Image image, standardImage, boostingImage;

    public Player(Panel panel) {
        this.panel = panel;
        initListeners();
        standardImage = new ImageIcon(getClass().getResource("/game/image/ship.png")).getImage();
        boostingImage = new ImageIcon(getClass().getResource("/game/image/boostingShip.png")).getImage();
        image = standardImage;
        new Thread(this).start();
    }

    public final double normalSpeed = 3;
    public final int earthOriginX = 0, earthOriginY = 1080, earthRadius = 200;
    public final int auroraOriginX = 1920, auroraOriginY = 0, auroraRadius = 150;
    public final double maxFuel = 100;
    public final int FPS = 60;
    public final int TARGET_TIME = 1000000000 / FPS;
    public final int spriteRadius = 8;

    public boolean launched = false, boosting = false;
    public double x, y, xOrientation, yOrientation, speed;
    public volatile double currentFuel = maxFuel;
    public Panel panel;
    public volatile boolean paused = false;
    public volatile boolean alive = true;

    public BoostKey boostKey = new BoostKey(this);
    public LaunchKey lauchKey = new LaunchKey(this);
    public PauseKey pauseKey = new PauseKey(this);

    private void initListeners() {
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(boostKey);
        panel.addMouseListener(lauchKey);
        panel.addKeyListener(pauseKey);
    }

    // LISTAS:
    public CopyOnWriteArrayList<CelestialBody> collisionList = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<CelestialBody> gravityList = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<CelestialBody> lightList = new CopyOnWriteArrayList<>();

    // CHECA COLISÕES NA NAVE:
    public void checkCollision(CopyOnWriteArrayList<CelestialBody> list) {
        for (CelestialBody body : list) {
            double deltaX = x - body.x;
            double deltaY = y - body.y;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            if (distance < body.collisionRadius) {
                if (body.partner != null) {
                    x = body.partner.x - (x - body.x);
                    y = body.partner.y - (y - body.y);
                    xOrientation *= 0.1;
                    yOrientation *= 0.1;
                    SoundPlayer soundPlayer = new SoundPlayer("vwoop.wav", true, panel.soundStatus);
                    new Thread(() -> {
                        soundPlayer.play();
                    }).start();
                } else {
                    defeatEvent();
                }
            }
        }
    }

    // CHECA GRAVIDADE NA NAVE:
    public void checkGravity(CopyOnWriteArrayList<CelestialBody> list) {
        for (CelestialBody body : list) {
            double deltaX = body.x - x;
            double deltaY = body.y - y;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            if (distance <= body.gravitationalFieldRadius) {
                double force = body.gravity / distance;
                double xrad = deltaX / distance;
                double yrad = deltaY / distance;
                xOrientation += xrad * force;
                yOrientation += yrad * force;
            }
        }
    }

    // CHECA AS ALTERAÇÕES DE LUZ NA NAVE:
    public void checkLight(CopyOnWriteArrayList<CelestialBody> list) {
        for (CelestialBody body : list) {
            double deltaX = body.x - x;
            double deltaY = body.y - y;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            if (distance <= body.lightFieldRadius) {
                currentFuel += 10*body.light;
                if(currentFuel<0) currentFuel=0;
                if(currentFuel>maxFuel) currentFuel=maxFuel;
            }
        }
    }

    // CHECA SE A NAVE ESTÁ NO OBJETIVO E, PORTANTO, VENCEU A FASE:
    public void checkAurora() {
        double deltaX = auroraOriginX - x;
        double deltaY = auroraOriginY - y;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (distance <= auroraRadius) {
            victoryEvent();
        }
    }

    // CHECA SE A NAVE SAIU DA TELA:
    public void checkOutOfBounds() {
        if (x > 2500 || x < -500 || y > 1500 || y < -500) {
            defeatEvent();
        }
    }

    // IMAGEM:
    public synchronized void draw(Graphics2D g2) {
        AffineTransform oldTransform = g2.getTransform();
        double angle = Math.toDegrees(Math.atan2(yOrientation, xOrientation)) + 90;
        g2.translate(x, y);
        g2.rotate(Math.toRadians(angle), spriteRadius, spriteRadius);
        g2.drawImage(image, -spriteRadius, -spriteRadius, null);
        g2.setTransform(oldTransform);
    }

    public void cleanup() {
        panel.removeKeyListener(boostKey);
        panel.removeMouseListener(lauchKey);
        panel.removeKeyListener(pauseKey);
        alive = false;
    }

    public void victoryEvent() {
        SoundPlayer soundPlayer = new SoundPlayer("aplause.wav", true, panel.soundStatus);
        new Thread(() -> {
            soundPlayer.play();
        }).start();
        this.launched = false;

        StringBuilder sb = new StringBuilder(panel.notepad.read());
        sb.setCharAt(panel.currentRoomIndex-1, '1');
        panel.notepad.update(sb.toString());

        panel.setRoom(6);
    }

    public void defeatEvent() {
        SoundPlayer soundPlayer = new SoundPlayer("boo.wav", true, panel.soundStatus);
        new Thread(() -> {
            soundPlayer.play();
        }).start();
        panel.setRoom(7);
    }

    // STEP EVENT:
    @Override
    public void run() {
        while (alive) {
            long startTime = System.nanoTime();
            if (!paused) {
                if (launched) {
                    if (boosting && currentFuel > 0 ) {
                        image = boostingImage;
                        double yBoost = 1;
                        double xBoost = Math.abs(xOrientation / yOrientation);
                        double mag = Math.sqrt(xBoost * xBoost + yBoost * yBoost);
                        yBoost /= mag*50;
                        xBoost /= mag*50;
                        xOrientation += Math.signum(xOrientation) * xBoost;
                        yOrientation += Math.signum(yOrientation) * yBoost;

                        currentFuel -= 1;
                        if (currentFuel < 0) currentFuel = 0;
                    } else image = standardImage;
                    speed = normalSpeed;
                    checkCollision(collisionList);
                    checkGravity(gravityList);
                    checkLight(lightList);
                    checkAurora();
                    checkOutOfBounds();
                    x += xOrientation * speed;
                    y += yOrientation * speed;
                } else {
                    double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
                    double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
                    double deltaX = mouseX - earthOriginX;
                    double deltaY = mouseY - earthOriginY;
                    double actualDistance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                    xOrientation = deltaX / actualDistance;
                    yOrientation = deltaY / actualDistance;
                    double newX = earthOriginX + xOrientation * earthRadius;
                    double newY = earthOriginY + yOrientation * earthRadius;
                    x = newX;
                    y = newY;
                }
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