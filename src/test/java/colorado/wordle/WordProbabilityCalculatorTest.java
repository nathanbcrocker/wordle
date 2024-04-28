package colorado.wordle;

import colorado.wordle.calculators.WordProbabilityCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordProbabilityCalculatorTest {

    @Test
    void calculate() {
        double[][] letterProbabilities = {
                {0.1, 0.2, 0.1, 0.3, 0.3},
                {0.2, 0.1, 0.4, 0.1, 0.2},
                {0.3, 0.4, 0.1, 0.1, 0.1},
        };

        var calculator = new WordProbabilityCalculator();
        assertEquals(0.00432, calculator.calculate("CCBAA", letterProbabilities), 0.0001);
        assertEquals(0.0012, calculator.calculate("ABBA", letterProbabilities), 0.0001);
        assertEquals(0.1, calculator.calculate("A", letterProbabilities), 0.0001);
        assertEquals(0.001, calculator.calculate("ABC", letterProbabilities), 0.0001);
    }
}