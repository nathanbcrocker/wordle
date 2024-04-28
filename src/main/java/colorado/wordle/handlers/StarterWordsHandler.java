package colorado.wordle.handlers;

import colorado.wordle.Request;
import colorado.wordle.Response;
import colorado.wordle.WordleContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StarterWordsHandler extends RequestHandler {
    private final List<String> starterWordList = List.of("SLATE", "TRACE", "CARTE", "SLANT", "CRATE", "CRANE", "LEAST", "CARET", "STALE");

    @Override
    public void handle(WordleContext context,Request request, Response response) {
        if (request.isStarters()) {
            response.setWords(starterWordList);
        } else {
            next.handle(context, request, response);
        }
    }

    @Override
    protected void process(WordleContext context, Request request, Response response) {

    }
}
