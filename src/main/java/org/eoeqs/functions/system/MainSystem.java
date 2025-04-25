package org.eoeqs.functions.system;

public class MainSystem {
    private final FirstFunction firstFunc;
    private final SecondFunction secondFunc;

    public MainSystem(FirstFunction firstFunc, SecondFunction secondFunc) {
        this.firstFunc = firstFunc;
        this.secondFunc = secondFunc;
    }

    public double calculate(double x, double eps) {
        if (x <= 0) {
            return firstFunc.calculate(x, eps);
        } else {
            return secondFunc.calculate(x, eps);
        }
    }
}
