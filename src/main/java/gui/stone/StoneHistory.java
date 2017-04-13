package gui.stone;

import java.awt.*;

/**
 * Created by jaeyoung on 2017. 4. 13..
 */
public class StoneHistory {
    public int[] points = new int[2];

    Image stone;

    public StoneHistory(int[] points, Image stone) {
        this.points = points;
        this.stone = stone;
    }

    public Image getStone() {
        return this.stone;
    }

    public String toString() {
        return "" + points[0] + " " + points[1];
    }
}