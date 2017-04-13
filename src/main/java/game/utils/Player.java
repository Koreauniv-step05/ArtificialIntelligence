package game.utils;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public interface Player {
    int[] getNewStone(int[][] board);
    void noticeWin();
    void noticeDefeat();
}
