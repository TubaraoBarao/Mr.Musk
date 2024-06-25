package game.button;

import game.Panel;

public class NovoJogo extends Btt {

    public NovoJogo(Panel panel, String whiteImage, String grayImage, int x, int y, int width, int height) {
        super(panel, whiteImage, grayImage, x, y, width, height);
    }

    public void action() {
        super.action();
        panel.notepad.update("00000");
        panel.setRoom(9);
    }
    
}