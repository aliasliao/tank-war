package Client;

import java.io.Serializable;

/**
 * Created by liao on 2016/12/31.
 */
class Frame implements Serializable {

    enum Direction { UP, DOWN, LEFT, RIGHT }

    enum WallType { WEAK, STRONG }

    class MoveObject {
        int x;
        int y;
        Direction direction;

        void init(int x, int y, Direction d) {
            this.x = x;
            this.y = y;
            this.direction = d;
        }
    }

    class Wall {
        int x;
        int y;
        WallType type;

        void init(int x, int y, WallType t) {
            this.x = x;
            this.y = y;
            this.type = t;
        }
    }

    MoveObject tank1, tank2;
    MoveObject[] zombies;
    MoveObject[] bullets;
    Wall[] walls;

    Frame(int zn, int bn, int wn) {
        tank1 = new MoveObject();
        tank2 = new MoveObject();

        zombies = new MoveObject[zn];
        bullets = new MoveObject[bn];
        walls = new Wall[wn];

        for (int i=0; i<zn; i++)
            zombies[i] = new MoveObject();
        for (int i=0; i<bn; i++)
            bullets[i] = new MoveObject();
        for (int i=0; i<wn; i++)
            walls[i] = new Wall();
    }
}
