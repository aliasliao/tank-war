package Server;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * Created by liao on 2016/12/27.
 */
public class Entity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    public Entity(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }

    protected void loadImage(String imageName) {

        ImageIcon imageIcon = new ImageIcon(imageName);
        image = imageIcon.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Image getImage() { return image; }

    public int getX() { return x; }

    public int getY() { return y; }

    public boolean isVisible() { return visible; }

    public void setVisible(boolean visible) { this.visible = visible; }

    public Rectangle getBounds() { return new Rectangle(x, y, width, height); }
}
