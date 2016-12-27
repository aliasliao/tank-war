package Server;

/**
 * Created by liao on 2016/12/27.
 */
public class Zombie extends Entity {

    private int speed = 1;
    private Direction direction;
    GameMap map;

    public Zombie(int x, int y, Direction direction, GameMap map) {

        super(x, y);
        this.direction = direction;
        this.map = map;
    }
}
