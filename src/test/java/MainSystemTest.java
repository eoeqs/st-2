import org.eoeqs.functions.basic.logarithmic.LnFunction;
import org.eoeqs.functions.basic.logarithmic.Log10Function;
import org.eoeqs.functions.basic.logarithmic.Log2Function;
import org.eoeqs.functions.basic.logarithmic.Log3Function;
import org.eoeqs.functions.basic.trigonometrical.CosFunction;
import org.eoeqs.functions.basic.trigonometrical.CscFunction;
import org.eoeqs.functions.basic.trigonometrical.SecFunction;
import org.eoeqs.functions.basic.trigonometrical.SinFunction;
import org.eoeqs.functions.system.FirstFunction;
import org.eoeqs.functions.system.MainSystem;
import org.eoeqs.functions.system.SecondFunction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class MainSystemTest {
    private final LnFunction ln = new LnFunction();
    private final CosFunction cos = new CosFunction();
    private final MainSystem system = new MainSystem(
            new FirstFunction(
                    new SecFunction(cos),
                    cos,
                    new CscFunction(new SinFunction(cos))
            ),
            new SecondFunction(
                    new Log2Function(ln),
                    new Log10Function(ln),
                    ln,
                    new Log3Function(ln)
            )
    );

    @ParameterizedTest
    @CsvSource({
            "1.6, 0.0961565621832193",
            "1.72601, 0.10476",
            "0.6, -0.10877538354179246",
            "2.3, -2.820226026841371",
            "-0.7, -0.02430552392309811",
            "-2.0, 13.024207016144441",
            "-2.3, 1.3031947338593135"
    })
    void fullIntegrationTest(double x, double expected) {
        double actual = system.calculate(x, 1e-8);
        assertEquals(expected, actual, Math.abs(expected * 0.01));
    }
}