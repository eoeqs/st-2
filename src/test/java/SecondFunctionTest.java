import org.eoeqs.functions.basic.logarithmic.LnFunction;
import org.eoeqs.functions.basic.logarithmic.Log10Function;
import org.eoeqs.functions.basic.logarithmic.Log2Function;
import org.eoeqs.functions.basic.logarithmic.Log3Function;
import org.eoeqs.functions.system.SecondFunction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SecondFunctionTest {
    private static final double EPS = 1e-6;
    private final Log2Function log2Mock = mock(Log2Function.class);
    private final Log10Function log10Mock = mock(Log10Function.class);
    private final LnFunction lnMock = mock(LnFunction.class);
    private final Log3Function log3Mock = mock(Log3Function.class);
    private final SecondFunction function = new SecondFunction(
            log2Mock, log10Mock, lnMock, log3Mock
    );

    @ParameterizedTest
    @CsvSource({
            "1.6,        0.6780719051126,      0.2041199826559248,      0.4700036292457,     0.427815739996,     0.0961565621832193",
            "1.72601,    0.7874408230966,      0.2370433075624110,      0.5458123863872,     0.496819844464,     0.104760938342304",
            "0.6,        -0.736965594166,      -0.221848749616356,      -0.510825623765,     -0.464927520717,    -0.10877538354179246",
            "2.3,        1.2016338611696,      0.3617278360175928,      0.8329091229351,     0.7581465559108,    -2.820226026841371",
            "2.0,        1.0,                  0.3010299956639812,      0.6931471805599,     0.6309297535714,    -0.08537018545"
    })
    void calculateTest(double x, double log2x, double log10x,
                       double lnx, double log3x, double expected) {
        when(log2Mock.calculate(x, EPS)).thenReturn(log2x);
        when(log10Mock.calculate(x, EPS)).thenReturn(log10x);
        when(lnMock.calculate(x, EPS)).thenReturn(lnx);
        when(log3Mock.calculate(x, EPS)).thenReturn(log3x);

        double result = function.calculate(x, EPS);
        assertEquals(expected, result, Math.abs(expected * 0.01));
    }
}