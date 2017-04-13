package game.utils;

import ai.AiPlayer;
import user.controller.UserControllerImpl;

import static consts.Consts.*;
import static consts.Consts.BLACK_STONE;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class Game {
    private Player mPlayerBlack;
    private Player mPlayerWhite;
    private int turn = BLACK_STONE;

    public Game(int player1, int player2) {
        this.mPlayerBlack = generatePlayer(player1);
        this.mPlayerBlack.setBlackOrWhite(BLACK_STONE);
        this.mPlayerWhite = generatePlayer(player2);
        this.mPlayerWhite.setBlackOrWhite(WHITE_STONE);
    }

    private Player generatePlayer(int playerType) {
        if (playerType == AI_PLAYER) {
            // System.out.println("Game: generatePlayer AI");
            return new AiPlayer();
        } else { // if (playerType == HUMAN_PLAYER) {
            // System.out.println("Game: generatePlayer USER");
            return new UserControllerImpl();
        }
    }

    public void getNewStone(int[][] board, StoneListener listener) {
        if (this.turn == BLACK_STONE) {
            // System.out.println("Game: getNewStone BLACK turn");
            this.mPlayerBlack.getNewStone(board, listener);
        } else {
            // System.out.println("Game: getNewStone WHITE turn");
            this.mPlayerWhite.getNewStone(board, listener);
        }
    }

    public int getTurn() {
        return this.turn;
    }

    public void rotateTurn() {
        // System.out.println("Game: rotateTurn");
        if (this.turn == BLACK_STONE) {
            this.turn = WHITE_STONE;
        } else {
            this.turn = BLACK_STONE;
        }
    }

    public void sendWasValidStone(int[] newStonePoint) {
        if (this.turn == BLACK_STONE) {
            // System.out.println("Game: sendWasValidStone BLACK turn");
            this.mPlayerBlack.sendWasValidStone(newStonePoint);
            this.mPlayerWhite.sendEnemyStone(newStonePoint);
        } else {
            // System.out.println("Game: sendWasValidStone WHITE turn");
            this.mPlayerWhite.sendWasValidStone(newStonePoint);
            this.mPlayerBlack.sendEnemyStone(newStonePoint);
        }
    }

    public void noticeWinner() {
        if (this.turn == BLACK_STONE) {
            this.mPlayerBlack.noticeWin();
            this.mPlayerWhite.noticeDefeat();
        } else {
            this.mPlayerWhite.noticeWin();
            this.mPlayerBlack.noticeDefeat();
        }
    }
}
