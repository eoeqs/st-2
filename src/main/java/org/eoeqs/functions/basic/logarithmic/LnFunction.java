package org.eoeqs.functions.basic.logarithmic;

public class LnFunction {
    protected static final int max_iterations = 10000;

    public double calculate(double x, double eps) {
        if (x <= 0) {
            throw new IllegalArgumentException("X must be positive");
        }

        double k = ((x - 1) * (x - 1)) / ((x + 1) * (x + 1));
        double result = 0;
        double currentValue = (x - 1) / (x + 1);
        int step = 1;

        while (Math.abs(currentValue) > eps / 1000 && step <= max_iterations) {
            result += currentValue;
            currentValue = currentValue * k * (2 * step - 1) / (2 * step + 1);
            step++;
        }

        if (step == max_iterations) {
            throw new IllegalArgumentException("Iterations limit exceeded");
        }

        result *= 2;
        return result;
    }
}

