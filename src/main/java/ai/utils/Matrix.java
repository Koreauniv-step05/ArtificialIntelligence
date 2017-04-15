package ai.utils;

/**
 * Created by jaeyoung on 2017. 4. 15..
 */
public class Matrix {
    public static int[][] transpose(int[][] ori) {
        // transpose

        // clone
        int[][] dup= ori.clone();
        for (int i = 0; i < ori.length; i++) {
            dup[i] = ori[i].clone();
        }

        for (int i = 0; i < ori.length; i++) {
            for (int j = 0; j < ori[i].length; j++) {
                dup[j][i] = ori[i][j];
//                System.out.println(i+""+j+""+ori[i][j]);
            }
        }

        return dup;
    }
}
