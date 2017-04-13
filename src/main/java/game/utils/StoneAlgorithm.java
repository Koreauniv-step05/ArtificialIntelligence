package game.utils;

import static consts.Consts.Directions.*;
import static consts.Consts.NONE_STONE;
import static consts.Consts.X;
import static consts.Consts.Y;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class StoneAlgorithm {

    private int analysis(int[][] board, int xStart, int yStart) {
        int[] point = new int[2];
        point[0] = xStart;
        point[1] = yStart;

        int up = 0;
        int rightUp = 0;
        int right = 0;
        int rightDown = 0;
        int down = 0;
        int leftDown = 0;
        int left = 0;
        int leftUp = 0;

        int[] result = new int[4];

        up        = countSameStone(board, point, UP);
        rightUp   = countSameStone(board, point, RIGHT_UP);
        right     = countSameStone(board, point, RIGHT);
        rightDown = countSameStone(board, point, RIGHT_DOWN);
        down      = countSameStone(board, point, DOWN);
        leftDown  = countSameStone(board, point, LEFT_DOWN);
        left      = countSameStone(board, point, LEFT);
        leftUp    = countSameStone(board, point, LEFT_UP);

        result[0] = up + down;
        result[1] = rightUp + leftDown;
        result[2] = right + left;
        result[3] = rightDown + leftUp;

        for (int i = 0; i < result.length; i++) {
            if(result[i] == 4) return 1;
        }

        return -1;
    }

    private int countSameStone(int[][] board, int[] point, int[] direction) {
        int[] checkPoint = new int[2];

        checkPoint[0] = point[0];
        checkPoint[1] = point[1];

        int count = 0;

        while (true) {
            checkPoint[X] += direction[X];
            checkPoint[Y] += direction[Y];

            if (checkPoint[X] < 0 || checkPoint[Y] < 0)       // pointer reach edge of board
                break;
            else if (checkPoint[X] > 18 || checkPoint[Y] > 18)
                break;

//			System.out.println("x : " + checkPoint[0]);
//			System.out.println("y : " + checkPoint[1]);
//			System.out.println("count : " + count);

            if(checking(board, point, checkPoint))
                count++;
            else break;
        }
        return count;
    }

    private boolean checking(int[][] board, int[] point, int[] checkPoint) {
        int x = point[X];
        int y = point[Y];
        int x_check = checkPoint[X];
        int y_check = checkPoint[Y];

        try {
            if(board[x][y] == board[x_check][y_check])
                return true;
            else {
                return false;
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("x : " + x_check);
            System.out.println("y : " + y_check);
            System.out.println("ArrayIndexOutOfBoundsException!!");
            return false;
        }

    }

    public void subLastStone(int[][] board, int[] subPoint) {
        board[subPoint[X]-1][subPoint[Y]-1] = NONE_STONE;
    }
}
