import org.eoeqs.functions.basic.trigonometrical.CosFunction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CosFunctionTest {
    private final CosFunction cos = new CosFunction();
    private static final double EPS = 1e-6;

    @ParameterizedTest
    @CsvSource({
            "0.0, 1.0",
            "1.57079632679, 0.0",
            "3.14159265359, -1.0",
            "4.71238898038, 0.0",
            "0.78539816339, 0.7071067",
            "2.35619449019, -0.7071067",
            "-1.57079632679, 0.0",
            "6.28318530718, 1.0"
    })
    void testCosValues(double x, double expected) {
        assertEquals(expected, cos.calculate(x, EPS), 1e-4);
    }

    @Test
    void cosNearPiOver2() {
        double x = Math.PI/2 - 0.001;
        assertEquals(Math.cos(x), cos.calculate(x, EPS), 1e-4);
    }
}