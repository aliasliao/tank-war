package Server;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by liao on 2016/12/27.
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Direction getRandomNext() {

        Direction[] directions = Direction.values();
        Random random = new Random();
        return directions[random.nextInt(directions.length)];
    }
}
