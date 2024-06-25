package game.listener;

import game.asset.Player;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PauseKey extends KeyAdapter {

    Player player;

    public PauseKey(Player player) {
        this.player = player;
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            player.paused = !player.paused;
        }
    }

}
