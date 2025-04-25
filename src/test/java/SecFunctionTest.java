import org.eoeqs.functions.basic.trigonometrical.CosFunction;
import org.eoeqs.functions.basic.trigonometrical.SecFunction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SecFunctionTest {

    @Test
    void secCalculation() {
        CosFunction cosMock = mock(CosFunction.class);
        when(cosMock.calculate(0.0, 1e-6)).thenReturn(1.0);

        SecFunction sec = new SecFunction(cosMock);
        double result = sec.calculate(0.0, 1e-6);

        assertEquals(1.0, result, 1e-6);
    }

    @Test
    void secThrowsAtPiOver2() {
        CosFunction cosMock = mock(CosFunction.class);
        when(cosMock.calculate(Math.PI/2, 1e-6)).thenReturn(0.0);

        SecFunction sec = new SecFunction(cosMock);
        assertThrows(IllegalArgumentException.class, () -> sec.calculate(Math.PI/2, 1e-6));
    }
}