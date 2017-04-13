package game.utils;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public interface Player {
    void getNewStone(int[][] board, StoneListener listener);
    void noticeWin();
    void noticeDefeat();
    void sendWasValidStone(int[] newStonePoint);
    void sendEnemyStone(int[] newStonePoint);
    void setBlackOrWhite(int blackOrWhite);
}
