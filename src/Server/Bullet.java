package Server;

/**
 * Created by liao on 2016/12/27.
 */
public class Bullet extends Entity {

    private Direction direction;
    int speed = 2;

    public Bullet(int x, int y, Direction direction) {

        super(x, y);
        this.direction = direction;
        loadImage(getImageName());
    }

    private String getImageName() {

        return "img/bullet_" + direction.toString().toLowerCase() + ".png";
    }

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
