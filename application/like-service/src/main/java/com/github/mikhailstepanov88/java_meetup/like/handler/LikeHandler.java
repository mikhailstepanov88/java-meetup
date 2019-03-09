package com.github.mikhailstepanov88.java_meetup.like.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

public interface LikeHandler {
    /**
     * Handle create like operation.
     *
     * @param request request for handle.
     * @return response of create like operation.
     */
    @NonNull
    Mono<ServerResponse> handleCreateLike(@NonNull ServerRequest request);

    /**
     * Handle read like by his identifier operation.
     *
     * @param request request for handle.
     * @return response of read like by his identifier operation.
     */
    @NonNull
    Mono<ServerResponse> handleReadLikeById(@NonNull ServerRequest request);

    /**
     * Handle read likes by idea identifier operation.
     *
     * @param request request for handle.
     * @return response of read likes by idea identifier operation.
     */
    @NonNull
    Mono<ServerResponse> handleReadLikesByIdeaId(@NonNull ServerRequest request);

    /**
     * Handle delete like operation.
     *
     * @param request request for handle.
     * @return response of delete like operation.
     */
    @NonNull
    Mono<ServerResponse> handleDeleteLike(@NonNull ServerRequest request);
}