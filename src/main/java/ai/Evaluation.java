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

    private static float evalEachSequence(ArrayList<String> each, String seq, float score){
        return StateContains.numOfContains(each,seq) * score;
    }

    private static float calculateEvaluationFromEachSequence(ArrayList<String> each) {
        float evaluation = 0;


        evaluation += evalEachSequence(each, AA,AA_SCORE);
        evaluation += evalEachSequence(each, AB,AB_SCORE);
        evaluation += evalEachSequence(each, BA,BA_SCORE);
        evaluation += evalEachSequence(each, BAR,BA_SCORE);
        evaluation += evalEachSequence(each, CA,CA_SCORE);
        evaluation += evalEachSequence(each, CB,CB_SCORE);
        evaluation += evalEachSequence(each, CBR,CB_SCORE);
        evaluation += evalEachSequence(each, CC,CC_SCORE);
        evaluation += evalEachSequence(each, CCR,CC_SCORE);
        evaluation += evalEachSequence(each, DA,DA_SCORE);
        evaluation += evalEachSequence(each, DB,DB_SCORE);
        evaluation += evalEachSequence(each, DBR,DB_SCORE);
        evaluation += evalEachSequence(each, DC,DC_SCORE);
        evaluation += evalEachSequence(each, DCR,DC_SCORE);
        evaluation += evalEachSequence(each, DD,DD_SCORE);
        evaluation += evalEachSequence(each, DDR,DD_SCORE);
        evaluation += evalEachSequence(each, DE,DE_SCORE);
        evaluation += evalEachSequence(each, DF,DF_SCORE);
        evaluation += evalEachSequence(each, DFR,DF_SCORE);
        evaluation += evalEachSequence(each, DF,DF_SCORE);
        evaluation += evalEachSequence(each, DG,DG_SCORE);
        evaluation += evalEachSequence(each, DGR,DG_SCORE);
        evaluation += evalEachSequence(each, EA,EA_SCORE);
        evaluation += evalEachSequence(each, EB,EB_SCORE);
        evaluation += evalEachSequence(each, EBR,EB_SCORE);
        evaluation += evalEachSequence(each, EC,EC_SCORE);
        evaluation += evalEachSequence(each, ECR,EC_SCORE);
        evaluation += evalEachSequence(each, ED,ED_SCORE);
        evaluation += evalEachSequence(each, EDR,ED_SCORE);
        evaluation += evalEachSequence(each, EE,EE_SCORE);
        evaluation += evalEachSequence(each, EER,EE_SCORE);
        evaluation += evalEachSequence(each, EF,EF_SCORE);
        evaluation += evalEachSequence(each, EG,EG_SCORE);
        evaluation += evalEachSequence(each, EGR,EG_SCORE);
        evaluation += evalEachSequence(each, EH,EH_SCORE);
        evaluation += evalEachSequence(each, EHR,EH_SCORE);
        evaluation += evalEachSequence(each, EI,EI_SCORE);
        evaluation += evalEachSequence(each, EIR,EI_SCORE);
        evaluation += evalEachSequence(each, EJ,EJ_SCORE);
        evaluation += evalEachSequence(each, EK,EK_SCORE);
        evaluation += evalEachSequence(each, EKR,EK_SCORE);
        evaluation += evalEachSequence(each, EK,EK_SCORE);
        evaluation += evalEachSequence(each, EL,EL_SCORE);
        evaluation += evalEachSequence(each, ELR,EL_SCORE);

        return evaluation;
    }
}
