package com.github.mikhailstepanov88.java_meetup.like.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

@Component
public class DefaultHandler implements Handler {
    private final LikeHandler likeHandler;

    /**
     * Constructor.
     *
     * @param likeHandler handler of likes.
     */
    public DefaultHandler(@NonNull final LikeHandler likeHandler) {
        this.likeHandler = likeHandler;
    }

    /**
     * Handle create like operation.
     *
     * @param request request for handle.
     * @return response of create like operation.
     */
    @Override
    public Mono<ServerResponse> handleCreateLike(ServerRequest request) {
        return likeHandler.handleCreateLike(request);
    }

    /**
     * Handle read like by his identifier operation.
     *
     * @param request request for handle.
     * @return response of read like by his identifier operation.
     */
    @Override
    public Mono<ServerResponse> handleReadLikeById(ServerRequest request) {
        return likeHandler.handleReadLikeById(request);
    }

    /**
     * Handle read likes by idea identifier operation.
     *
     * @param request request for handle.
     * @return response of read likes by idea identifier operation.
     */
    @Override
    public Mono<ServerResponse> handleReadLikesByIdeaId(ServerRequest request) {
        return likeHandler.handleReadLikesByIdeaId(request);
    }

    /**
     * Handle delete like operation.
     *
     * @param request request for handle.
     * @return response of delete like operation.
     */
    @Override
    public Mono<ServerResponse> handleDeleteLike(ServerRequest request) {
        return likeHandler.handleDeleteLike(request);
    }
}