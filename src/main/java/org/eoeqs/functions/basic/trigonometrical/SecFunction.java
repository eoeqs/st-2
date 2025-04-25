package org.eoeqs.functions.basic.trigonometrical;


public class SecFunction{
    private final CosFunction cos;

    public SecFunction(CosFunction cos) {
        this.cos = cos;
    }

    public double calculate(double x, double eps) {
        if (x % (Math.PI/2) == 0.0 && x % (Math.PI) != 0.0) {
            throw new IllegalArgumentException("sec doesn't exist for pi/2 * n");
        }
        return 1/cos.calculate(x, eps);
    }
}

