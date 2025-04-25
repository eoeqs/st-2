import org.eoeqs.functions.basic.logarithmic.LnFunction;
import org.eoeqs.functions.basic.logarithmic.Log2Function;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Log2FunctionTest {

    @Test
    void log2Calculation() {
        LnFunction lnMock = mock(LnFunction.class);
        when(lnMock.calculate(8.0, 1e-6)).thenReturn(2.07944154);
        when(lnMock.calculate(2.0, 1e-6)).thenReturn(0.69314718);

        Log2Function log2 = new Log2Function(lnMock);
        double result = log2.calculate(8.0, 1e-6);

        assertEquals(3.0, result, 1e-6);
    }
}