package Client;

import javax.swing.JFrame;

import java.awt.EventQueue;

/**
 * Created by liao on 2016/12/31.
 */
public class ClientMain extends JFrame {

    public ClientMain() {

        add(new GameBoard());
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
