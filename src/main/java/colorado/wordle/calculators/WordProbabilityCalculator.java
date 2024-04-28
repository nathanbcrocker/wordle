package colorado.wordle.calculators;

public class WordProbabilityCalculator {

    public double calculate(String word, double[][] letterProbabilities) {
        word = word.toUpperCase();
        double probability = 1.0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            probability *= letterProbabilities[c - 'A'][i];
        }

        return probability;
    }
}
