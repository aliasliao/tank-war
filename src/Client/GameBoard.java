package Client;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;

/**
 * Created by liao on 2016/12/31.
 */
public class GameBoard extends JPanel {

    private Tank tank1, tank2;
    private Zombie[] zombies;
    private Bullet[] bullets;
    private Wall[] walls;

    @Override
    public void paintComponent(Graphics g) {

    }

    public GameBoard() {

    }

    private class KeyHandler extends KeyAdapter {

    }

    public void handleFrame(Frame frame) {

        tank1 = new Tank(frame.tank1);
        tank2 = new Tank(frame.tank2);
        zombies = new Zombie[frame.zombies.length];
        bullets = new Bullet[frame.bullets.length];
        walls = new  Wall[frame.bullets.length];

        for (int i=0; i<zombies.length; i++)
            zombies[i] = new Zombie(frame.zombies[i]);
        for (int i=0; i<bullets.length; i++)
            bullets[i] = new Bullet(frame.bullets[i]);
        for (int i=0; i<walls.length; i++)
            walls[i] = new Wall(frame.walls[i]);

        repaint();
    }
}
