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
        this.execute();
    }

    private void execute() {
        addCanvas();
    }

    private void addCanvas() {
        this.m_canvasPanel = new JPanel() {
            public void paint(Graphics g) {
                this.paintComponents(g);
            }
        };

        this.m_canvasPanel.add(this.m_board);
        this.m_canvasPanel.setLayout(null);
        this.m_board.setBounds(5, 5, 590, 593);

        this.add(this.m_canvasPanel);
        this.m_canvasPanel.setBounds(5, 5, 600, 700);
//		  m_canvasPanel.setBorder(new TitledBorder("sdf"));

        this.setLayout(null);
    }

//    public void showBoard(int[][] board) {
//        this.m_board.resetHistoryOfStone();
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                if (board[i][j] == BLACK_STONE) {
//                    this.drawStone(new int[]{i+1,j+1}, true);
//                }
//                else if (board[i][j] == WHITE_STONE) {
//                    this.drawStone(new int[]{i+1,j+1}, false);
//                }
//            }
//        }
//    }

    public void drawStone(int[] stoneLocation, boolean isBlack) {
        if (isBlack) {
            // System.out.println("GameBoardView : drawBlackStone " + "x: "+stoneLocation[0] +" y: "+ stoneLocation[1]);
            this.m_board.addHistory(stoneLocation, BLACK_STONE);
        }
        else {
            // System.out.println("GameBoardView : drawWhiteStone " + "x: "+stoneLocation[0] +" y: "+ stoneLocation[1]);
            this.m_board.addHistory(stoneLocation, WHITE_STONE);
        }
    }

    public GameBoardCanvas getCanvas() {
        return m_board;
    }
}
