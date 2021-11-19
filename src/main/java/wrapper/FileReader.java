package wrapper;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.IOException;

public class FileReader {
    public static Instances readFile(String file) throws IOException{
        try {
            DataSource source = new DataSource(file);
            Instances instances = source.getDataSet();
            if (instances.classIndex() == -1) {
                instances.setClassIndex(instances.numAttributes() - 1);
            }
            return instances;
        } catch (Exception e) {
            throw new IOException("cannot read file " + file);
        }
    }
}
