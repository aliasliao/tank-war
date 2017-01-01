package Server;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.util.HashMap;

/**
 * Created by liao on 2016/12/27.
 */
public class Zombie extends MoveEntity {

    private GameMap map;
    private HashMap<Direction, Image> imageMap;
    private final int fireSpeed = 60; // dps=40/fs
    private int count = 0;

    public Zombie(int x, int y, Direction direction, GameMap map) {

        this.x = x;
        this.y = y;
        this.direction = direction;
        this.map = map;
        imageMap = new HashMap<>();

        speed = 1;

        initImageMap();
        loadImage();
    }

    private void initImageMap() {

        Image im;
        ImageIcon ii;

        for (Direction d : Direction.values()) {
            ii = new ImageIcon(getImageName(d));
            im = ii.getImage();
            imageMap.put(d, im);
        }
    }

    private void loadImage() {

        Image im = imageMap.get(direction);
        width = im.getWidth(null);
        height = im.getHeight(null);
    }

    private String getImageName(Direction d) {

        return "img/zombie_" + d.toString().toLowerCase() + ".png";
    }

    public void fire() {

        int bx = x, by = y;
        Bullet newBullet = null;

        switch (direction) {
            case UP:
                bx = x + width/2; by = y;
                newBullet = new Bullet(bx, by, direction);
                newBullet.setY(by - newBullet.getHeight());
                break;
            case DOWN:
                bx = x + width/2; by = y + height;
                newBullet = new Bullet(bx, by, direction);
                break;
            case LEFT:
                bx = x; by = y + height/2;
                newBullet = new Bullet(bx, by, direction);
                newBullet.setX(bx - newBullet.getWidth());
                break;
            case RIGHT:
                bx = x + width; by = y + height/2;
                newBullet = new Bullet(bx, by, direction);
                break;
            default:
                break;
        }
        map.addBullet(newBullet);
    }

    public void move() {

        if (count >= fireSpeed) {
            count = 0;
            fire();
        }
        else
            count++;

        boolean go = !map.isBlocked(this);

        if (go) {
            switch (direction) {
                case UP:    y -= speed; break;
                case DOWN:  y += speed; break;
                case LEFT:  x -= speed; break;
                case RIGHT: x += speed; break;
                default:
                    break;
            }
        }
        else {
            direction = direction.getRandomNext();
            loadImage();
        }
    }
}
