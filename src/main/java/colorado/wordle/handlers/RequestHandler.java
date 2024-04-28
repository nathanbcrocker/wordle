package colorado.wordle.handlers;

import colorado.wordle.Request;
import colorado.wordle.Response;
import colorado.wordle.WordleContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class RequestHandler {

    public void handle(WordleContext context, Request request, Response response) {
        log.info("{} is processing request", this.getClass().getSimpleName());
        process(context, request, response);
        if (this.next != null) {
            this.next.handle(context, request, response);
        }
    }

    public RequestHandler setNext(RequestHandler next) {
        this.next = next;
        return next;
    }

    protected abstract void process(WordleContext context, Request request, Response response);

    protected RequestHandler next;
}
