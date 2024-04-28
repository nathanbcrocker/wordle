package colorado.wordle.components;

import colorado.wordle.Response;
import colorado.wordle.WordleContext;
import colorado.wordle.Request;
import colorado.wordle.handlers.RequestHandlerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import java.util.List;

@ShellComponent
@Slf4j
public class WordleShellComponent {

    @Autowired
    public WordleShellComponent(WordleContext context) {
        this.context = context;
    }

    @ShellMethod(key = "wordle", value = "Assist with a wordle")
    public List<String> wordle(@ShellOption(defaultValue = "") String pattern,
                               @ShellOption(defaultValue = "") String sequence,
                               @ShellOption(defaultValue = "") String exclude,
                               @ShellOption(defaultValue = "") String include,
                               @ShellOption(defaultValue = "false", help = "returns a list of good starter words") String starters,
                               @ShellOption(defaultValue = "false", help = "removes recent wordle words from the output") String recent) {

        var request = new Request();
        request.setStarters(starters.equalsIgnoreCase("true"));
        request.setRecent(recent.equalsIgnoreCase("true"));
        request.setPattern(pattern.toUpperCase());
        request.setSequence(sequence.toUpperCase());
        request.setInclude(include.toUpperCase());
        request.setExclude(exclude.toUpperCase());

        var handler = RequestHandlerFactory.createHandler(request);
        var response = new Response();
        handler.handle(context, request, response);
        return response.getWords();

        //return RequestHandlerFactory.createHandler(request).handle(context, request, new Response());

//        if ((pattern == null || pattern.isEmpty()) && (sequence == null || sequence.isEmpty())) {
//            return null;
//        }
//
//        if (sequence != null && !sequence.isEmpty()) {
//            return transform(wordList::contains, sequence.toUpperCase(), without.toUpperCase(), with.toUpperCase(), recentFlag);
//        }
//
//        if (pattern.length() < 5) {
//            return null;
//        }
//
//        return transform(wordList::searchPattern, pattern.toUpperCase(), without.toUpperCase(), with.toUpperCase(), recentFlag);
    }

//    private List<String> transform(Function<String, List<String>> func, String op, String without, String with, boolean recent) {
//        List<String> results = new ArrayList<>(func.apply(op));
//        results = filter(results, without, String::contains);
//        results = filter(results, with, (word, withChar) -> !word.contains(withChar));
//        if (recent) {
//            results.removeAll(recentWords);
//        }
//
//        Collections.sort(results, (w1, w2) -> Double.compare(
//                calculateWordProbability(w2, letterProbabilities),
//                calculateWordProbability(w1, letterProbabilities)
//        ));
//
//        return results;
//    }
//
//    private double calculateWordProbability(String word, double[][] letterProbabilities) {
//        return wordProbabilityCalculator.calculate(word, letterProbabilities);
//    }
//
//    private List<String> filter(List<String> words, String testWord, BiPredicate<String, String> predicate) {
//        List<String> results = new ArrayList<>();
//        for (String word : words) {
//            boolean found = true;
//            for (int i = 0; i < testWord.length(); i++) {
//                if (predicate.test(word, String.valueOf(testWord.charAt(i)))) {
//                    //log.info("Filtered out {}: due to letter {}", word, with.charAt(i));
//                    found = false;
//                    break;
//                }
//            }
//
//            if (found) {
//                results.add(word);
//            }
//        }
//
//        return results;
//    }

    private final WordleContext context;
}