package cn.quickits.sewing;

import org.apache.commons.cli.*;

public class Sewing {

    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption(
                Option.builder("h")
                        .desc("list help")
                        .longOpt("help")
                        .build()
        );
        options.addOption(
                Option.builder("v")
                        .desc("version name")
                        .longOpt("version")
                        .build()
        );
        options.addOption(
                Option.builder("i")
                        .desc("svg file or dir")
                        .longOpt("input")
                        .hasArg()
                        .build()
        );

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("h")) {
            String formatStr = "Sewing cli args list";
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp(formatStr, "", options, "");
            return;
        }

        if (cmd.hasOption("v")) {
            System.out.println("v0.1.1");
            return;
        }

        if (cmd.hasOption("i")) {

        }
    }

}
