package ai;

import ai.algorithm.TimeLimiter;
import ai.algorithm.TimeListener;
import ai.algorithm.TimeListenerImpl;
import game.utils.Player;
import game.utils.StoneListener;

import java.util.ArrayList;

import static consts.Consts.BLACK_STONE;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class AiPlayer implements Player {
    private int blackOrWhite = BLACK_STONE;
    private int[][] state;
    private StoneListener mListener;

    private TimeLimiter mTimeLimiter;

//    public void getNewStone(int[][] board, StoneListener listener) {
//        this.state = board;
//        this.mListener = listener;
//
//        mTimeLimiter = TimeLimiter.getInstance();
//        mTimeLimiter.setLimitedSecs(5);
//        mTimeLimiter.addTimeListener(new TimeListenerImpl());
//
//        mTimeLimiter.start();
//
//        this.searchSolution();
//    }


    public void setBoard(int[][] board) {
        this.state = board;
    }

    private void searchSolution() {
        int[] newStonePoint;

        newStonePoint = AiAlgorithm.searchSolution(state);
        mTimeLimiter.stop();
        this.mListener.onNewStone(newStonePoint, blackOrWhite);
    }

    public void noticeWin() {

    }

    public void noticeDefeat() {

    }

    public void sendWasValidStone(int[] newStonePoint) {

    }

    public void sendEnemyStone(int[] newStonePoint) {

    }

    public void setBlackOrWhite(int blackOrWhite) {
        this.blackOrWhite = blackOrWhite;
    }

    public void setStoneListener(StoneListener listener) {
        this.mListener = listener;
    }

    public void onYourTurn() {
        mTimeLimiter = TimeLimiter.getInstance();
        mTimeLimiter.setLimitedSecs(5);
        mTimeLimiter.addTimeListener(new TimeListenerImpl());

        mTimeLimiter.start();

        this.searchSolution();
    }

    public void onNewStone(int[] newStonePoint, int stoneType) {

    }

    public void showMsg(String msg) {

    }
}
