package wrapper;

public class AlgorithmEngine {
    void start(OptionsProvider optionsProvider) {
        if(optionsProvider.hasFile()) {
            String inputFile = optionsProvider.getFileName();
            System.out.println("inputFile = " + inputFile);
        }else {
            int number = optionsProvider.getAge();
            System.out.println("number = " + number);
        }
    }
}
