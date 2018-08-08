package cn.quickits.sewing;

import cn.quickits.sewing.util.FileUtils;
import com.android.ide.common.vectordrawable.Svg2Vector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@SuppressWarnings("ALL")
class Processor {

    private List<File> inputPath;

    private File outputPath;

    Processor(List<File> inputPath, File outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;

        if (!outputPath.exists()) outputPath.mkdirs();
    }

    void process() {
        for (File file : inputPath) {
            if (!file.exists()) {
                System.err.println(file.getAbsolutePath() + " not found");
                continue;
            }
            process(file);
        }
    }

    private void process(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File childFile : files) {
                    process(childFile);
                }
            }
            return;
        }

        convertToVectorDrawable(file);
    }

    private void convertToVectorDrawable(File file) {
        if (FileUtils.getFileExtension(file).toLowerCase().equals("svg")) {
            File outputFile = new File(outputPath, FileUtils.getFileName(file) + ".xml");

            System.out.println(">> Process: " + file);

            try {
                Svg2Vector.parseSvgToXml(file, new FileOutputStream(outputFile));
            } catch (FileNotFoundException e) {
                System.err.println("<< Error: " + file.getAbsolutePath() + "not found");
            }

            System.out.println("<< Success: " + outputFile);
        }
    }

}
