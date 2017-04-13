package controller;

import config.Config;
import view.GameBoardCanvas;
import view.GameBoardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static consts.Consts.BLACK_STONE;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class GameController implements GameBoardCanvas.GameBoardCanvasUIListener {
    int[][] board;
    private GameBoardView mGameBoardView;

    public GameController() {
        this.board = new int[19][19];
        initBoard();
        mGameBoardView = new GameBoardView();
        attachUserActionListener();
        // detachUserActionListener();
    }

    public GameBoardView getGameBoardView() {
        return mGameBoardView;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Network FIve Eyes Ver. 1.0");
        Container cp = frame.getContentPane();

        GameController gameController = new GameController();
        cp.add(gameController.getGameBoardView());

        frame.setSize(650, 650);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                System.out.println("Exit");
            }
        });
    }

    private void initBoard() {
        for(int i=0; i<19; i++)
            for(int j=0; j<19; j++) board[i][j] = 0;
    }

    private void drawStoneToCanvas(int[] ingamePoint, boolean isBlack) {
        if (mGameBoardView != null) {
            mGameBoardView.drawStone(ingamePoint, isBlack);
        }
    }

    private void attachUserActionListener() {
        mGameBoardView.getCanvas().setGameBoardCanvasUIListener(this);
    }

    private void detachUserActionListener() {
        mGameBoardView.getCanvas().removeGameBoardCanvasUIListener();
    }

    public void putStoneByPlayer(int[] ingamePoint, boolean isBlack) {

        if (Config.VISUALIZE) {
            drawStoneToCanvas(ingamePoint, isBlack);
        }
    }

    public void onNewStone(int[] ingamePoint) {
        if(isValidStone(ingamePoint)) {
            putStoneByPlayer(ingamePoint, true);
            setBoardPointStatus(ingamePoint,BLACK_STONE);
        }
        else {
            System.out.println("Invalid Stone Point");
        }
    }

    private int getBoardPointStatus(int[] ingamePoint) {
        return board[ingamePoint[0]-1][ingamePoint[1]-1];
    }

    private void setBoardPointStatus(int[] ingamePoint, int status) {
        board[ingamePoint[0]-1][ingamePoint[1]-1] = status;
    }

    private boolean isValidStone(int[] ingamePoint) {
        if (getBoardPointStatus(ingamePoint) == 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
