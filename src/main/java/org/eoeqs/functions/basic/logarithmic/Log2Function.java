package org.eoeqs.functions.basic.logarithmic;


public class Log2Function  {
    private final LnFunction ln;

    public Log2Function(LnFunction ln) {
        this.ln = ln;
    }

    public double calculate(double x, double eps) {
        double result = 0;
        result += ln.calculate(x, eps)/ln.calculate(2.0, eps);
        return result;
    }
}

