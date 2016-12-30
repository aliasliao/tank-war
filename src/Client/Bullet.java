package Client;

/**
 * Created by liao on 2016/12/31.
 */
public class Bullet extends MoveEntity {

    public Bullet(Frame.MoveObject bullet) {

        x = bullet.x;
        y = bullet.y;
        direction = Direction.valueOf(direction.toString());

        loadImage();
    }

    @Override
    protected String getImageName() {

        return "img/bullet_" + direction.toString().toLowerCase() + ".png";
    }
}
