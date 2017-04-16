package ai;

import ai.utils.GomokuTree;

/**
 * Created by jaeyoung on 2017. 4. 14..
 */
public class AiAlgorithm {
    private static GomokuTree tree;

    public static int[] searchSolution(int[][] state) {
        tree = new GomokuTree(state);
        float ev = tree.eval();

        System.out.println(ev+"");
        return new int[]{0,0};
    }


}
