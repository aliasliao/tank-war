package Client;

import java.awt.Image;

/**
 * Created by liao on 2016/12/31.
 */
public abstract class Entity {

    protected int x;
    protected int y;
    protected Image image;

    public int getX() { return x; }

    public int getY() { return y; }

    public Image getImage() { return image; }
}
