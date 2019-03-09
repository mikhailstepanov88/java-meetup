package com.github.mikhailstepanov88.java_meetup.like.matcher;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.util.annotation.NonNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Primary
@Component
public class DefaultLikeMatcher implements LikeMatcher {
    /**
     * Verify that the request matched to the create like operation.
     *
     * @param request request for check.
     * @return the request matched to the create like operation or not.
     */
    @Override
    public boolean matchCreateLike(@NonNull final ServerRequest request) {
        return POST("/idea/{idea_id}/like")
                .and(accept(APPLICATION_JSON_UTF8))
                .and(contentType(APPLICATION_JSON_UTF8))
                .test(request);
    }

    /**
     * Verify that the request matched to read like by his identifier operation.
     *
     * @param request request for check.
     * @return the request matched to read like by his identifier operation or not.
     */
    @Override
    public boolean matchReadLikeById(@NonNull final ServerRequest request) {
        return GET("/idea/{idea_id}/like/{like_id}")
                .and(accept(APPLICATION_JSON_UTF8))
                .and(contentType(APPLICATION_JSON_UTF8))
                .test(request);
    }

    /**
     * Verify that the request matched to the read likes by idea identifier operation.
     *
     * @param request request for check.
     * @return the request matched to the read likes by idea identifier operation or not.
     */
    @Override
    public boolean matchReadLikesByIdeaId(@NonNull final ServerRequest request) {
        return GET("/idea/{idea_id}/like")
                .and(accept(APPLICATION_JSON_UTF8))
                .and(contentType(APPLICATION_JSON_UTF8))
                .test(request);
    }

    /**
     * Verify that the request matched to the delete like operation.
     *
     * @param request request for check.
     * @return the request matched to the delete like operation or not.
     */
    @Override
    public boolean matchDeleteLike(@NonNull final ServerRequest request) {
        return DELETE("/idea/{idea_id}/like/{like_id}")
                .and(accept(APPLICATION_JSON_UTF8))
                .and(contentType(APPLICATION_JSON_UTF8))
                .test(request);
    }
}