package ai.utils;

import java.util.ArrayList;

import static consts.Consts.BLACK_STONE;
import static consts.Consts.NONE_STONE;
import static consts.Consts.WALL_STONE;

/**
 * Created by jaeyoung on 2017. 4. 16..
 */
public class Debug {
    public static void printStateForDebug(int[][] state) {
        ArrayList<String> strArr = new ArrayList<String>();
        String str = "";

        for (int i = 0; i < state.length; i++) {
            str = "";
            for (int j = 0; j < state[i].length; j++) {
                str = str + state[i][j];

            }
            System.out.println(str);
            strArr.add(str);
        }
        System.out.println("");
    }
}
