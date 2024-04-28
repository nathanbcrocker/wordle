package colorado.wordle;

import colorado.wordle.collections.Trie;
import lombok.Data;

import java.util.List;

@Data
public final class WordleContext {
    private Trie trie;
    private List<String> recentWords;
    private double[][] letterProbabilities;
}
