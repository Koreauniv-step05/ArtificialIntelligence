package ai.domain;


import ai.Evaluation;
import ai.utils.Matrix;

import java.util.ArrayList;

import static consts.Consts.WALL_STONE;

/**
 * Created by jaeyoung on 2017. 4. 15..
 */
public class State {
    private int[][] state;

    public State(int[][] state) {
        this.state = state;
    }

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

    public ArrayList<String> garo(int[][] state) {
        return toStringArr(state);
    }

    public ArrayList<String> sero(int[][] state) {
        int[][] transState = Matrix.transpose(state);

        return toStringArr(transState);
    }

    public ArrayList<String> leftdowncross(int[][] state) {
        ArrayList<String> strArr = new ArrayList<String>();
        String str = "";

        for (int i = 0; i < state.length; i++) {
            str = WALL_STONE+"";
            for (int j = i; j >= 0; j--) {
                str = str + state[i-j][j];
            }
            str = str + WALL_STONE;
//            System.out.println(str);
            strArr.add(str);
        }

        for (int j = 1; j < state.length; j++) {
            str = WALL_STONE+"";
            for (int i = 0; (j+i) < state.length; i++) {
                str = str + state[j+i][state.length-i-1];
            }
            str = str+WALL_STONE;
//            System.out.println(str);
            strArr.add(str);
        }

        return strArr;
    }

    public ArrayList<String> rightdowncross(int[][] state) {
        ArrayList<String> strArr = new ArrayList<String>();
        String str = "";

        for (int i = 0; i < state.length; i++) {
            str = WALL_STONE+"";
            for (int j = 0; (i+j) < state[i].length; j++) {
                str = str + state[i+j][j];
            }
            str = str+WALL_STONE;

//            System.out.println(str);
            strArr.add(str);
        }

        for (int j = 1; j < state.length; j++) {
            str = WALL_STONE+"";
            for (int i = 0; (i+j) < state[j].length; i++) {
                str = str + state[i][i+j];
            }
            str = str+WALL_STONE;

//            System.out.println(str);
            strArr.add(str);
        }

        return strArr;
    }

    public ArrayList<String> toStringArr(int[][] state) {
        ArrayList<String> strArr = new ArrayList<String>();

        String str = "";

        for (int[] col:state) {
            str = WALL_STONE+"";
            for (int each:col) {
                str = str + each;
            }
            str = str+WALL_STONE;
            // System.out.println(str.toString());
            strArr.add(str);
        }
        return strArr;
    }

    private int[][] enemyState() {
        int[][] enemy = state.clone();
//        String logstrA = "";
//        String logstrB = "";

        for (int i = 0; i < state.length; i++) {
//            logstrA = "";
//            logstrB = "";
            enemy[i] = state[i].clone();
            for (int j = 0; j < state[i].length; j++) {
                enemy[i][j] = (-1)*state[i][j];
//                logstrA = logstrA + state[i][j];
//                logstrB = logstrB + enemy[i][j];
            }

//            System.out.println(""+logstrA);
//            System.out.println(""+logstrB);
        }

        return enemy;
    }

    public ArrayList<ArrayList<String>> everySequenceState() {
        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
        arr.add(garo(state));
        arr.add(sero(state));
        arr.add(rightdowncross(state));
        arr.add(leftdowncross(state));

        return arr;
    }

    public ArrayList<ArrayList<String>> everyEnemySequenceState() {
        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
        arr.add(garo(enemyState()));
        arr.add(sero(enemyState()));
        arr.add(rightdowncross(enemyState()));
        arr.add(leftdowncross(enemyState()));

        return arr;
    }

    public float eval() {
        return Evaluation.eval(this);
    }
}