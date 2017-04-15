package ai;

/**
 * Created by jaeyoung on 2017. 4. 14..
 */
public class AiAlgorithm {
    public static int[] searchSolution(int[][] state) {
        float eval = Evaluation.getInstance(state).evaluate();

        System.out.println(eval);
        return new int[]{0,0};
    }
}
