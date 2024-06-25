package game.button;

import game.Panel;

public class Continuar extends Btt {

    public Continuar(Panel panel, String whiteImage, String grayImage, int x, int y, int width, int height) {
        super(panel, whiteImage, grayImage, x, y, width, height);
    }

    public void action() {
        super.action();
        panel.setRoom(9);
    }
    
}