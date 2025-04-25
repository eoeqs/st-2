package org.eoeqs.functions.basic.trigonometrical;

public class CscFunction {
    private final SinFunction sin;


    public CscFunction(SinFunction sin) {
        this.sin = sin;
    }

    public double calculate(double x, double eps) {
        if (x % (Math.PI) == 0.0) {
            throw new IllegalArgumentException("csc doesn't exist for pi*n");
        }
        return 1/sin.calculate(x, eps);
    }
}
