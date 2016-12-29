package Server;

import java.awt.Rectangle;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by liao on 2016/12/27.
 */
public class GameMap {

    private Tank tank1, tank2;
    private ArrayList<Zombie> zombies;
    private ArrayList<Bullet> bullets;
    private ArrayList<Wall> walls;
    private Connection connection;
    private ObjectOutputStream output1, output2;

    public GameMap() {

        zombies = new ArrayList<>();
        bullets = new ArrayList<>();
        walls = new ArrayList<>();

        connection = new Connection();
        output1 = connection.getOutput1();
        output2 = connection.getOutput2();
    }

    public void addBullet(Bullet bullet) {

        bullets.add(bullet);
    }

    public boolean isWall(Rectangle bound, Direction direction) {

        return true;
    }
}
