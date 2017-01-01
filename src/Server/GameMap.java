package Server;

import Client.KeySignal;

import java.awt.Rectangle;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
    private Lock bulletsLock;

    public GameMap() {

        tank1 = new Tank(20, 460, Direction.UP, this);
        tank2 = new Tank(460, 20, Direction.DOWN, this);
        walls = new ArrayList<>();
        zombies = new ArrayList<>();
        bullets = new ArrayList<>();
        inGame = true;
        bulletsLock = new ReentrantLock();

        for (int i=0; i<5; i++)
            walls.add(new Wall(240, 20*i, WallType.WEAK));
        for (int i=10; i<15; i++)
            walls.add(new Wall(240, 20*i, WallType.STRONG));
        for (int i=20; i<25; i++)
            walls.add(new Wall(240, 20*i, WallType.WEAK));
        walls.add(new Wall(80, 480, WallType.STRONG));
        walls.add(new Wall(300, 0, WallType.STRONG));
        walls.add(new Wall(420, 480, WallType.STRONG));
        walls.add(new Wall(200, 0, WallType.STRONG));
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

        bulletsLock.lock();

        for (Iterator<Bullet> bi = bullets.iterator(); bi.hasNext();) {
            Bullet b = bi.next();
            Rectangle bBound = b.getBounds();

            if (bBound.intersects(tank1.getBounds()) || bBound.intersects(tank2.getBounds()))
                inGame = false;

            for (Iterator<Zombie> zi = zombies.iterator(); zi.hasNext();) {
                Zombie z = zi.next();
                if (bBound.intersects(z.getBounds())) {
                    try { bi.remove(); } catch (java.lang.IllegalStateException e) {}
                    zi.remove();
                }
            }

            for (Iterator<Wall> wi = walls.iterator(); wi.hasNext();) {
                Wall w = wi.next();
                if (bBound.intersects(w.getBounds())) {
                    try { bi.remove(); } catch (java.lang.IllegalStateException e) {}
                    if (w.getType() == WallType.WEAK)
                        wi.remove();
                }
            }

            if (b.getX()<0 || b.getX()+b.getWidth()>MAP_X
                    || b.getY()<0 || b.getY()+b.getHeight()>MAP_Y)
                try { bi.remove(); } catch (java.lang.IllegalStateException e) {}
        }

        bulletsLock.unlock();
    }

    public void addBullet(Bullet bullet) {

        bulletsLock.lock();

        bullets.add(bullet);

        bulletsLock.unlock();
    }

    public boolean isBlocked(MoveEntity entity) {

        HashSet<Rectangle> obstacles = new HashSet<>();
        Rectangle fbound = entity.getFutureBound();
        Rectangle nbound = entity.getBounds();

        obstacles.add(tank1.getBounds());
        obstacles.add(tank2.getBounds());
        for (Entity e : zombies)
            obstacles.add(e.getBounds());
        for (Entity e : walls)
            obstacles.add(e.getBounds());

        obstacles.remove(nbound);

        for (Rectangle r : obstacles) {
            if (fbound.intersects(r))
                return true;
        }
        if (fbound.getX()<0 || fbound.getX() + fbound.getWidth() > MAP_X || fbound.getY() < 0 || fbound.getY() + fbound.getHeight() > MAP_Y)
            return true;
        else return false;

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

        frame.tank1.init(tank1.getX(), tank1.getY(), Frame.Direction.valueOf(tank1.getDirection().name()));
        frame.tank2.init(tank2.getX(), tank2.getY(), Frame.Direction.valueOf(tank2.getDirection().name()));
        for (int i=0; i<zombies.size(); i++) {
            Zombie z = zombies.get(i);
            frame.zombies[i].init(z.getX(), z.getY(), Frame.Direction.valueOf(z.getDirection().name()));
        }
        for (int i=0; i<bullets.size(); i++) {
            Bullet b = bullets.get(i);
            frame.bullets[i].init(b.getX(), b.getY(), Frame.Direction.valueOf(b.getDirection().name()));
        }
        for (int i=0; i<walls.size(); i++) {
            Wall w = walls.get(i);
            frame.walls[i].init(w.getX(), w.getY(), Frame.WallType.valueOf(w.getType().name()));
        }

        try {
            output1.writeObject(frame);
            output2.writeObject(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
