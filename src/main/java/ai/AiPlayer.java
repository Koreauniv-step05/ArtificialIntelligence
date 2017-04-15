package ai;

import game.utils.Player;
import game.utils.StoneListener;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class AiPlayer implements Player {
    private int[][] state;
    private StoneListener mListener;

    public void getNewStone(int[][] board, StoneListener listener) {
        this.state = board;
        this.mListener = listener;

        this.searchSolution();
    }

    private void searchSolution() {
        int[] newStonePoint;

        newStonePoint = AiAlgorithm.searchSolution(state);

        this.mListener.onNewStone(newStonePoint);
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

    }
}
