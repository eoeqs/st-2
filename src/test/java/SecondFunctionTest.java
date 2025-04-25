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
    private final Log2Function log2WithLnMock = new Log2Function(lnMock);
    private final Log3Function log3WithLnMock = new Log3Function(lnMock);
    private final Log10Function log10WithLnMock = new Log10Function(lnMock);
    private final SecondFunction function = new SecondFunction(
            log2Mock, log10Mock, lnMock, log3Mock
    );
    private final LnFunction realLn = new LnFunction();
    private final Log2Function realLog2 = new Log2Function(realLn);
    private final Log3Function realLog3 = new Log3Function(realLn);
    private final Log10Function realLog10 = new Log10Function(realLn);


    @ParameterizedTest
    @CsvSource({
            "1.6,        0.6780719051126,      0.2041199826559248,      0.4700036292457,     0.427815739996,     0.0961565621832193",
            "1.72601,    0.7874408230966,      0.2370433075624110,      0.5458123863872,     0.496819844464,     0.104760938342304",
            "0.6,        -0.736965594166,      -0.221848749616356,      -0.510825623765,     -0.464927520717,    -0.10877538354179246",
            "2.3,        1.2016338611696,      0.3617278360175928,      0.8329091229351,     0.7581465559108,    -2.820226026841371",
            "2.0,        1.0,                  0.3010299956639812,      0.6931471805599,     0.6309297535714,    -0.08537018545"
    })
    void calculateTestWithMocks(double x, double log2x, double log10x,
                                double lnx, double log3x, double expected) {
        when(log2Mock.calculate(x, EPS)).thenReturn(log2x);
        when(log10Mock.calculate(x, EPS)).thenReturn(log10x);
        when(lnMock.calculate(x, EPS)).thenReturn(lnx);
        when(log3Mock.calculate(x, EPS)).thenReturn(log3x);

        double result = function.calculate(x, EPS);
        assertEquals(expected, result, Math.abs(expected * 0.01));
    }

    @ParameterizedTest
    @CsvSource({
            "1.6,        0.6780719051126,      0.2041199826559248,      0.4700036292457,     0.427815739996,     0.0961565621832193",
            "1.72601,    0.7874408230966,      0.2370433075624110,      0.5458123863872,     0.496819844464,     0.104760938342304",
            "0.6,        -0.736965594166,      -0.221848749616356,      -0.510825623765,     -0.464927520717,    -0.10877538354179246",
            "2.3,        1.2016338611696,      0.3617278360175928,      0.8329091229351,     0.7581465559108,    -2.820226026841371",
            "2.0,        1.0,                  0.3010299956639812,      0.6931471805599,     0.6309297535714,    -0.08537018545"
    })
    void calculateTestWithRealLog2(double x, double log2x, double log10x,
                                   double lnx, double log3x, double expected) {
        SecondFunction func = new SecondFunction(
                log2WithLnMock, log10Mock, lnMock, log3Mock
        );

        when(log10Mock.calculate(x, EPS)).thenReturn(log10x);
        when(lnMock.calculate(x, EPS)).thenReturn(lnx);
        when(log3Mock.calculate(x, EPS)).thenReturn(log3x);
        when(lnMock.calculate(2.0, EPS)).thenReturn(0.6931471805599453);
        when(lnMock.calculate(10.0, EPS)).thenReturn(2.302585092994046);
        when(lnMock.calculate(3.0, EPS)).thenReturn(1.0986122886681098);
        double result = func.calculate(x, EPS);
        assertEquals(expected, result, Math.abs(expected * 0.01));
    }

    @ParameterizedTest
    @CsvSource({
            "1.6,        0.6780719051126,      0.2041199826559248,      0.4700036292457,     0.427815739996,     0.0961565621832193",
            "1.72601,    0.7874408230966,      0.2370433075624110,      0.5458123863872,     0.496819844464,     0.104760938342304",
            "0.6,        -0.736965594166,      -0.221848749616356,      -0.510825623765,     -0.464927520717,    -0.10877538354179246",
            "2.3,        1.2016338611696,      0.3617278360175928,      0.8329091229351,     0.7581465559108,    -2.820226026841371",
            "2.0,        1.0,                  0.3010299956639812,      0.6931471805599,     0.6309297535714,    -0.08537018545"
    })
    void calculateTestWithRealLog3(double x, double log2x, double log10x,
                                   double lnx, double log3x, double expected) {

        SecondFunction func = new SecondFunction(
                log2Mock, log10Mock, lnMock, log3WithLnMock
        );

        when(log2Mock.calculate(x, EPS)).thenReturn(log2x);
        when(log10Mock.calculate(x, EPS)).thenReturn(log10x);
        when(lnMock.calculate(x, EPS)).thenReturn(lnx);
        when(lnMock.calculate(2.0, EPS)).thenReturn(0.6931471805599453);
        when(lnMock.calculate(10.0, EPS)).thenReturn(2.302585092994046);
        when(lnMock.calculate(3.0, EPS)).thenReturn(1.0986122886681098);

        double result = func.calculate(x, EPS);
        assertEquals(expected, result, Math.abs(expected * 0.01));
    }

    @ParameterizedTest
    @CsvSource({
            "1.6,        0.6780719051126,      0.2041199826559248,      0.4700036292457,     0.427815739996,     0.0961565621832193",
            "1.72601,    0.7874408230966,      0.2370433075624110,      0.5458123863872,     0.496819844464,     0.104760938342304",
            "0.6,        -0.736965594166,      -0.221848749616356,      -0.510825623765,     -0.464927520717,    -0.10877538354179246",
            "2.3,        1.2016338611696,      0.3617278360175928,      0.8329091229351,     0.7581465559108,    -2.820226026841371",
            "2.0,        1.0,                  0.3010299956639812,      0.6931471805599,     0.6309297535714,    -0.08537018545"
    })
    void calculateTestWithRealLog10(double x, double log2x, double log10x,
                                    double lnx, double log3x, double expected) {
        SecondFunction func = new SecondFunction(
                log2Mock, log10WithLnMock, lnMock, log3Mock
        );

        when(log2Mock.calculate(x, EPS)).thenReturn(log2x);
        when(lnMock.calculate(x, EPS)).thenReturn(lnx);
        when(log3Mock.calculate(x, EPS)).thenReturn(log3x);
        when(lnMock.calculate(2.0, EPS)).thenReturn(0.6931471805599453);
        when(lnMock.calculate(10.0, EPS)).thenReturn(2.302585092994046);
        when(lnMock.calculate(3.0, EPS)).thenReturn(1.0986122886681098);

        double result = func.calculate(x, EPS);
        assertEquals(expected, result, Math.abs(expected * 0.01));
    }

    @ParameterizedTest
    @CsvSource({
            "1.6, 0.0961565621832193",
            "1.72601, 0.104760938342304",
            "0.6, -0.10877538354179246",
            "2.3,  -2.820226026841371",
            "2.0, -0.08537018545"
    })
    void calculateTestWithRealLn(double x, double expected) {
        SecondFunction func = new SecondFunction(
                realLog2, realLog10, realLn, realLog3
        );
        double result = func.calculate(x, EPS);
        assertEquals(expected, result, Math.abs(expected * 0.01));
    }


}