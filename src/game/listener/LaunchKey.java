package game.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import game.asset.Player;

public class LaunchKey extends MouseAdapter {

    private Player player;

    public LaunchKey(Player player) {
        this.player = player;
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.launched = true;
        }
    }

}
