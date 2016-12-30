package Server;

import java.awt.Rectangle;

/**
 * Created by liao on 2016/12/30.
 */
public class Entity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public Rectangle getBounds() { return new Rectangle(x, y, width, height); }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }
}
