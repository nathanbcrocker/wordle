package colorado.wordle.handlers;

import colorado.wordle.Response;
//import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

//@Slf4j
public abstract class FilterHandler extends RequestHandler {
    protected List<String> filter(String filterChars, Response response, BiPredicate<String, String> predicate) {
        List<String> results = new ArrayList<>();
        for (String word : response.getWords()) {
            boolean found = true;
            for (int i = 0; i < filterChars.length(); i++) {
                if (predicate.test(word, String.valueOf(filterChars.charAt(i)))) {
                    //log.info("Filtered out {}: due to letter {}", word, filterChars.charAt(i));
                    found = false;
                    break;
                }
            }

            if (found) {
                results.add(word);
            }
        }

        return results;
    }
}
