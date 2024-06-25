package game;

import game.object.*;
import game.room.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JComponent {

    private Graphics2D g2;
    private BufferedImage image;
    private int width;
    private int height;
    private Thread thread;
    private boolean running = true;
    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;

    private Room currentRoom;
    public int currentRoomIndex;
    public volatile boolean notification = true;
    public volatile boolean musicStatus = true;
    public volatile boolean soundStatus = true;

    public Notepad notepad = new Notepad();
    public volatile SoundPlayer music;

    public void setRoom(int index) {

        switch (index) {

            // 0 - para menu;
            // 1 - para nível 1;
            // 2 - para nível 2;
            // 3 - para nível 3;
            // 4 - para nível 4;
            // 5 - para nível 5;
            // 6 - para victoryRoom;
            // 7 - para defeatRoom;
            // 8 - para próximo nível;
            // 9 - para seleção de níveis;

            case 0:
                changeRoom(new MainMenu());
                break;
            case 1:
                currentRoomIndex = 1;
                changeRoom(new Level(1));
                break;
            case 2:
                currentRoomIndex = 2;
                changeRoom(new Level(2));
                break;
            case 3:
                currentRoomIndex = 3;
                changeRoom(new Level(3));
                break;
            case 4:
                currentRoomIndex = 4;
                changeRoom(new Level(4));
                break;
            case 5:
                currentRoomIndex = 5;
                changeRoom(new Level(5));
                break;
            case 6:
                changeRoom(new VictoryMenu(currentRoomIndex));
                break;
            case 7:
                changeRoom(new DefeatMenu());
                break;
            case 8:
                setRoom(currentRoomIndex+1);
                break;
            case 9:
                changeRoom(new LevelSelection());
                break;
            case 10:
                changeRoom(new Cutscene());
                break;
            case 11:
                changeRoom(new Controls());
                break;
            default: break;

        }

    }

    public void start() {

        width = getWidth();
        height = getHeight();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        currentRoom = new MainMenu();
        currentRoom.init(this);
        currentRoomIndex = 0;
        new Thread(() -> {
            music = new SoundPlayer("tem.wav", false, soundStatus);
            music.setVolume(-20.0f);
            music.loop();
        }).start();

        thread = new Thread(() -> {
            while (running) {

                long startTime = System.nanoTime();

                draw();
                render();

                long time = System.nanoTime() - startTime;
                if (time < TARGET_TIME) {
                    long sleepTime = (TARGET_TIME - time) / 1000000;
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread.start();
    }

    private void draw() {
        g2.setColor(new Color(30, 30, 30));
        g2.fillRect(0, 0, width, height);
        currentRoom.draw(g2);
    }

    private void render() {
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    public synchronized void changeRoom(Room newRoom) {
        currentRoom.cleanup();
        currentRoom = newRoom;
        currentRoom.init(this);
    }
}
