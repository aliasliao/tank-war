package Client;

import Server.Frame;
import javax.swing.ImageIcon;

/**
 * Created by liao on 2016/12/31.
 */
public class Wall extends Entity {

    WallType type;

    public Wall(Frame.Wall wall) {

        x = wall.x;
        y = wall.y;
        type = WallType.valueOf(wall.type.toString());

        loadImage();
    }

    private void loadImage() {

//        String imName = getClass().getResource("/img/wall_" + type.toString().toLowerCase() + ".png").getFile();
        String imName = "img/wall_" + type.name().toLowerCase() + ".png";
        ImageIcon ii = new ImageIcon(imName);
        image = ii.getImage();
    }
}
