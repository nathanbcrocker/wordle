package colorado.wordle.handlers;

import colorado.wordle.Request;
import colorado.wordle.Response;
import colorado.wordle.WordleContext;

public class RemoveRecentWordsHandler extends RequestHandler{
    @Override
    protected void process(WordleContext context, Request request, Response response) {
        if (request.isRecent()) {
            response.getWords().removeAll(context.getRecentWords());
        }
    }
}
