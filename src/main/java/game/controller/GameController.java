package game.controller;

import game.utils.Game;
import game.utils.StoneListener;

import static consts.Consts.*;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class GameController {
    private Game mGame;

    private int[][] board;

    public GameController(Game mGame) {
        this.mGame = mGame;
        this.newGame();
    }

    public static void main(String[] args) {
        new GameController(new Game(HUMAN_PLAYER,HUMAN_PLAYER));
    }

    private void initBoard() {
        this.board = new int[19][19];
        for(int i=0; i<19; i++)
            for(int j=0; j<19; j++) board[i][j] = NONE_STONE;
    }

    private void newGame() {
        // todo wait
        this.initBoard();

        this.waitNewStone();
    }

    private void waitNewStone() {
        this.mGame.getNewStone(this.board, new StoneListener() {
            public void onNewStone(int[] newStonePoint) {
                // System.out.println("GameController: onNewStone");
                if (isValidStone(newStonePoint)) {
                    // Update
                    setBoardPointStatus(newStonePoint, mGame.getTurn());
                    sendWasValidStone(newStonePoint);

                    // Rule chk
                    if (false) { // todo winner?
                        mGame.noticeWinner();

                        try {
                            // System.out.println("end");
                            Thread.sleep(10000);
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }

                        newGame();
                        return;
                    }

                    mGame.rotateTurn();
                }

                waitNewStone();
            }
        });
    }

    private void sendWasValidStone(int[] newStonePoint) {
        this.mGame.sendWasValidStone(newStonePoint);
    }

    private boolean isValidStone(int[] ingamePoint) {
        if (this.getBoardPointStatus(ingamePoint) == NONE_STONE) {
            // System.out.println("GameController: isValidStone true");
            return true;
        }
        else {
            // System.out.println("GameController: isValidStone false");
            return false;
        }
    }

    private int getBoardPointStatus(int[] ingamePoint) {
        return this.board[ingamePoint[0]-1][ingamePoint[1]-1];
    }

    private void setBoardPointStatus(int[] ingamePoint, int status) {
        this.board[ingamePoint[0]-1][ingamePoint[1]-1] = status;
    }
}
