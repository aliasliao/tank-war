package Server;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * Created by liao on 2016/12/27.
 */
public class Wall {

    private int x;
    private int y;
    private int width;
    private int height;
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

        return "img/wall_" + type.toString().toLowerCase() + ".png";
    }

    public Rectangle getBounds() { return new Rectangle(x, y, width, height); }
}
