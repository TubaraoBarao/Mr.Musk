package game.asset;
import javax.swing.*;

public class Planet extends CelestialBody {

    public Planet(Player player, String spritePath, double x, double y, double spriteRadius, double collisionRadius, double gravitationalFieldRadius, double gravity) {

        this.player = player;
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
