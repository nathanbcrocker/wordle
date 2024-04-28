package colorado.wordle.handlers;

import colorado.wordle.Request;

public class RequestHandlerFactory {
    public static RequestHandler createHandler(Request request) {
        RequestHandler root = new StarterWordsHandler();
        RequestHandler next = request.getSequence() != null && !request.getSequence().isEmpty() ? new SequenceHandler() : new PatternHandler();
        root
                .setNext(next)
                .setNext(new ExclusiveFilterHandler())
                .setNext(new InclusiveFilterHandler())
                .setNext(new ProbabilitySortHandler())
                .setNext(new RemoveRecentWordsHandler())
        ;
        return root;
    }
}
