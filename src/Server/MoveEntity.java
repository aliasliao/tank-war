package Server;

import java.awt.Rectangle;

/**
 * Created by liao on 2016/12/30.
 */
public class MoveEntity extends Entity {

    protected Direction direction;
    protected int speed;

    public Direction getDirection() { return direction; }

    public Rectangle getFutureBound() {

        switch (direction) {
            case UP:
                return new Rectangle(x, y-speed, width, height);
            case DOWN:
                return new Rectangle(x, y+speed, width, height);
            case LEFT:
                return new Rectangle(x-speed, y, width, height);
            case RIGHT:
                return new Rectangle(x+speed, y, width, height);
        }

        return null;
    }
}
