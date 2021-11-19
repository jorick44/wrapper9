package wrapper;

import weka.classifiers.trees.J48;
import weka.core.Instances;

public class ConstructTree {
    protected J48 buildTree(Instances instances) throws Exception {
        J48 tree = new J48();
        tree.setMinNumObj(6);
        tree.buildClassifier(instances);
        return tree;
    }
}
