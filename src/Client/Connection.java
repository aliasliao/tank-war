package Client;

import Server.GameMap;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by liao on 2016/12/31.
 */
public class Connection implements Runnable {

    private final String ip = "localhost";
    private final int port = 4396;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private GameBoard board;

    public Connection(GameBoard board) {

        this.board = board;

        try {
            Socket socket = new Socket(ip, port);
            System.out.println("Connected!");

            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());

            Thread recvThread = new Thread(this);
            recvThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        Frame frame;

        while (true) {
            try {
                frame = (Frame) input.readObject();
                board.handleFrame(frame);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ObjectOutputStream getOutput() { return output; }
}
