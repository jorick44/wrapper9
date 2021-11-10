package wrapper;

import org.apache.commons.cli.*;

public class CliOptionsProvider implements OptionsProvider{
    private Options options;
    private CommandLine cmd;
    private int age;
    private String file;
    private Boolean hasFile;

    public CliOptionsProvider(String[] args) {
        init();
        parseArgs(args);
    }

    private void parseArgs(String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            this.cmd = parser.parse(options, args);
            if (cmd.hasOption('h')) {
                printHelp();
            }
            verify();
        } catch (ParseException e) {
            System.out.println("Something went wrong while parsing, cause: "
                    + e.getMessage());
            printHelp();
        }
    }

    private void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "java -jar MyApp.jar [options]", options );
        System.exit(0);
    }

    private void init(){
        this.options = new Options();
        options.addOption(new Option("a",
                "age",
                true,
                "The number - a positive number below 120"));
        options.addOption(new Option("f",
                "file",
                true,
                "The input file "));
        options.addOption(new Option("g",
                "gender",
                true,
                "gender either M or F"
        ));
        options.addOption(new Option("h",
                "help",
                false,
                "Prints the help"));
    }

    private void verify() throws ParseException {
        if (!cmd.hasOption('f')) {
            hasFile = false;
        } else {
            this.file = cmd.getOptionValue('f');
            hasFile = true;
        }
        if (!cmd.hasOption('a')) {
            throw new ParseException("no number provided");
        } else {
            try {
                final String numberStr = cmd.getOptionValue('a');
                int age = Integer.parseInt(numberStr);
                if (age < 0) {
                    throw new ParseException("Number is below zero: " + age);
                }else if(age > 120){
                    throw new ParseException("Number is above 120: " + age);
                }
                this.age = age;
            } catch (NumberFormatException nfe) {
                throw new ParseException("Number cannot be parsed: "
                        + cmd.getOptionValue('a'));
            }
        }

    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getFileName() {
        return file;
    }

    @Override
    public Boolean hasFile() {
        return hasFile;
    }
}
