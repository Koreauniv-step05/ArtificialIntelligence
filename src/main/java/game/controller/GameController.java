package game.controller;

import game.utils.Game;

import static consts.Consts.*;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class GameController {
    private Game mGame;
    private int turn = BLACK_STONE;

    private int[][] board;

    public GameController(Game mGame) {
        this.mGame = mGame;

        initBoard();
        runGame();
    }

    public static void main(String[] args) {
        new GameController(new Game(HUMAN_PLAYER,HUMAN_PLAYER));
    }

    private void initBoard() {
        this.board = new int[19][19];
        for(int i=0; i<19; i++)
            for(int j=0; j<19; j++) board[i][j] = NONE_STONE;
    }

    private void runGame() {
        while(true) {
            // Get Next Stone
            while(true) {
                int[] newStonePoint = mGame.getNewStone(board, turn);
                if (true) { // todo newStonePoint is valid?
                    break;
                }
            }
            // Rule chk
            if (false) { // todo winner?
                mGame.noticeWinner(turn);
            }

            // Turn rotate
            if (turn == BLACK_STONE) {
                turn = WHITE_STONE;
            } else {
                turn = BLACK_STONE;
            }
        }
    }
}
