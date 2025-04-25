package org.eoeqs.functions.basic.logarithmic;

public class Log3Function {
    private final LnFunction ln;

    public Log3Function(LnFunction ln) {
        this.ln = ln;
    }

    public double calculate(double x, double eps) {
        double result = 0;
        result += ln.calculate(x, eps)/ln.calculate(3.0, eps);
        return result;
    }
}
