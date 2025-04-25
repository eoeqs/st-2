package org.eoeqs.functions.basic.trigonometrical;

public class SinFunction {
    private final CosFunction cos;

    public SinFunction(CosFunction cos) {
        this.cos = cos;
    }

    public double calculate(double x, double eps) {
        double argument = Math.PI / 2 - x;
        return cos.calculate(argument, eps);
    }
}
