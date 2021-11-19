package wrapper;

import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;

public class ML_Classifier {
    protected void classifyFile(J48 tree, Instances unknown) throws Exception {
        for (int i = 0; i < unknown.numInstances(); i++) {
            System.out.println("Predicted instance number: " + i );
            double prediction = tree.classifyInstance(unknown.instance(i));
            Instance instance = unknown.instance(i);
            System.out.println("\tCG00050873 Methylation ratio = " + instance.value(0) +
            "\n\tCG00212031 Methylation ratio = " + instance.value(1) +
            "\n\tCG01707559 Methylation ratio = " + instance.value(2) +
            "\n\tCG02839559 Methylation ratio = " + instance.value(3));
            printPrediction(prediction);
        }
    }

    protected void classifyCLI(J48 tree, Instances data, ArrayList<Double> valueArray) throws Exception{
        double[] values = new double[data.numAttributes()];
        values[0] = valueArray.get(0);
        values[1] = valueArray.get(1);
        values[2] = valueArray.get(2);
        values[3] = valueArray.get(3);

        Instance instance = new DenseInstance(1, values);
        instance.setDataset(data);

        double prediction = tree.classifyInstance(instance);
        System.out.println("Given values:" +
                "\n\tCG00050873 Methylation ratio = " + values[0] +
                "\n\tCG00212031 Methylation ratio = " + values[1] +
                "\n\tCG01707559 Methylation ratio = " + values[2] +
                "\n\tCG02839557 Methylation ratio = " + values[3]);
        printPrediction(prediction);
    }

    private void printPrediction(double prediction) {
        System.out.println("\nExpected patient smoking status:");
        if (prediction == 1.0) {
            System.out.println("\nNever\n");
        } else {
            System.out.println("\nCurrent\n");
        }
    }
}
