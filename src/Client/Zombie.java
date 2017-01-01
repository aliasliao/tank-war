package Client;

import Server.Frame;

/**
 * Created by liao on 2016/12/31.
 */
public class Zombie extends MoveEntity {

    public Zombie(Frame.MoveObject zombie) {

        x = zombie.x;
        y = zombie.y;
        direction = Direction.valueOf(zombie.direction.toString());

        loadImage();
    }

    @Override
    protected String getImageName() {

        return "img/zombie_" + direction.toString().toLowerCase() + ".png";
    }
}
