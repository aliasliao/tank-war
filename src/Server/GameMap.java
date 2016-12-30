package Server;

import java.awt.Rectangle;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by liao on 2016/12/27.
 */
public class GameMap implements Runnable {

    private final int MAP_X = 500;
    private final int MAP_Y = 500;
    private final int INTERVAL = 25;

    private Tank tank1, tank2;
    private ArrayList<Zombie> zombies;
    private ArrayList<Bullet> bullets;
    private ArrayList<Wall> walls;
    private Connection connection;
    private ObjectOutputStream output1, output2;
    private Thread animator;
    private boolean inGame;

    public GameMap() {

        tank1 = new Tank(0, 480, Direction.UP, this);
        tank2 = new Tank(480, 0, Direction.DOWN, this);
        walls = new ArrayList<>();
        zombies = new ArrayList<>();
        bullets = new ArrayList<>();
        inGame = true;

        for (int i=0; i<5; i++)
            walls.add(new Wall(240, 20*i, WallType.WEAK));
        for (int i=10; i<15; i++)
            walls.add(new Wall(240, 20*i, WallType.STRONG));
        for (int i=20; i<25; i++)
            walls.add(new Wall(240, 20*i, WallType.WEAK));
        zombies.add(new Zombie(220, 0, Direction.DOWN, this));
        zombies.add(new Zombie(260, 480, Direction.UP, this));
        zombies.add(new Zombie(120, 120, Direction.DOWN, this));
        zombies.add(new Zombie(400, 400, Direction.DOWN, this));

        connection = new Connection(this);
        output1 = connection.getOutput1();
        output2 = connection.getOutput2();

        animator = new Thread(this);
        animator.start();
    }

    private void moveObject() {

        tank1.move();
        tank2.move();
        for (Zombie z : zombies)
            z.move();
        for (Bullet b : bullets)
            b.move();
    }

    private void handleCollisons() {

        for (Bullet b : bullets) {
            Rectangle bBound = b.getBounds();

            if (bBound.intersects(tank1.getBounds()) || bBound.intersects(tank2.getBounds()))
                inGame = false;

            for (Zombie z : zombies) {
                if (bBound.intersects(z.getBounds())) {
                    bullets.remove(b);
                    zombies.remove(z);
                }
            }

            for (Wall w : walls) {
                if (bBound.intersects(w.getBounds())) {
                    bullets.remove(b);
                    if (w.getType() == WallType.WEAK)
                        walls.remove(w);
                }
            }

            if (b.getX()<0 || b.getX()+b.getWidth()>MAP_X
                    || b.getY()<0 || b.getY()+b.getHeight()>MAP_Y)
                bullets.remove(b);
        }
    }

    public void addBullet(Bullet bullet) {

        bullets.add(bullet);
    }

    public boolean isBlocked(MoveEntity entity) {

        HashSet<Rectangle> obstacles = new HashSet<>();
        Rectangle bound = entity.getFutureBound();

        obstacles.add(tank1.getBounds());
        obstacles.add(tank2.getBounds());
        for (Entity e : zombies)
            obstacles.add(e.getBounds());
        for (Entity e : walls)
            obstacles.add(e.getBounds());

        for (Rectangle r : obstacles) {
            if (bound.intersects(r) && !bound.equals(r))
                return true;
        }
        return entity.getX() < 0 || entity.getX() + entity.getWidth() > MAP_X
                || entity.getY() < 0 || entity.getY() + entity.getHeight() > MAP_Y;

    }

    public void handleKey1(KeySignal key) {

        KeyCode keyCode = Enum.valueOf(KeyCode.class, key.code.name());

        switch (key.type) {
            case PRESS:
                tank1.keyPressed(keyCode);
                break;
            case RELEASE:
                tank1.keyReleased(keyCode);
                break;
            default:
                break;
        }
    }

    public void handleKey2(KeySignal key) {

        KeyCode keyCode = Enum.valueOf(KeyCode.class, key.code.name());

        switch (key.type) {
            case PRESS:
                tank2.keyPressed(keyCode);
                break;
            case RELEASE:
                tank2.keyReleased(keyCode);
                break;
            default:
                break;
        }
    }

    private void inGame() {

        if (!inGame)
            animator.interrupt();
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        while (true) {
            inGame();
            moveObject();
            handleCollisons();
            sendFrame();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = INTERVAL - timeDiff;
            if(sleep < 0)
                sleep = 2;

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            beforeTime = System.currentTimeMillis();
        }
    }

    private void sendFrame() {

        Frame frame = new Frame(zombies.size(), bullets.size(), walls.size());

        frame.tank1.init(tank1.getX(), tank1.getY(), Frame.Direction.valueOf(tank1.getDirection().toString()));
        frame.tank2.init(tank2.getX(), tank2.getY(), Frame.Direction.valueOf(tank2.getDirection().toString()));
        for (int i=0; i<zombies.size(); i++) {
            Zombie z = zombies.get(i);
            frame.zombies[i].init(z.getX(), z.getY(), Frame.Direction.valueOf(z.getDirection().toString()));
        }
        for (int i=0; i<bullets.size(); i++) {
            Bullet b = bullets.get(i);
            frame.bullets[i].init(b.getX(), b.getY(), Frame.Direction.valueOf(b.getDirection().toString()));
        }
        for (int i=0; i<walls.size(); i++) {
            Wall w = walls.get(i);
            frame.walls[i].init(w.getX(), w.getY(), Frame.WallType.valueOf(w.getType().toString()));
        }

        try {
            output1.writeObject(frame);
            output2.writeObject(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
