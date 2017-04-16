package ai;

import ai.algorithm.IterativeDeepeningSearch;
import ai.algorithm.TimeLimiter;
import ai.domain.State;
import ai.utils.GomokuTree;

/**
 * Created by jaeyoung on 2017. 4. 14..
 */
public class AiAlgorithm {
    private static GomokuTree tree;

    public static int[] searchSolution(int[][] state) {
        IterativeDeepeningSearch.iterativeDeepeningSearch(new State(state));
        int[] nextPoint = IterativeDeepeningSearch.getCurrentOptimalStonePoint();

        System.out.println("search complete");
        return nextPoint;
    }


}
