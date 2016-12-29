package Server;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liao on 2016/12/28.
 */
public class Connection {

    final private int port =  4396;
    final private int backlog = 2;
    private ObjectInputStream input1, input2;
    private ObjectOutputStream output1, output2;
    private GameMap map;

    public Connection() {

        try {
            ServerSocket serverSocket = new ServerSocket(port, backlog);

            Socket clientSocket1 = serverSocket.accept();
            System.out.println("Player 1 is connected!");
            input1 = new ObjectInputStream(clientSocket1.getInputStream());
            output1 = new ObjectOutputStream(clientSocket1.getOutputStream());
            Thread client1 = new Thread(new Task1(input1));
            client1.start();

            Socket clientSocket2 = serverSocket.accept();
            System.out.println("Player 2 is connected!");
            input2 = new ObjectInputStream(clientSocket2.getInputStream());
            output2 = new ObjectOutputStream(clientSocket2.getOutputStream());
            Thread client2 = new Thread(new Task2(input2));
            client2.start();

//            client1.join();
//            client2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Task1 implements Runnable {

        private ObjectInputStream input;

        public Task1(ObjectInputStream input) {
            this.input =  input;
        }

        @Override
        public void run() {
            KeySignal keySignal;

            while (true) {
                try {
                    keySignal = (KeySignal) input.readObject();
                    map.handleKey1(keySignal);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class Task2 implements Runnable {

        private ObjectInputStream input;

        public Task2(ObjectInputStream input) {
            this.input =  input;
        }

        @Override
        public void run() {
            KeySignal keySignal;

            while (true) {
                try {
                    keySignal = (KeySignal) input.readObject();
                    map.handleKey2(keySignal);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ObjectOutputStream getOutput1() { return output1; }

    public ObjectOutputStream getOutput2() { return output2; }
}
