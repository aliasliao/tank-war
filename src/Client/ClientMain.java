package Client;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

/**
 * Created by liao on 2016/12/31.
 */
public class ClientMain extends JFrame {

    private GameBoard gameBoard;
    private MsgBoard msgBoard;

    public ClientMain() {

        msgBoard = new MsgBoard();
        gameBoard = new GameBoard(msgBoard);

        add(msgBoard, BorderLayout.SOUTH);
        add(gameBoard, BorderLayout.NORTH);
        setResizable(false);
        pack();
        setTitle("Tank War");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            ClientMain game = new ClientMain();
            game.setVisible(true);
        });
    }
}
