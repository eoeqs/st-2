package org.eoeqs.functions.system;

import org.eoeqs.functions.basic.logarithmic.LnFunction;
import org.eoeqs.functions.basic.logarithmic.Log10Function;
import org.eoeqs.functions.basic.logarithmic.Log2Function;
import org.eoeqs.functions.basic.logarithmic.Log3Function;

public class SecondFunction {
    private final Log2Function log2;
    private final Log10Function log10;
    private final LnFunction ln;
    private final Log3Function log3;

    public SecondFunction(Log2Function log2, Log10Function log10, LnFunction ln, Log3Function log3) {
        this.log2 = log2;
        this.log10 = log10;
        this.ln = ln;
        this.log3 = log3;
    }

    public double calculate(double x, double eps) {
        if (x <= 0) {
            throw new IllegalArgumentException("Function is defined only for x > 0");
        }

        double log2x = log2.calculate(x, eps);
        double log10x = log10.calculate(x, eps);
        double lnx = ln.calculate(x, eps);
        double log3x = log3.calculate(x, eps);

        double part1 = Math.pow(Math.pow(log2x * log2x, 3), 2);

        double part2 = log10x / Math.pow(lnx, 2);

        double part3 = lnx * (log10x - log3x);

        return (part1 - part2) * part3;
    }
}
