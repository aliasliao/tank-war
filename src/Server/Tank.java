package Server;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.util.HashMap;

/**
 * Created by liao on 2016/12/27.
 */
public class Tank extends MoveEntity {

    private GameMap map;
    private HashMap<Direction, Image> imageMap;
    private final int MAXSPEED = 2;

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

        return getClass().getResource("/img/tank_" + d.toString().toLowerCase() + ".png").getFile();
    }

    public void move() { // each frame

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

    public void keyPressed(KeyCode key) {

        switch (key) {
            case SPACE:
                fire();
                break;
            case UP:
                direction = Direction.UP;
                loadImage();
                speed = MAXSPEED;
                break;
            case DOWN:
                direction = Direction.DOWN;
                loadImage();
                speed = MAXSPEED;
                break;
            case LEFT:
                direction = Direction.LEFT;
                loadImage();
                speed = MAXSPEED;
                break;
            case RIGHT:
                direction = Direction.RIGHT;
                loadImage();
                speed = MAXSPEED;
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
