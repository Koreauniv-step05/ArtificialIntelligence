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

    private static State mState;
    private static Evaluation mInstance;

    private Evaluation(int[][] mState) {
        this.mState = new State(mState);
    }

    public static Evaluation getInstance(int[][] mState) {
        if (mInstance == null) {
            mInstance = new Evaluation(mState);
        } else {
            mInstance.setState(mState);
        }

        return mInstance;
    }

    private void setState(int[][] mState) {
        this.mState.setState(mState);
    }

    private int[][] getState() {
        return this.mState.getState();
    }

    public float evaluate() {
        if (false) { // if infinity
            return INF;
        }

        if (false) { // if zero
            return ZERO;
        }

        // eval =

        return eval();
    }

    public int eval() {
        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
        arr.add(mState.garo());
        arr.add(mState.sero());
        arr.add(mState.rightdowncross());
        arr.add(mState.leftdowncross());

        String seq = AA;
        String seq2 = AB;
        int count = 0;

        for (ArrayList<String> each:
             arr) {
            count += StateContains.numOfContains(each,seq);
            count += StateContains.numOfContains(each,seq2);
        }

        return count;
    }
}
