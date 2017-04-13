package view;

import javax.swing.*;
import java.awt.*;

import static consts.Consts.BLACK_STONE;
import static consts.Consts.WHITE_STONE;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class GameBoardView extends JPanel {
    private JPanel m_canvasPanel;
    private GameBoardCanvas m_board = new GameBoardCanvas();

    public GameBoardView() {
        execute();

    }

    private void execute() {
        addCanvas();
    }

    private void addCanvas() {
        m_canvasPanel = new JPanel() {
            public void paint(Graphics g) {
                this.paintComponents(g);
            }
        };

        m_canvasPanel.add(m_board);
        m_canvasPanel.setLayout(null);
        m_board.setBounds(5, 5, 590, 593);

        add(m_canvasPanel);
        m_canvasPanel.setBounds(5, 5, 600, 700);
//		m_canvasPanel.setBorder(new TitledBorder("sdf"));

        setLayout(null);
    }

    public void drawStone(int[] stoneLocation, boolean isBlack) {
        if (isBlack)
            m_board.addHistory(stoneLocation, BLACK_STONE);
        else m_board.addHistory(stoneLocation, WHITE_STONE);
    }

    public GameBoardCanvas getCanvas() {
        return m_board;
    }
}
