package ai.utils;

import ai.algorithm.PossibleNextStone;
import ai.domain.State;

import java.util.ArrayList;

import static consts.Consts.BLACK_STONE;
import static consts.Consts.WHITE_STONE;

/**
 * Created by jaeyoung on 2017. 4. 16..
 */
public class GomokuTree extends Tree<State> {
    private int expendedDepth = 0;
    private float alpha;
    private float beta;

    public GomokuTree(State data) {
        super(data);
    }

    public GomokuTree(int[][] data) {
        super(new State(data));
    }

    public float eval() {
        return this.data.eval();
    }

    public void addChild(GomokuTree child){
        this.children.add(child);
    }

    public void addChild(State data) {
        this.children.add(new GomokuTree(data));
    }

    public void extendDeeper(int targetDepth, int presentDepth) {
        if (targetDepth == presentDepth) {
            return;
        }
        if(!(expendedDepth > presentDepth)) {
            int stoneType;
            if (presentDepth%2==0) {
                stoneType = BLACK_STONE;
            } else {
                stoneType = WHITE_STONE;
            }

            this.addChildren(PossibleNextStone.possibleNextStone(this.getData(), stoneType));
        }
        this.extendDeeperEachChild(targetDepth, presentDepth+1);
        expendedDepth = targetDepth;
//        this.extendDeeper(targetDepth, presentDepth+1);
    }

    public void extendDeeperEachChild(int targetDepth, int presentDepth) {
        // System.out.println(targetDepth+"presentDepth: "+presentDepth);
        for (Tree<State> child:
                getChildren()) {
            ((GomokuTree)child).extendDeeper(targetDepth, presentDepth);
        }
    }

    public void extendDeeper(int targetDepth) {
        extendDeeper(targetDepth, 0);
    }

    public float getAlpha() {
        return this.alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getBeta() {
        return this.beta;
    }

    public void setBeta(float beta) {
        this.beta = beta;
    }
}
