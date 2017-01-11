package Server;

import javax.swing.ImageIcon;

import java.awt.Image;

/**
 * Created by liao on 2016/12/27.
 */
public class Bullet extends MoveEntity {

    public Bullet(int x, int y, Direction direction) {

        this.x = x;
        this.y = y;
        this.direction = direction;

        speed = 3;

        loadImage();
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon(getImageName());
        Image im = ii.getImage();
        width = im.getWidth(null);
        height = im.getHeight(null);
    }

    private String getImageName() {

//        return getClass().getResource("/img/bullet_" + direction.toString().toLowerCase() + ".png").getFile();
        return "img/bullet_" + direction.name().toLowerCase() + ".png";
    }

    public Direction getDirection() { return direction; }

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
