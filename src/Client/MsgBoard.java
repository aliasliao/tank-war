package Client;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Created by liao on 2017/1/12.
 */
public class MsgBoard extends JPanel {

    private final int BOARD_W = 500;
    private final int BOARD_H = 25;
    private JLabel msgLabel;

    public MsgBoard() {

        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(BOARD_W, BOARD_H));
        msgLabel = new JLabel("Initializing...");
        add(msgLabel);
    }

    public void updateMsg(String msg) {

        msgLabel.setText(msg);
    }
}
