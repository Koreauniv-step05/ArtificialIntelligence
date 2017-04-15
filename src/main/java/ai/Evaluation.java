package ai;

import ai.domain.State;
import ai.utils.StateContains;

import java.util.ArrayList;

import static consts.Consts.*;
import static consts.Consts.Directions.*;
import static consts.Consts.Directions.LEFT_UP;
import static consts.Consts.X;
import static consts.Consts.Y;

/**
 * Created by jaeyoung on 2017. 4. 14..
 */

public class Evaluation {
    public static float eval(State state) {


        float count = 0;
        ArrayList<ArrayList<String>> everySequenceState = state.everySequenceState();
        ArrayList<ArrayList<String>> everyEnemySequenceState = state.everyEnemySequenceState();

        for (ArrayList<String> each:
                everySequenceState) {
            count += calculateEvaluationFromEachSequence(each);
//            System.out.println(count+" everySequenceState");
        }

        for (ArrayList<String> each:
                everyEnemySequenceState) {
            count -= calculateEvaluationFromEachSequence(each);
//            System.out.println(count+" enemyState");
        }

        return count;
    }

    private static float calculateEvaluationFromEachSequence(ArrayList<String> each) {
        float evaluation = 0;

        String seq = AA;
        evaluation += StateContains.numOfContains(each,seq) * AA_SCORE;

        seq = AB;
        evaluation += StateContains.numOfContains(each,seq) * AB_SCORE;

        return evaluation;
    }
}
