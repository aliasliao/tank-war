package Server;

/**
 * Created by liao on 2016/12/27.
 */
public class Tank extends Entity {

    private  Direction direction;
    private int speed; // >=0
    private GameMap map;

    public Tank(int x, int y, Direction direction, GameMap map) {

        super(x, y);
        this.direction = direction;
        loadImage(getImageNamge());
        speed = 0;
        this.map = map;
    }

    private String getImageNamge() {

        return "img/tank_" + direction.toString().toLowerCase() + ".png";
    }

    public void move() { // each frame

        boolean go = !map.isWall(getBounds(), direction);

        switch (direction) {
            case UP:
                if (go) y -= speed; break;
            case DOWN:
                if (go) y += speed; break;
            case LEFT:
                if (go) x -= speed; break;
            case RIGHT:
                if (go) x += speed; break;
            default:
                break;
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

    public void keyPressed(KeyCode key) {

        switch (key) {
            case SPACE:
                fire();
                break;
            case UP:
                direction = Direction.UP;
                speed = 1;
                break;
            case DOWN:
                direction = Direction.DOWN;
                speed = 1;
                break;
            case LEFT:
                direction = Direction.LEFT;
                speed = 1;
                break;
            case RIGHT:
                direction = Direction.RIGHT;
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
