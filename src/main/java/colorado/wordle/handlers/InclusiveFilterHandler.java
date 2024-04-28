package colorado.wordle.handlers;

import colorado.wordle.Request;
import colorado.wordle.Response;
import colorado.wordle.WordleContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InclusiveFilterHandler extends FilterHandler{
    @Override
    protected void process(WordleContext context, Request request, Response response) {
        if (request.getInclude() != null && !request.getInclude().isEmpty()) {
            log.info("Including words containing: {}", request.getInclude());
            response.setWords(filter(request.getInclude(), response, (word, with) -> !word.contains(with)));
        }
    }
}
