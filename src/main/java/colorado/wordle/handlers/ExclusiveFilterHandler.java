package colorado.wordle.handlers;

import colorado.wordle.Request;
import colorado.wordle.Response;
import colorado.wordle.WordleContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExclusiveFilterHandler extends FilterHandler{
    @Override
    protected void process(WordleContext context, Request request, Response response) {
        if (request.getExclude() != null && !request.getExclude().isEmpty()) {
            log.info("Excluding words containing: {}", request.getExclude());
            response.setWords(filter(request.getExclude(), response, String::contains));
        }
    }
}
