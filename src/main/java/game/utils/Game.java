package game.utils;

import ai.AiPlayer;
import user.UserPlayer;

import static consts.Consts.*;
import static consts.Consts.BLACK_STONE;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class Game {
    private Player mPlayerBlack;
    private Player mPlayerWhite;

    public Game(int player1, int player2) {
        mPlayerBlack = generatePlayer(player1);
        mPlayerWhite = generatePlayer(player2);
    }

    private Player generatePlayer(int playerType) {
        if (playerType == AI_PLAYER) {
            return new AiPlayer();
        } else { // if (playerType == HUMAN_PLAYER) {
            return new UserPlayer();
        }
    }

    public int[] getNewStone(int[][] board, int turn) {
        if (turn == BLACK_STONE) {
            return mPlayerBlack.getNewStone(board);
        } else {
            return mPlayerWhite.getNewStone(board);
        }
    }

    public void noticeWinner(int turn) {
        if (turn == BLACK_STONE) {
            mPlayerBlack.noticeWin();
            mPlayerWhite.noticeDefeat();
        } else {
            mPlayerWhite.noticeWin();
            mPlayerBlack.noticeDefeat();
        }
    }
}
