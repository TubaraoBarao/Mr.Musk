package game.button;

import game.Panel;

public class Bcutscene extends Btt {

    public Bcutscene(Panel panel, String whiteImage, String grayImage, int x, int y, int width, int height) {
        super(panel, whiteImage, grayImage, x, y, width, height);
    }

    public void action() {
        super.action();
        panel.setRoom(10);
    }
    
}