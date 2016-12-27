package Server;

import java.io.Serializable;

/**
 * Created by liao on 2016/12/28.
 */
public class Frame implements Serializable {

    class Coordinate {
        int x;
        int y;
    }

    Coordinate tank1;
    Coordinate tank2;
    Coordinate[] bullets;
    Coordinate[] walls;
}
