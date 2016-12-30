package Server;

import java.io.Serializable;

/**
 * Created by liao on 2016/12/28.
 */
public class Frame implements Serializable {

    enum Direction { UP, DOWN, LEFT, RIGHT }

    enum WallType { WEAK, STRONG }

    class MoveObject {
        int x;
        int y;
        Direction direction;
    }

    class Wall {
        int x;
        int y;
        WallType type;
    }

    MoveObject tank1;
    MoveObject tank2;
    MoveObject[] zombies;
    MoveObject[] bullets;
    Wall[] walls;
}
