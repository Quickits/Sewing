package cn.quickits.sewing;

import cn.quickit.BuildConfig;
import org.apache.commons.cli.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
                        .desc("show version name")
                        .longOpt("version")
                        .build()
        );
        options.addOption(
                Option.builder("i")
                        .desc("input dirs or svg files")
                        .longOpt("input")
                        .hasArgs()
                        .build()
        );
        options.addOption(
                Option.builder("o")
                        .desc("output dir")
                        .longOpt("output")
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
            System.out.println(BuildConfig.VERSION);
            return;
        }

        List<File> inputPath = new ArrayList<>();

        File outputPath = null;

        if (cmd.hasOption("i")) {
            String[] values = cmd.getOptionValues("i");
            for (String s : values) {
                inputPath.add(Paths.get(s).toFile());
            }
        }

        if (cmd.hasOption("o")) {
            String value = cmd.getOptionValue("o");
            outputPath = Paths.get(value).toFile();
        }

        if (inputPath.size() > 1 && outputPath == null) {
            System.err.println("ERROR: Miss of output dir. please add -o <arg>");
            return;
        } else if (outputPath == null) {
            File file = inputPath.get(0);
            if (file.isFile()) {
                outputPath = inputPath.get(0).getParentFile();
            } else if (file.isDirectory()) {
                outputPath = inputPath.get(0);
            } else {
                System.err.println("ERROR: Miss of output dir. please add -o <arg>");
                return;
            }
        }

        new Processor(inputPath, outputPath).process();
    }

}
