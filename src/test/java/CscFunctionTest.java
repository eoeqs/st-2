import org.eoeqs.functions.basic.trigonometrical.CscFunction;
import org.eoeqs.functions.basic.trigonometrical.SinFunction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CscFunctionTest {

    @Test
    void cscCalculation() {
        SinFunction sinMock = mock(SinFunction.class);
        when(sinMock.calculate(Math.PI/2, 1e-6)).thenReturn(1.0);

        CscFunction csc = new CscFunction(sinMock);
        double result = csc.calculate(Math.PI/2, 1e-6);

        assertEquals(1.0, result, 1e-6);
    }

    @Test
    void cscThrowsAtPi() {
        SinFunction sinMock = mock(SinFunction.class);
        when(sinMock.calculate(Math.PI, 1e-6)).thenReturn(0.0);

        CscFunction csc = new CscFunction(sinMock);
        assertThrows(IllegalArgumentException.class, () -> csc.calculate(Math.PI, 1e-6));
    }
}