package wrapper;

import java.io.IOException;
import java.util.Arrays;

public class CliOptionsProviderMain {
    public static void main(String[] args) throws Exception {
        System.out.println("Arrays.toString(args) = "
                + Arrays.toString(args));
        OptionsProvider optionsProvider = new CliOptionsProvider(args);
        AlgorithmEngine algorithmEngine = new AlgorithmEngine();
        algorithmEngine.start(optionsProvider);
    }
}
