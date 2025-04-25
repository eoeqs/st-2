package org.eoeqs;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.function.BiFunction;

public class CsvWriter {
    private final BiFunction<Double, Double, Double> function;
    private final double startX;
    private final double endX;
    private final double step;
    private final double eps;
    private final String delimiter;
    private final Path filePath;
    private final DecimalFormat numberFormat;

    public CsvWriter(BiFunction<Double, Double, Double> function,
                     double startX,
                     double endX,
                     double step,
                     double eps,
                     String delimiter,
                     Path filePath) {
        this.function = function;
        this.startX = startX;
        this.endX = endX;
        this.step = Math.abs(step) * Math.signum(endX - startX);
        this.eps = eps;
        this.delimiter = delimiter;
        this.filePath = filePath;

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        this.numberFormat = new DecimalFormat("0.##########", symbols);
    }

    public void write() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write("X" + delimiter + "Result");
            writer.newLine();

            double currentX = startX;
            while (isInRange(currentX)) {
                try {
                    double result = function.apply(currentX, eps);
                    writeLine(writer, currentX, result);
                } catch (IllegalArgumentException e) {
                    writeErrorLine(writer, currentX, e);
                }
                currentX += step;
            }
        }
    }

    private boolean isInRange(double x) {
        return step > 0 ? x <= endX : x >= endX;
    }

    private void writeLine(BufferedWriter writer, double x, double result) throws IOException {
        writer.write(numberFormat.format(x) + delimiter + numberFormat.format(result));
        writer.newLine();
    }

    private void writeErrorLine(BufferedWriter writer, double x, Exception e) throws IOException {
        writer.write(numberFormat.format(x) + delimiter + "ERROR: " + e.getMessage().replaceAll(",", ";"));
        writer.newLine();
    }
}