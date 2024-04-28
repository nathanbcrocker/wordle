package colorado.wordle;

import colorado.wordle.calculators.LetterProbabilityCalculator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LetterProbabilityCalculatorTest {
    @Test
    void testCalculate() {
        var words = List.of("SLATE", "TRACE", "CARTE", "SLANT", "CRATE", "CRANE", "LEAST", "CARET", "STALE");
        var calculator = new LetterProbabilityCalculator();
        var probabilities = calculator.calculate(words);
        assertEquals(26, probabilities.length);
        assertEquals(5, probabilities[0].length);
        assertEquals(0.8, probabilities[2][0]);
        assertEquals(0.0, probabilities[2][2]);
        assertEquals(0.2, probabilities[2][3]);
    }
}