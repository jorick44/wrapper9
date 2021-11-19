package wrapper;

import org.apache.commons.cli.*;

public class CliOptionsProvider implements OptionsProvider{
    private Options options;
    private CommandLine cmd;
    private Double cg00050873;
    private Double cg00212031;
    private Double cg01707559;
    private Double cg02839557;
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
                "cg00050873",
                true,
                "The ratio of Methylation for cg00050873 between 0 & 1"));
        options.addOption(new Option("b",
                "cg00212031",
                true,
                "The ratio of Methylation for cg00212031 between 0 & 1"));
        options.addOption(new Option("c",
                "cg01707559",
                true,
                "The ratio of Methylation for cg01707559 between 0 & 1"));
        options.addOption(new Option("d",
                "cg02839557",
                true,
                "The ratio of Methylation for cg02839559 between 0 & 1"));
        options.addOption(new Option("f",
                "file",
                true,
                "The input file "));
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
                final Double[] numbers = {Double.parseDouble(cmd.getOptionValue('a')),
                        Double.parseDouble(cmd.getOptionValue('b')),
                        Double.parseDouble(cmd.getOptionValue('c')),
                        Double.parseDouble(cmd.getOptionValue('d'))};
                for (Double cg : numbers) {
                    if (cg < 0) {
                        throw new ParseException("Number is below zero: " + cg);
                    } else if (cg > 1) {
                        throw new ParseException("Number is above one: " + cg);
                    }
                }
                this.cg00050873 = numbers[0];
                this.cg00212031 = numbers[1];
                this.cg01707559 = numbers[2];
                this.cg02839557 = numbers[3];

            } catch (NumberFormatException nfe) {
                throw new ParseException("Number cannot be parsed: "
                        + cmd.getOptionValue('a'));
            }
        }

    }

    @Override
    public Double getCg00050873() {
        return cg00050873;
    }

    @Override
    public Double getCg00212031() {
        return cg00212031;
    }

    @Override
    public Double getCg01707559() {
        return cg01707559;
    }

    @Override
    public Double getCg02839557() {
        return cg02839557;
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
