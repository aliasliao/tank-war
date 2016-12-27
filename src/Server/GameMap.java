package Server;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Created by liao on 2016/12/27.
 */
public class GameMap {

    private ArrayList<Bullet> bullets;
    private ArrayList<Wall> walls;
    private ArrayList<Zombie> zombies;
    private Tank tank1;
    private Tank tank2;

    public GameMap() {

        bullets = new ArrayList<>();
        walls = new ArrayList<>();
    }

    public void addBullet(Bullet bullet) {

        bullets.add(bullet);
    }

    public boolean isWall(Rectangle bound, Direction direction) {

        return true;
    }
}
