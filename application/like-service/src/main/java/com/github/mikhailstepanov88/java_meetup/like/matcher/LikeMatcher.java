package com.github.mikhailstepanov88.java_meetup.like.matcher;

import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.util.annotation.NonNull;

public interface LikeMatcher {
    /**
     * Verify that the request matched to the create like operation.
     *
     * @param request request for check.
     * @return the request matched to the create like operation or not.
     */
    boolean matchCreateLike(@NonNull ServerRequest request);

    /**
     * Verify that the request matched to read like by his identifier operation.
     *
     * @param request request for check.
     * @return the request matched to read like by his identifier operation or not.
     */
    boolean matchReadLikeById(@NonNull ServerRequest request);

    /**
     * Verify that the request matched to the read likes by idea identifier operation.
     *
     * @param request request for check.
     * @return the request matched to the read likes by idea identifier operation or not.
     */
    boolean matchReadLikesByIdeaId(@NonNull ServerRequest request);

    /**
     * Verify that the request matched to the delete like operation.
     *
     * @param request request for check.
     * @return the request matched to the delete like operation or not.
     */
    boolean matchDeleteLike(@NonNull ServerRequest request);
}