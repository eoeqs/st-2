package org.eoeqs.functions.system;

import org.eoeqs.functions.basic.trigonometrical.CosFunction;
import org.eoeqs.functions.basic.trigonometrical.CscFunction;
import org.eoeqs.functions.basic.trigonometrical.SecFunction;

public class FirstFunction {
    private final SecFunction sec;
    private final CosFunction cos;
    private final CscFunction csc;

    public FirstFunction(SecFunction sec, CosFunction cos, CscFunction csc) {
        this.sec = sec;
        this.cos = cos;
        this.csc = csc;
    }

    public double calculate(double x, double eps) {
        if (x > 0) {
            throw new IllegalArgumentException("Function is defined only for x <= 0");
        }

        double secVal = sec.calculate(x, eps);
        double cosVal = cos.calculate(x, eps);
        double cscVal = csc.calculate(x, eps);

        double numerator = Math.pow(secVal, 3) * cosVal + cscVal;

        double denominator = cosVal - secVal;

        return Math.pow(numerator / denominator, 3);
    }
}
