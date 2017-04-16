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
    private static boolean terminateFlag = false;

    public static void onTimeLimited() {
        terminateFlag = true;
    }

    public static void iterativeDeepeningSearch(State state) {
        GomokuTree root = new GomokuTree(state);

        int targetDepth = 4;
//        int targetDepth = (int)INF;
        int presentDepth = 2;
        while(true) {
            root.extendDeeper(presentDepth);
            int[] nextPoint = alphabetaSearchFindNextState(root,presentDepth);
            setCurrentOptimal(nextPoint);
            presentDepth++;

            if(terminateFlag || presentDepth == targetDepth){
                break;
            }
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
        // Debug.printStateForDebug(state.getState());
        return state.getAddedStonePoint();
    }
}
