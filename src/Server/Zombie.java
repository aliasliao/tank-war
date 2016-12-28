package Server;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;

/**
 * Created by liao on 2016/12/27.
 */
public class Zombie {

    private int x;
    private int y;
    private int width;
    private int height;

    private int speed = 1;
    private Direction direction;
    private GameMap map;
    private HashMap<Direction, Image> imageMap;

    public Zombie(int x, int y, Direction direction, GameMap map) {

        this.x = x;
        this.y = y;
        this.direction = direction;
        this.map = map;
        imageMap = new HashMap<>();

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

        switch (direction) {
            case UP:
                bx = x + width/2; by = y;
                break;
            case DOWN:
                bx = x + width/2; by = y + height;
                break;
            case LEFT:
                bx = x; by = y + height/2;
                break;
            case RIGHT:
                bx = x + width; by = y + height/2;
                break;
            default:
                break;
        }
        map.addBullet(new Bullet(bx, by, direction));
    }

    public Rectangle getBounds() { return new Rectangle(x, y, width, height); }

    public void move() {

        boolean go = !map.isWall(getBounds(), direction);

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
