package game.asset;

import javax.swing.*;

public class WhiteHole extends CelestialBody{

    public WhiteHole(Player player, String spritePath, double x, double y, double spriteRadius, double portalRadius, double gravitationalFieldRadius, double gravity) {

        this.player = player;
        this.image = new ImageIcon(getClass().getResource(spritePath)).getImage();
        this.x = x;
        this.y = y;
        this.spriteRadius = spriteRadius;
        this.gravitationalFieldRadius = gravitationalFieldRadius;
        this.gravity = gravity;

        player.gravityList.add(this);

    }

}
