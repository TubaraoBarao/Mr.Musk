package game.asset;

import javax.swing.*;

public class BlackHole extends CelestialBody{

    public BlackHole(Player player, WhiteHole partner, String spritePath, double x, double y, double spriteRadius, double collisionRadius, double gravitationalFieldRadius, double gravity) {

        this.player = player;
        this.partner = partner;
        this.image = new ImageIcon(getClass().getResource(spritePath)).getImage();
        this.x = x;
        this.y = y;
        this.spriteRadius = spriteRadius;
        this.collisionRadius = collisionRadius;
        this.gravitationalFieldRadius = gravitationalFieldRadius;
        this.gravity = gravity;

        player.gravityList.add(this);
        player.collisionList.add(this);

    }

}
