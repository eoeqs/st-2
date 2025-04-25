package org.eoeqs.functions.basic.trigonometrical;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class CosFunction {
    protected static final int max_iterations = 2000;

    public double calculate(double x, double eps) {


        x = normalizeAngle(x);

        BigDecimal result = BigDecimal.ZERO;
        int n = 0;

        BigDecimal prev;

        do {
            prev = result;
            BigInteger factorial = BigInteger.ONE;
            for (int i = 1; i <= 2 * n; i++) {
                factorial = factorial.multiply(BigInteger.valueOf(i));
            }

            BigDecimal term = new BigDecimal(x).pow(2 * n)
                    .divide(new BigDecimal(factorial), 20, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf((-1)).pow(n));

            result = result.add(term);
            n++;
        } while (Math.abs(result.subtract(prev).doubleValue()) >= eps && n < max_iterations);

        if (n == max_iterations) {
            throw new IllegalArgumentException("Iterations limit exceeded");
        }

        return result.doubleValue();
    }

    private double normalizeAngle(double x) {
        double twoPi = 2 * Math.PI;
        x = x % twoPi;
        return x < 0 ? x + twoPi : x;
    }


}
