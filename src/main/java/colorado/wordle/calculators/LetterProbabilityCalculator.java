package colorado.wordle.calculators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterProbabilityCalculator {

    private static final int POSITION_COUNT = 5;
    public double[][] calculate(List<String> words) {
        Map<Character, int[]> letterFrequencies = new HashMap<>();
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            letterFrequencies.put(ch, new int[POSITION_COUNT]);
        }

        for (String word : words) {
            word = word.toUpperCase();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (letterFrequencies.containsKey(c)) {
                    letterFrequencies.get(c)[i]++;
                }
            }
        }

        return letterFrequencies.entrySet().stream()
                .map(entry -> {
                    int[] frequencies = entry.getValue();
                    double[] probabilities = new double[POSITION_COUNT];
                    int total = 0;
                    for (int frequency : frequencies) {
                        total += frequency;
                    }
                    for (int i = 0; i < POSITION_COUNT; i++) {
                        probabilities[i] = (double) frequencies[i] / total;
                    }
                    return probabilities;
                })
                .toList()
                .toArray(new double[0][0]);
    }
}
