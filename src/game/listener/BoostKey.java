package game.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import game.asset.Player;

public class BoostKey extends KeyAdapter {

    private Player player;

    public BoostKey(Player player) {
        this.player = player;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            player.boosting = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            player.boosting = false;
        }
    }

}
