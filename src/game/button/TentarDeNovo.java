package game.button;

import game.Panel;

public class TentarDeNovo extends Btt {

    public TentarDeNovo(Panel panel, String whiteImage, String grayImage, int x, int y, int width, int height) {
        super(panel, whiteImage, grayImage, x, y, width, height);
    }

    public void action() {
        super.action();
        panel.setRoom(panel.currentRoomIndex);
    }
    
}