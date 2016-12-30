package Client;

import javax.swing.ImageIcon;

/**
 * Created by liao on 2016/12/31.
 */
public abstract class MoveEntity extends Entity {

    protected Direction direction;

    protected abstract String getImageName();

    protected void loadImage() {

        ImageIcon ii = new ImageIcon(getImageName());
        image = ii.getImage();
    }
}
