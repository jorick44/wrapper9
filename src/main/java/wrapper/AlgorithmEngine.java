package wrapper;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.SerializationHelper;

import java.util.ArrayList;


public class AlgorithmEngine {
    final String model = "data/model.model";
    final String trainingData = "data/ml_data_pruned.csv";

    void start(OptionsProvider optionsProvider) throws Exception {
        try {
            Instances data = FileReader.readFile(trainingData);
            J48 tree = (J48) SerializationHelper.read(model);
            ML_Classifier classifier = new ML_Classifier();
            if (optionsProvider.hasFile()) {
                Instances instances = FileReader.readFile(optionsProvider.getFileName());
                classifier.classifyFile(tree, instances);
            } else {
                ArrayList<Double> values = new ArrayList<Double>();
                values.add(optionsProvider.getCg00050873());
                values.add(optionsProvider.getCg00212031());
                values.add(optionsProvider.getCg01707559());
                values.add(optionsProvider.getCg02839557());

                classifier.classifyCLI(tree, data, values);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
