package ai.utils;

/**
 * Created by jaeyoung on 2017. 4. 16..
 */
public class TestTree extends Tree<Float> {
    public TestTree(Float data) {
        super(data);
    }

    @Override
    public float eval() {
        return super.getData();
    }

    @Override
    public void addChild(Float data) {
        super.addChild(new TestTree(data));
    }
}
