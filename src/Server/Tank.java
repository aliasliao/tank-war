package Server;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;

/**
 * Created by liao on 2016/12/27.
 */
public class Tank {

    private int x;
    private int y;
    private int width;
    private int height;

    private  Direction direction;
    private int speed; // >=0
    private GameMap map;
    private HashMap<Direction, Image> imageMap;

    public Tank(int x, int y, Direction direction, GameMap map) {

        this.x = x;
        this.y = y;
        this.direction = direction;
        this.map = map;
        speed = 0;
        imageMap = new HashMap<>();

        initImageMap();
        loadImage();
    }

    private void initImageMap() {

        Image im;
        ImageIcon ii;

        for (Direction d : Direction.values()) {
            ii = new ImageIcon(getImageName(direction));
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

        return "img/tank_" + d.toString().toLowerCase() + ".png";
    }

    public void move() { // each frame

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
    }

    public void fire() {

        int bx=x, by=y;

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

    public void keyPressed(KeyCode key) {

        switch (key) {
            case SPACE:
                fire();
                break;
            case UP:
                direction = Direction.UP;
                loadImage();
                speed = 1;
                break;
            case DOWN:
                direction = Direction.DOWN;
                loadImage();
                speed = 1;
                break;
            case LEFT:
                direction = Direction.LEFT;
                loadImage();
                speed = 1;
                break;
            case RIGHT:
                direction = Direction.RIGHT;
                loadImage();
                speed = 1;
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyCode key) {

        switch (key) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                speed = 0;
            default:
                break;
        }
    }
}
