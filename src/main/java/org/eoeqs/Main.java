package org.eoeqs;

import org.eoeqs.CsvWriter;
import org.eoeqs.functions.basic.trigonometrical.CosFunction;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        CosFunction cosFunction = new CosFunction();

        BiFunction<Double, Double, Double> cosineCalculator =
                cosFunction::calculate;

        CsvWriter csvWriter = new CsvWriter(
                cosineCalculator,
                -2 * Math.PI,
                2 * Math.PI,
                0.1,
                1e-6,
                ",",
                Path.of("cos_results.csv")
        );

        try {
            csvWriter.write();
            System.out.println("Файл cos_results.csv успешно создан");
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}