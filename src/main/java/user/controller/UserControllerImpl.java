package user.controller;

import config.Config;
import game.utils.Player;
import game.utils.StoneListener;
import view.GameBoardCanvas;
import view.GameBoardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static consts.Consts.BLACK_STONE;
import static consts.Consts.NONE_STONE;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class UserControllerImpl implements UserController, GameBoardCanvas.GameBoardCanvasUIListener {
    private int blackOrWhite;
    private GameBoardView mGameBoardView;
    private JFrame mFrame;
    private Container mContainer;
    private StoneListener mStoneListener;

    private int[][] mBoard;

    public UserControllerImpl() {
        this.mGameBoardView = new GameBoardView();
        this.attachUserActionListener();
        // this.detachUserActionListener();

        this.mFrame = new JFrame("Gomoku-java");
        this.mContainer = this.mFrame.getContentPane();
        this.mContainer.add(this.mGameBoardView);

        this.mFrame.setSize(650, 650);
        this.mFrame.setVisible(true);

        this.mFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                // System.out.println("Exit");
            }
        });
    }

    public void setBoard(int[][] board) {
        this.mBoard = board;
    }

    public void setBlackOrWhite(int blackOrWhite) {
        this.blackOrWhite = blackOrWhite;
    }

    //    public GameBoardView getGameBoardView() {
//        return mGameBoardView;
//    }

//    private void drawStoneToCanvas(int[] ingamePoint, boolean isBlack) {
//        if (this.mGameBoardView != null) {
//            this.mGameBoardView.drawStone(ingamePoint, isBlack);
//        }
//    }

    private void attachUserActionListener() {
        // System.out.println("UserControllerImpl: attachUserActionListener");
        this.mGameBoardView.getCanvas().setGameBoardCanvasUIListener(this);
    }

    // from user click
    public void onNewStone(int[] ingamePoint) {
        // System.out.println("UserControllerImpl: onNewStone");
        if(mStoneListener != null) {
            // System.out.println("UserControllerImpl: onNewStone mStoneListener is not null");
            this.mStoneListener.onNewStone(ingamePoint,blackOrWhite);
            // System.out.println("UserControllerImpl: onNewStone removeStoneListener");
        }
    }

    public void setStoneListener(StoneListener listener) {
        // System.out.println("UserControllerImpl: setStoneListener");
        this.mStoneListener = listener;
    }

    public void getNewStone(int[][] board, StoneListener listener) {
//        this.showBoard(board);
        System.out.println("UserControllerImpl: getNewStone");
        this.setStoneListener(listener);
    }

//    private void showBoard(int[][] board) {
//        // System.out.println("UserControllerImpl: showBoard");
//        this.mGameBoardView.showBoard(board);
//    }

    public void sendEnemyStone(int[] newStonePoint) {
        this.mGameBoardView.drawStone(newStonePoint, !(blackOrWhite==BLACK_STONE));
    }

    public void sendWasValidStone(int[] newStonePoint) {
        this.mGameBoardView.drawStone(newStonePoint, blackOrWhite==BLACK_STONE);
    }

    public void noticeWin() {
        showMsg("User Win");
    }

    public void noticeDefeat() {
        showMsg("User Defeat");
    }

    // from gamecontroller
    public void onYourTurn() {
        showMsg("User onYourTurn");
    }

    public void onNewStone(int[] newStonePoint, int stoneType) {
//        showMsg("View onNewStone drawStone"+(stoneType==BLACK_STONE));
        this.mGameBoardView.drawStone(newStonePoint, stoneType==BLACK_STONE);
    }

    public void showMsg(String msg) {
        System.out.println(msg);
    }
}
