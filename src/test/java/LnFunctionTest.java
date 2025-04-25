import org.eoeqs.functions.basic.logarithmic.LnFunction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


public class LnFunctionTest {
    private final LnFunction ln = new LnFunction();
    private static final double EPS = 1e-6;

    @ParameterizedTest
    @CsvSource({
            "1.0, 0.0",
            "2.7182818284, 1.0",
            "0.5, -0.693147",
            "2.0, 0.693147",
            "0.1, -2.302585",
            "100.0, 4.605170"})
    void testLnValues(double x, double expected) {
        assertEquals(expected, ln.calculate(x, EPS), 1e-4);
    }

}