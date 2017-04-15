package ai.domain;


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

    public ArrayList<String> garo() {
        return toStringArr(state);
    }

    public ArrayList<String> sero() {
        int[][] transState = Matrix.transpose(state);

        return toStringArr(transState);
    }

    public ArrayList<String> leftdowncross() {
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

    public ArrayList<String> rightdowncross() {
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
}