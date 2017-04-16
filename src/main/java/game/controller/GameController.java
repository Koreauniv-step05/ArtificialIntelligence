package game.controller;

import ai.AiPlayer;
import ai.domain.State;
import ai.utils.Debug;
import game.utils.Player;
import game.utils.StoneListener;
import user.controller.UserControllerImpl;

import java.util.ArrayList;
import java.util.List;

import static consts.Consts.*;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class GameController implements StoneListener {
    private Player mPlayerBlack;
    private Player mPlayerWhite;
    private int turn = BLACK_STONE;
    private GameControllerListener blackPlayerListener;
    private GameControllerListener whitePlayerListener;

    private State state;

    public interface GameControllerListener {
        void onYourTurn();
        void onNewStone(int newStonePoint[], int stoneType);
        void showMsg(String msg);
    }

    void sendListenersNewStone(int[] newStonePoint, int stoneType) {
        this.blackPlayerListener.onNewStone(newStonePoint, stoneType);
        this.whitePlayerListener.onNewStone(newStonePoint, stoneType);
    }

    public GameController(int player1, int player2) {
        this.mPlayerBlack = generatePlayer(player1);
        this.mPlayerBlack.setBlackOrWhite(BLACK_STONE);
        this.mPlayerBlack.setStoneListener(this);
        this.blackPlayerListener = mPlayerBlack;
        this.mPlayerWhite = generatePlayer(player2);
        this.mPlayerWhite.setBlackOrWhite(WHITE_STONE);
        this.mPlayerWhite.setStoneListener(this);
        this.whitePlayerListener = mPlayerWhite;

        this.initBoard();
        this.mPlayerBlack.setBoard(state.getState());
        this.mPlayerWhite.setBoard(state.getState());
        this.blackPlayerListener.onYourTurn();
    }

    private Player generatePlayer(int playerType) {
        if (playerType == AI_PLAYER) {
            return new AiPlayer();
        } else {
            return new UserControllerImpl();
        }
    }

    private void initBoard() {
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++) board[i][j] = NONE_STONE;
        this.state = new State(board);
    }

    private boolean isProcessing=false;
    public void rotateTurn() {
            if (this.turn == BLACK_STONE) {
                this.turn = WHITE_STONE;
                isProcessing = false;
                this.whitePlayerListener.onYourTurn();
            } else {
                this.turn = BLACK_STONE;
                isProcessing = false;
                this.blackPlayerListener.onYourTurn();
            }
        }


    private int getBoardPointStatus(int[] ingamePoint) {
        return this.state.getState()[ingamePoint[0]-1][ingamePoint[1]-1];
    }

    private void setBoardPointStatus(int[] ingamePoint, int stoneType) {
        this.state.addNewStoneByIngamePoint(ingamePoint, stoneType);
    }

    private boolean isValidStone(int[] ingamePoint) {
        if (ingamePoint[X] > BOARD_SIZE || ingamePoint[X] < 1 ||
                ingamePoint[Y] > BOARD_SIZE || ingamePoint[Y] < 1) {
            // System.out.println("GameController: invalid position");
            return false;
        }
        if (this.getBoardPointStatus(ingamePoint) == NONE_STONE) {
            // System.out.println("GameController: isValidStone true");
            return true;
        }
        else {
            // System.out.println("GameController: isValidStone false");
            return false;
        }
    }

    private void showMsg(String msg) {
        if(turn == BLACK_STONE) {
            blackPlayerListener.showMsg(msg);
        } else {
            whitePlayerListener.showMsg(msg);
        }
    }

    public void onNewStone(int[] newStonePoint, int blackOrWhite) {
        // System.out.println("GameController onNewStone blackOrWhite:"+ blackOrWhite);
        if(turn != blackOrWhite) {
            showMsg("is not your turn");
            return;
        }

        if(!isValidStone(newStonePoint)){ // invalid
//            Debug.printStateForDebug(state.getState());
            showMsg("invalidStonePoint");
            return;
        }

        if(isProcessing) {
            return;
        } else {
            isProcessing = true;

            setBoardPointStatus(newStonePoint, this.turn);
            sendListenersNewStone(newStonePoint, this.turn);
        }

        if(isGameEnd()) {
            noticeWinner(blackOrWhite);
            return;
        } else {
            rotateTurn();
        }
    }

    public boolean isGameEnd() {
        float eval = state.eval();

        if (state.eval() > GAMEEND || state.eval() < -GAMEEND) {
//            System.out.println("Evaluation:"+eval);
//            Debug.printStateForDebug(state.getState());
            return true;
        } else {
            return false;
        }
    }

    public void noticeWinner(int blackOrWhite) {
        if (blackOrWhite == BLACK_STONE) {
            System.out.println("Game: noticeWinner BLACK WIN");
            this.mPlayerBlack.noticeWin();
            this.mPlayerWhite.noticeDefeat();
        } else {
            System.out.println("Game: noticeWinner WHITE WIN");
            this.mPlayerWhite.noticeWin();
            this.mPlayerBlack.noticeDefeat();
        }
    }
}
