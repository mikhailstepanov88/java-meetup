package com.github.mikhailstepanov88.java_meetup.idea.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

public interface IdeaHandler {
    /**
     * Handle create idea operation.
     *
     * @param request request for handle.
     * @return response of create idea operation.
     */
    @NonNull
    Mono<ServerResponse> handleCreateIdea(@NonNull ServerRequest request);

    /**
     * Handle read idea by his identifier operation.
     *
     * @param request request for handle.
     * @return response of read idea by his identifier operation.
     */
    @NonNull
    Mono<ServerResponse> handleReadIdeaById(@NonNull ServerRequest request);

    /**
     * Handle read ideas by pattern operation.
     *
     * @param request request for handle.
     * @return response of read ideas by pattern operation.
     */
    @NonNull
    Mono<ServerResponse> handleReadIdeasByPattern(@NonNull ServerRequest request);

    /**
     * Handle read ideas operation.
     *
     * @param request request for handle.
     * @return response of read ideas operation.
     */
    @NonNull
    Mono<ServerResponse> handleReadIdeas(@NonNull ServerRequest request);
}