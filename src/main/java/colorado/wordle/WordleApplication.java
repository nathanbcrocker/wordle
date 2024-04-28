package colorado.wordle;

import colorado.wordle.calculators.LetterProbabilityCalculator;
import colorado.wordle.collections.Trie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootApplication
@Slf4j
public class WordleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordleApplication.class, args);
	}

    @Bean
    public Trie wordList() {
        return fromFile(Trie::new, "/five-letter-words.txt", Trie::insert);
    }

    @Bean
    public List<String> recentWords() {
        return fromFile(ArrayList::new, "/recent-words.txt", (ls, s) -> {
            Collections.addAll(ls, s.split(" "));
        });
    }

    @Bean
    public WordleContext context(List<String> recentWords, Trie wordList) {
        var ctx = new WordleContext();
        ctx.setTrie(wordList());
        ctx.setRecentWords(recentWords);
        ctx.setLetterProbabilities(new LetterProbabilityCalculator().calculate(recentWords));
        return ctx;
    }

    private <T> T fromFile(Supplier<T> supplier, String fileName, BiConsumer<T, String> processor) {
        var t = supplier.get();
        try (var reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(fileName))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processor.accept(t, line.toUpperCase());
            }
        } catch (IOException e) {
            log.error("Error reading file", e);
        }

        return t;
    }
}
