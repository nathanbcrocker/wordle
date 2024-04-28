package colorado.wordle.handlers;

import colorado.wordle.WordleContext;
import colorado.wordle.Request;
import colorado.wordle.Response;
import colorado.wordle.calculators.WordProbabilityCalculator;

import java.util.Collections;

public class ProbabilitySortHandler extends RequestHandler {
    @Override
    protected void process(WordleContext context, Request request, Response response) {
        Collections.sort(response.getWords(), (w1, w2) -> Double.compare(
                calculateWordProbability(w2, context.getLetterProbabilities()),
                calculateWordProbability(w1, context.getLetterProbabilities())
        ));
    }

    private double calculateWordProbability(String word, double[][] letterProbabilities) {
        return calc.calculate(word, letterProbabilities);
    }

    private final WordProbabilityCalculator calc = new WordProbabilityCalculator();
}
