package Client;

import java.io.Serializable;

/**
 * Created by liao on 2016/12/31.
 */
public class KeySignal implements Serializable {

    public enum KeyType { PRESS, RELEASE }
    public enum KeyCode { UP, DOWN, LEFT, RIGHT, SPACE }

    public KeyType type;
    public KeyCode code;
}
