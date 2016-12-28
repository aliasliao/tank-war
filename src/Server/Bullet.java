package Server;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;

/**
 * Created by liao on 2016/12/27.
 */
public class Bullet {

    private int x;
    private int y;
    private int width;
    private int height;

    private Direction direction;
    private int speed = 2;

    public Bullet(int x, int y, Direction direction) {

        this.x = x;
        this.y = y;
        this.direction = direction;

        loadImage();
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon(getImageName());
        Image im = ii.getImage();
        width = im.getWidth(null);
        height = im.getHeight(null);
    }

    private String getImageName() {

        return "img/bullet_" + direction.toString().toLowerCase() + ".png";
    }

    public Rectangle getBounds() { return new Rectangle(x, y, width, height); }

    public void move() {

        switch (direction) {
            case UP:    y -= speed; break;
            case DOWN:  y += speed; break;
            case LEFT:  x -= speed; break;
            case RIGHT: x += speed; break;
            default: break;
        }
    }
}
