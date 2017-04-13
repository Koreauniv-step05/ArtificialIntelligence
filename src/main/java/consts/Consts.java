package consts;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public interface Consts {
    int BLACK_STONE = 1;
    int WHITE_STONE = -1;
    int NONE_STONE = 0;

    int X = 0;
    int Y = 1;

    interface Directions {
        int[] UP = {0, -1};
        int[] RIGHT_UP = {1, -1};
        int[] RIGHT = {1, 0};
        int[] RIGHT_DOWN = {1, 1};
        int[] DOWN = {0, 1};
        int[] LEFT_DOWN = {-1, 1};
        int[] LEFT = {-1, 0};
        int[] LEFT_UP = {-1, -1};
    }

    // GUI Coordinates
    int XSTART = 20, YSTART = 20, CELLSIZE = 29;

    int HUMAN_PLAYER = 0;
    int AI_PLAYER = 1;

    int VICTORY = 1;
}
