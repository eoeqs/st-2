import org.eoeqs.functions.basic.trigonometrical.*;
import org.eoeqs.functions.basic.logarithmic.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BasicFunctionsIntegrationTest {
    private static final double EPS = 1e-5;

    @Test
    void testCosSinIntegration() {
        CosFunction cos = new CosFunction();
        SinFunction sin = new SinFunction(cos);

        assertEquals(Math.sin(Math.PI/4), sin.calculate(Math.PI/4, EPS), EPS);
    }

    @Test
    void testLog2Integration() {
        LnFunction ln = new LnFunction();
        Log2Function log2 = new Log2Function(ln);

        assertEquals(1.0, log2.calculate(2.0, EPS), EPS);
        assertEquals(3.0, log2.calculate(8.0, EPS), 1e-3);
    }
}
