package com.github.mikhailstepanov88.java_meetup.idea.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

@Component
public class DefaultHandler implements Handler {
    private final DefaultIdeaHandler ideaHandler;

    /**
     * Constructor.
     *
     * @param ideaHandler handler of ideas.
     */
    public DefaultHandler(@NonNull final DefaultIdeaHandler ideaHandler) {
        this.ideaHandler = ideaHandler;
    }

    /**
     * Handle create idea operation.
     *
     * @param request request for handle.
     * @return response of create idea operation.
     */
    @NonNull
    @Override
    public Mono<ServerResponse> handleCreateIdea(@NonNull final ServerRequest request) {
        return ideaHandler.handleCreateIdea(request);
    }

    /**
     * Handle read idea by his identifier operation.
     *
     * @param request request for handle.
     * @return response of read idea by his identifier operation.
     */
    @NonNull
    @Override
    public Mono<ServerResponse> handleReadIdeaById(@NonNull final ServerRequest request) {
        return ideaHandler.handleReadIdeaById(request);
    }

    /**
     * Handle read ideas by pattern operation.
     *
     * @param request request for handle.
     * @return response of read ideas by pattern operation.
     */
    @NonNull
    @Override
    public Mono<ServerResponse> handleReadIdeasByPattern(@NonNull final ServerRequest request) {
        return ideaHandler.handleReadIdeasByPattern(request);
    }

    /**
     * Handle read ideas operation.
     *
     * @param request request for handle.
     * @return response of read ideas operation.
     */
    @NonNull
    @Override
    public Mono<ServerResponse> handleReadIdeas(@NonNull final ServerRequest request) {
        return ideaHandler.handleReadIdeas(request);
    }
}