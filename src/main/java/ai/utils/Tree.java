package ai.utils;

import ai.domain.State;

import java.util.ArrayList;

/**
 * Created by jaeyoung on 2017. 4. 16..
 */
public class Tree {
    private ArrayList<Tree> children;
    private State data;

    public Tree(State data) {
        this.data = data;
        this.children = new ArrayList<Tree>();
    }

    public Tree(int[][] data) {
        this.children = children;
        this.data = new State(data);
    }

    public float eval() {
        return data.eval();
    }

    public void addChild(Tree child){
        this.children.add(child);
    }

    public void addChild(State data) {
        this.children.add(new Tree(data));
    }

    public ArrayList<Tree> getChildren() {
        return this.children;
    }

    public int numOfChildren() {
        return this.children.size();
    }
}
