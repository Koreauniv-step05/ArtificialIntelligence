package ai.algorithm;

import ai.domain.State;
import ai.utils.Debug;
import ai.utils.GomokuTree;
import ai.utils.Tree;

import java.util.ArrayList;

import static consts.Consts.INF;

/**
 * Created by jaeyoung on 2017. 4. 16..
 */
public class IterativeDeepeningSearch {
    private static int[] mCurrentOptimal;

    public static void iterativeDeepeningSearch(State state) {
        GomokuTree root = new GomokuTree(state);

        int targetDepth = 3;
        for (int i = 3; i <= targetDepth; i++) {
            root.extendDeeper(targetDepth);
            int[] nextPoint = alphabetaSearchFindNextState(root,i);
            setCurrentOptimal(nextPoint);
        }
    }
    private static void setCurrentOptimal(int[] nextStonePoint) {
        System.out.println("setCurrentOptimal : "+"X "+(nextStonePoint[0]+1)+" Y "+(nextStonePoint[1]+1));
        mCurrentOptimal = nextStonePoint;
    }

    public static int[] getCurrentOptimalStonePoint() {
        return new int[]{mCurrentOptimal[0]+1,mCurrentOptimal[1]+1};
    }

    private static int[] alphabetaSearchFindNextState(Tree root, int targetDepth) {
        State state = Alphabeta.alphabetaSearchForFindBestState(root,0,-INF, INF, true, targetDepth);
        Debug.printStateForDebug(state.getState());
        return state.getAddedStonePoint();
    }
}
