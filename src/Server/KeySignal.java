package Server;

import java.io.Serializable;

/**
 * Created by liao on 2016/12/29.
 */
public class KeySignal implements Serializable {

    enum KeyType { PRESS, RELEASE }
    enum KeyCode { UP, DOWN, LEFT, RIGHT, SPACE }

    KeyType type;
    KeyCode code;
}
