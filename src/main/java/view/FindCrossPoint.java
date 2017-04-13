package view;

import static consts.Consts.CELLSIZE;
import static consts.Consts.XSTART;
import static consts.Consts.YSTART;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class FindCrossPoint {
    public static int[] find(int x, int y) {
        return new CrossPointLogic(x, y).getResult();
    }
}

class CrossPointLogic {

    private int x, y;

    private int[] points = new int[2];

    public CrossPointLogic(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private void logic(int point, int index) {
        int start;

        if (index == 0) start = XSTART;
        else start = YSTART;

        if (((point + start) % CELLSIZE) < CELLSIZE/2)
            points[index] = (int)((point + start-10) / CELLSIZE) * CELLSIZE - 9;
        else points[index] = (int)((point + start-10) / CELLSIZE) * CELLSIZE - 9;
    }

    public int[] getResult() {
        logic(x, 0);
        logic(y, 1);
        // // System.out.println(points[0]+" "+points[1]);
        return points;
    }

}
