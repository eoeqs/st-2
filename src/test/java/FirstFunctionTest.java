import org.eoeqs.functions.basic.trigonometrical.CosFunction;
import org.eoeqs.functions.basic.trigonometrical.CscFunction;
import org.eoeqs.functions.basic.trigonometrical.SecFunction;
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
    void firstFunctionValues(
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
}