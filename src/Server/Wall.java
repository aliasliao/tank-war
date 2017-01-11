package Server;

import javax.swing.ImageIcon;

import java.awt.Image;

/**
 * Created by liao on 2016/12/27.
 */
public class Wall extends Entity {

    private WallType type;

    public Wall(int x, int y, WallType type) {

        this.x = x;
        this.y = y;
        this.type = type;

        loadImage();
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon(getImageName());
        Image im = ii.getImage();
        width = im.getWidth(null);
        height = im.getHeight(null);
    }

    private String getImageName() {

//        return getClass().getResource("/img/wall_" + type.toString().toLowerCase() + ".png").getFile();
        return "img/wall_" + type.name().toLowerCase() + ".png";
    }

    public WallType getType() { return type; }
}
