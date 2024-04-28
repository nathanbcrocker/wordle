package colorado.wordle.handlers;

import colorado.wordle.Request;
import colorado.wordle.WordleContext;

import java.util.List;
import java.util.function.Function;

public class PatternHandler extends TrieHandler{
    @Override
    protected String operator(Request request) {
        return request.getPattern();
    }

    @Override
    protected Function<String, List<String>> function(WordleContext context) {
        return context.getTrie()::searchPattern;
    }
}
