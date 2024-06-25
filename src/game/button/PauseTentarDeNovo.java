package game.button;

import game.Panel;
import game.asset.Player;

public class PauseTentarDeNovo extends PauseBtt {

    public PauseTentarDeNovo(Player player, Panel panel, String whiteImage, String grayImage, int x, int y, int width, int height) {
        super(player, panel, whiteImage, grayImage, x, y, width, height);
    }

    public void action() {
        if(player.paused) {
            super.action();
            panel.setRoom(panel.currentRoomIndex);
        }
    }

}