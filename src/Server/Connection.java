package Server;

import Client.KeySignal;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by liao on 2016/12/28.
 */
public class Connection {

    final private int port =  4396;
    final private int backlog = 2;
    private Socket clientSocket1;
    private Socket clientSocket2;
    private ObjectInputStream input1, input2;
    private ObjectOutputStream output1, output2;
    private GameMap map;

    public Connection(GameMap map) {

        this.map = map;

        try {
            ServerSocket serverSocket = new ServerSocket(port, backlog);

            System.out.println("Waiting for player 1...");
            clientSocket1 = serverSocket.accept();
            System.out.println("Player 1 is connected!");
            output1 = new ObjectOutputStream(clientSocket1.getOutputStream()); // order!!!
            input1 = new ObjectInputStream(clientSocket1.getInputStream());
            Thread client1 = new Thread(new Task1(input1));
            client1.start();

            System.out.println("Waiting for player 2...");
            clientSocket2 = serverSocket.accept();
            System.out.println("Player 2 is connected!");
            output2 = new ObjectOutputStream(clientSocket2.getOutputStream());
            input2 = new ObjectInputStream(clientSocket2.getInputStream());
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

        public Task1(ObjectInputStream input1) { this.input =  input1; }

        @Override
        public void run() {
            KeySignal keySignal;

            while (true) {
                try {
                    keySignal = (KeySignal) input.readObject();
                    map.handleKey1(keySignal);
                } catch (SocketException e) {
                    System.out.println("get signal: Player 1 has disconnected!");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class Task2 implements Runnable {

        private ObjectInputStream input;

        public Task2(ObjectInputStream input2) { this.input =  input2; }

        @Override
        public void run() {
            KeySignal keySignal;

            while (true) {
                try {
                    keySignal = (KeySignal) input.readObject();
                    map.handleKey2(keySignal);
                } catch (SocketException e) {
                    System.out.println("get signal: Player 2 has disconnected!");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ObjectOutputStream getOutput1() { return output1; }

    public ObjectOutputStream getOutput2() { return output2; }

    public Socket getClientSocket1() { return clientSocket1; }

    public Socket getClientSocket2() { return clientSocket2; }
}
