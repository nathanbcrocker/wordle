package colorado.wordle.handlers;

import colorado.wordle.Request;
import colorado.wordle.Response;
import colorado.wordle.WordleContext;
import java.util.List;
import java.util.function.Function;

public abstract class TrieHandler extends RequestHandler {
    @Override
    protected void process(WordleContext context, Request request, Response response) {
        response.setWords(function(context).apply(operator(request)));
    }

    protected abstract String operator(Request request);
    protected abstract Function<String, List<String>> function(WordleContext context);
}
