package ai.algorithm;

import ai.domain.State;
import ai.utils.Debug;
import ai.utils.GomokuTree;
import ai.utils.Tree;

import java.util.ArrayList;

import static consts.Consts.*;

/**
 * Created by jaeyoung on 2017. 4. 16..
 */
public class PossibleNextStone {
    public static ArrayList<State> possibleNextStone(State state, int stoneType) {
        return possibleNextStone(state.getState(), stoneType);
    }

    public static int[] possibleNextStonePoint(int[][] state) {

        for (int i = 0; i < state.length; i++) {
            for(int j=0; j<state[i].length; j++) {
                if(state[i][j] == NONE_STONE) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0,0};
    }

    public static ArrayList<State> possibleNextStone(int[][] state, int stoneType) {
        ArrayList<State> nextStates = new ArrayList<State>();

        for (int i = 0; i < state.length; i++) {
            for(int j=0; j<state[i].length; j++) {
                if(state[i][j] == NONE_STONE) {
                    nextStates.add(makeNextState(state, i,j, stoneType));
                }
            }

        }

        return nextStates;
    }

    public static State makeNextState(int[][] state, int nextStoneX, int nextStoneY, int stoneType) {
        int[][] nextState = state.clone();

        for (int i = 0; i < state.length; i++) {
            nextState[i] = state[i].clone();
        }

        nextState[nextStoneX][nextStoneY] = stoneType;
        // for debug
//        Debug.printStateForDebug(nextState);
        return new State(nextState, nextStoneX, nextStoneY);
    }
}
