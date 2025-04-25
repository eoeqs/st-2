import org.eoeqs.functions.basic.trigonometrical.CosFunction;
import org.eoeqs.functions.basic.trigonometrical.CscFunction;
import org.eoeqs.functions.basic.trigonometrical.SecFunction;
import org.eoeqs.functions.basic.trigonometrical.SinFunction;
import org.eoeqs.functions.system.FirstFunction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FirstFunctionTest {
    private static final double EPS = 1e-5;

    @ParameterizedTest
    @CsvSource({
            "-0.7,      1.30745925973,        0.7648421872844885,        -1.552270326957104,        -0.02430560446184",
            "-2.0,      -2.4029979617,        -0.4161468365471424,       -1.0997501702946164,       13.0241998222133",
            "-2.3,      -1.5008794674,        -0.66627602127982416,      -1.3410124854578809,       1.3031985097944279",
            "-1.047197551, 2.0,               0.5,                       -1.1547005385103166,       -6.825118047357264"
    })
    void firstFunctionValuesWithMock(
            double x, double secVal, double cosVal, double cscVal, double expected
    ) {
        SecFunction sec = mock(SecFunction.class);
        CosFunction cos = mock(CosFunction.class);
        CscFunction csc = mock(CscFunction.class);

        when(sec.calculate(x, EPS)).thenReturn(secVal);
        when(cos.calculate(x, EPS)).thenReturn(cosVal);
        when(csc.calculate(x, EPS)).thenReturn(cscVal);

        FirstFunction func = new FirstFunction(sec, cos, csc);
        assertEquals(expected, func.calculate(x, EPS), Math.abs(expected * 0.01));
    }

    @ParameterizedTest
    @CsvSource({
            "-0.7,      1.30745925973,        0.7648421872844885,        -1.552270326957104,        -0.02430560446184",
            "-2.0,      -2.4029979617,        -0.4161468365471424,       -1.0997501702946164,       13.0241998222133",
            "-2.3,      -1.5008794674,        -0.66627602127982416,      -1.3410124854578809,       1.3031985097944279",
            "-1.047197551, 2.0,               0.5,                       -1.1547005385103166,       -6.825118047357264"
    })
    void firstFunctionValuesWithRealSec(
            double x, double secVal, double cosVal, double cscVal, double expected
    ) {
        CosFunction cos = mock(CosFunction.class);
        CscFunction csc = mock(CscFunction.class);
        SecFunction sec = new SecFunction(cos);

        when(cos.calculate(x, EPS)).thenReturn(cosVal);
        when(csc.calculate(x, EPS)).thenReturn(cscVal);

        FirstFunction func = new FirstFunction(sec, cos, csc);
        assertEquals(expected, func.calculate(x, EPS), Math.abs(expected * 0.01));
    }


    @ParameterizedTest
    @CsvSource({
            "-0.7,      1.30745925973,        0.7648421872844885,        -1.552270326957104,        -0.02430560446184",
            "-2.0,      -2.4029979617,        -0.4161468365471424,       -1.0997501702946164,       13.0241998222133",
            "-2.3,      -1.5008794674,        -0.66627602127982416,      -1.3410124854578809,       1.3031985097944279",
            "-1.047197551, 2.0,               0.5,                       -1.1547005385103166,       -6.825118047357264"
    })
    void firstFunctionValuesWithRealCos(
            double x, double secVal, double cosVal, double cscVal, double expected
    ) {
        CscFunction csc = mock(CscFunction.class);
        CosFunction cos = new CosFunction();
        SecFunction sec = new SecFunction(cos);

        when(csc.calculate(x, EPS)).thenReturn(cscVal);

        FirstFunction func = new FirstFunction(sec, cos, csc);
        assertEquals(expected, func.calculate(x, EPS), Math.abs(expected * 0.01));
    }

    @ParameterizedTest
    @CsvSource({
            "-0.7,        -0.644217687237691,   -1.552270326957104",
            "-2.0,        -0.9092974268256817,  -1.0997501702946164",
            "-2.3,        -0.7456241416655576,  -1.3410124854578809",
            "-1.047197551, -0.8660254037844386,  -1.1547005385103166"
    })
    void cscFunctionValuesWithMockSin(double x, double sinVal, double expectedCsc) {
        SinFunction sin = mock(SinFunction.class);
        when(sin.calculate(x, EPS)).thenReturn(sinVal);
        CscFunction csc = new CscFunction(sin);
        assertEquals(expectedCsc, csc.calculate(x, EPS), Math.abs(expectedCsc * 0.01));
    }

    @ParameterizedTest
    @CsvSource({
            "-0.7,        -0.644217687237691",
            "-2.0,        -0.9092974268256817",
            "-2.3,        -0.7456241416655576",
            "-1.047197551, -0.8660254037844386"
    })
    void sinFunctionValuesWithMockCos(double x, double expectedSin) {
        CosFunction cos = mock(CosFunction.class);
        double arg = Math.PI / 2 - x;
        when(cos.calculate(arg, EPS)).thenReturn(expectedSin);
        SinFunction sin = new SinFunction(cos);
        assertEquals(expectedSin, sin.calculate(x, EPS), Math.abs(expectedSin * 0.01));
    }

}