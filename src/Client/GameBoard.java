package Client;

import Server.Frame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ObjectOutputStream;

/**
 * Created by liao on 2016/12/31.
 */
public class GameBoard extends JPanel {

    private Tank tank1, tank2;
    private Zombie[] zombies;
    private Bullet[] bullets;
    private Wall[] walls;
    private boolean gameStart;
    private ObjectOutputStream output;
    private final int BOARD_W = 500;
    private final int BOARD_H = 500;

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (gameStart) {
            g.drawImage(tank1.getImage(), tank1.getX(), tank1.getY(), this);
            g.drawImage(tank2.getImage(), tank2.getX(), tank2.getY(), this);
            for (Zombie z : zombies)
                g.drawImage(z.getImage(), z.getX(), z.getY(), this);
            for (Bullet b : bullets)
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            for (Wall w : walls)
                g.drawImage(w.getImage(), w.getX(), w.getY(), this);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    public GameBoard() {

        addKeyListener(new KeyHandler());
        setFocusable(true);
        setBackground(Color.black);
        setPreferredSize(new Dimension(BOARD_W, BOARD_H));

        gameStart = false;

        Connection connection = new Connection(this);
        output = connection.getOutput();
    }

    private class KeyHandler extends KeyAdapter {

        KeySignal ks = null;

        @Override
        public void keyPressed(KeyEvent e) {

            try {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        ks = new KeySignal(KeySignal.KeyType.PRESS, KeySignal.KeyCode.UP);
                        output.writeObject(ks);
                        break;
                    case KeyEvent.VK_DOWN:
                        ks = new KeySignal(KeySignal.KeyType.PRESS, KeySignal.KeyCode.DOWN);
                        output.writeObject(ks);
                        break;
                    case KeyEvent.VK_LEFT:
                        ks = new KeySignal(KeySignal.KeyType.PRESS, KeySignal.KeyCode.LEFT);
                        output.writeObject(ks);
                        break;
                    case KeyEvent.VK_RIGHT:
                        ks = new KeySignal(KeySignal.KeyType.PRESS, KeySignal.KeyCode.RIGHT);
                        output.writeObject(ks);
                        break;
                    case KeyEvent.VK_SPACE:
                        ks = new KeySignal(KeySignal.KeyType.PRESS, KeySignal.KeyCode.SPACE);
                        output.writeObject(ks);
                        break;
                    case KeyEvent.VK_ENTER:
                        ks = new KeySignal(KeySignal.KeyType.PRESS, KeySignal.KeyCode.ENTER);
                        output.writeObject(ks);
                        break;
                    default:
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            try {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        ks = new KeySignal(KeySignal.KeyType.RELEASE, KeySignal.KeyCode.UP);
                        output.writeObject(ks);
                        break;
                    case KeyEvent.VK_DOWN:
                        ks = new KeySignal(KeySignal.KeyType.RELEASE, KeySignal.KeyCode.DOWN);
                        output.writeObject(ks);
                        break;
                    case KeyEvent.VK_LEFT:
                        ks = new KeySignal(KeySignal.KeyType.RELEASE, KeySignal.KeyCode.LEFT);
                        output.writeObject(ks);
                        break;
                    case KeyEvent.VK_RIGHT:
                        ks = new KeySignal(KeySignal.KeyType.RELEASE, KeySignal.KeyCode.RIGHT);
                        output.writeObject(ks);
                        break;
                    default:
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void handleFrame(Frame frame) {

        if (!gameStart)
            gameStart = true;

        tank1 = new Tank(frame.tank1);
        tank2 = new Tank(frame.tank2);
        zombies = new Zombie[frame.zombies.length];
        bullets = new Bullet[frame.bullets.length];
        walls = new  Wall[frame.walls.length];

        for (int i=0; i<zombies.length; i++)
            zombies[i] = new Zombie(frame.zombies[i]);
        for (int i=0; i<bullets.length; i++)
            bullets[i] = new Bullet(frame.bullets[i]);
        for (int i=0; i<walls.length; i++)
            walls[i] = new Wall(frame.walls[i]);

        repaint();
    }
}
