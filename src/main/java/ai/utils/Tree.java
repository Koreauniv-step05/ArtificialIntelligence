package ai.utils;

import ai.domain.State;

import java.util.ArrayList;

/**
 * Created by jaeyoung on 2017. 4. 16..
 */
public abstract class Tree<T> {
    protected ArrayList<Tree<T>> children;
    protected T data;

    public Tree(T data) {
        this.data = data;
        this.children = new ArrayList<Tree<T>>();
    }

    public T getData() {
        return data;
    }

    public void addChild(Tree<T> child){
        this.children.add(child);
    }
    public ArrayList<Tree<T>> getChildren() {
        return this.children;
    }
    public int numOfChildren() {
        return this.children.size();
    }

    public void addChildren(ArrayList<T> data) {
        for (T each:
                data) {

            this.addChild(each);
        }
    }

    public abstract float eval();
    public abstract void addChild(T data);
}
