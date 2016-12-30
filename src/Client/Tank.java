package Client;

/**
 * Created by liao on 2016/12/31.
 */
public class Tank extends MoveEntity {

    public Tank(Frame.MoveObject tank) {

        x = tank.x;
        y = tank.y;
        direction = Direction.valueOf(tank.direction.toString());

        loadImage();

    }

    @Override
    protected String getImageName() {

        return "img/tank_" + direction.toString().toLowerCase() + ".png";
    }
}
