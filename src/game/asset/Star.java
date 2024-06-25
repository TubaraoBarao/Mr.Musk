package game.asset;

import javax.swing.*;

public class Star extends CelestialBody {

    public Star(Player player, String spritePath, double x, double y, double spriteRadius, double collisionRadius, double gravityFieldRadius, double gravity, double lightFieldRadius, double light){

        this.player = player;
        this.image = new ImageIcon(getClass().getResource(spritePath)).getImage();
        this.x = x;
        this.y = y;
        this.spriteRadius = spriteRadius;
        this.collisionRadius = collisionRadius;
        this.gravitationalFieldRadius = gravityFieldRadius;
        this.gravity = gravity;
        this.lightFieldRadius = lightFieldRadius;
        this.light = light;

        player.gravityList.add(this);
        player.collisionList.add(this);
        player.lightList.add(this);

    }

}