package com.github.mikhailstepanov88.java_meetup.like.matcher;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.util.annotation.NonNull;

@Component
public class DefaultMatcher implements Matcher {
    private final LikeMatcher likeMatcher;

    /**
     * Constructor.
     *
     * @param likeMatcher matcher of like requests.
     */
    public DefaultMatcher(@NonNull final LikeMatcher likeMatcher) {
        this.likeMatcher = likeMatcher;
    }

    /**
     * Verify that the request matched to the create like operation.
     *
     * @param request request for check.
     * @return the request matched to the create like operation or not.
     */
    @Override
    public boolean matchCreateLike(@NonNull final ServerRequest request) {
        return likeMatcher.matchCreateLike(request);
    }

    /**
     * Verify that the request matched to read like by his identifier operation.
     *
     * @param request request for check.
     * @return the request matched to read like by his identifier operation or not.
     */
    @Override
    public boolean matchReadLikeById(@NonNull final ServerRequest request) {
        return likeMatcher.matchReadLikeById(request);
    }

    /**
     * Verify that the request matched to the read likes by idea identifier operation.
     *
     * @param request request for check.
     * @return the request matched to the read likes by idea identifier operation or not.
     */
    @Override
    public boolean matchReadLikesByIdeaId(@NonNull final ServerRequest request) {
        return likeMatcher.matchReadLikesByIdeaId(request);
    }

    /**
     * Verify that the request matched to the delete like operation.
     *
     * @param request request for check.
     * @return the request matched to the delete like operation or not.
     */
    @Override
    public boolean matchDeleteLike(@NonNull final ServerRequest request) {
        return likeMatcher.matchDeleteLike(request);
    }
}