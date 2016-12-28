package Server;

/**
 * Created by liao on 2016/12/28.
 */
public class Test {

    public static void main(String[] args) {
        Direction direction = Direction.DOWN;
        for (int i=0; i<10; i++) {
            System.out.println(direction.getRandomNext());
        }
    }
}
