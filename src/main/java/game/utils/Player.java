package game.utils;

import ai.domain.State;
import game.controller.GameController;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public interface Player extends GameController.GameControllerListener {
    void noticeWin();
    void noticeDefeat();
    void sendWasValidStone(int[] newStonePoint);
    void sendEnemyStone(int[] newStonePoint);
    void setBlackOrWhite(int blackOrWhite);
    void setStoneListener(StoneListener listener);
    void setBoard(int[][] board);
}
