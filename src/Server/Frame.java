package Server;

import java.io.Serializable;

/**
 * Created by liao on 2016/12/28.
 */
public class Frame implements Serializable {

    public enum Direction { UP, DOWN, LEFT, RIGHT }
    public enum WallType { WEAK, STRONG }

    public class MoveObject implements Serializable {
        public int x;
        public int y;
        public Direction direction;

        void init(int x, int y, Direction d) {
            this.x = x;
            this.y = y;
            this.direction = d;
        }
    }

    public class Wall implements Serializable {
        public int x;
        public int y;
        public WallType type;

        void init(int x, int y, WallType t) {
            this.x = x;
            this.y = y;
            this.type = t;
        }
    }

    public MoveObject tank1, tank2;
    public MoveObject[] zombies;
    public MoveObject[] bullets;
    public Wall[] walls;
    public String msg;

    public Frame(int zn, int bn, int wn) {
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

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
