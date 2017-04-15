package ai;

import ai.utils.Tree;

import javax.swing.*;

/**
 * Created by jaeyoung on 2017. 4. 14..
 */
public class AiAlgorithm {
    private static Tree tree;

    public static int[] searchSolution(int[][] state) {
        tree = new Tree(state);
        float ev = tree.eval();

        System.out.println(ev+"");
        return new int[]{0,0};
    }
}
