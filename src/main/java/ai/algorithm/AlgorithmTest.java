package ai.algorithm;

import ai.AiAlgorithm;
import ai.domain.State;
import ai.utils.TestTree;
import ai.utils.Tree;

import static consts.Consts.BLACK_STONE;
import static consts.Consts.INF;

/**
 * Created by jaeyoung on 2017. 4. 16..
 */
public class AlgorithmTest {

    public static void main(String[] args) {
        // testPossibleNextStone();
        testIterativeDeepeningSearch();
    }

    public static void testIterativeDeepeningSearch() {
        int[][] state = new int[][]{
                {1,0,0,0,0,1,-1},
                {1,0,0,0,0,1,-1},
                {0,0,0,0,0,1,-1},
                {0,0,0,0,0,0,0},
                {0,0,1,0,0,0,-1},
                {0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0}
        };

        IterativeDeepeningSearch.iterativeDeepeningSearch(new State(state));
        int[] nextPoint = IterativeDeepeningSearch.getCurrentOptimalStonePoint();
        System.out.println("NextStone : X "+nextPoint[0]+" Y "+nextPoint[1]);
    }

    public static void testAlphabetaAlgorithm() {
        Tree root = new TestTree(0f);

        Tree depth1node1 = new TestTree(0f);
        Tree depth1node2 = new TestTree(0f);
        Tree depth1node3 = new TestTree(0f);

        Tree depth2node1 = new TestTree(0f);
        depth2node1.addChild(4f);
        depth2node1.addChild(6f);
        Tree depth2node2 = new TestTree(0f);
        depth2node2.addChild(7f);
        depth2node2.addChild(9f);
        Tree depth2node3 = new TestTree(0f);
        depth2node3.addChild(1f);
        depth2node3.addChild(2f);
        Tree depth2node4 = new TestTree(0f);
        depth2node4.addChild(0f);
        depth2node4.addChild(1f);
        Tree depth2node5 = new TestTree(0f);
        depth2node5.addChild(8f);
        depth2node5.addChild(1f);
        Tree depth2node6 = new TestTree(0f);
        depth2node6.addChild(9f);
        depth2node6.addChild(2f);

        depth1node1.addChild(depth2node1);
        depth1node1.addChild(depth2node2);

        depth1node2.addChild(depth2node3);
        depth1node2.addChild(depth2node4);

        depth1node3.addChild(depth2node5);
        depth1node3.addChild(depth2node6);

        root.addChild(depth1node1);
        root.addChild(depth1node2);
        root.addChild(depth1node3);

        Alphabeta.alphabeta(root, 0, -INF, INF, true, 3);
    }

    public static void testPossibleNextStone() {
        int[][] state = new int[][]{
                        {1,0,0,0,0,1,-1,0,0,0},
                        {1,0,0,0,0,1,-1,0,0,0},
                        {0,0,0,0,1,1,-1,0,0,0},
                        {0,0,0,1,0,1,-1,0,0,0},
                        {0,0,1,0,0,1,-1,0,0,0},
                        {0,1,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0}
                };

        PossibleNextStone.possibleNextStone(state, BLACK_STONE);
    }

    public static void testSearchSolution() {
        int[] next = AiAlgorithm.searchSolution(
                new int[][]{
                        {1,0,0,0,0,1,-1,0,0,0},
                        {1,0,0,0,0,1,-1,0,0,0},
                        {0,0,0,0,1,1,-1,0,0,0},
                        {0,0,0,1,0,1,-1,0,0,0},
                        {0,0,1,0,0,1,-1,0,0,0},
                        {0,1,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0}
                });

        // System.out.println("NextStone : X "+next[0]+" Y "+next[1]);

    }
}
