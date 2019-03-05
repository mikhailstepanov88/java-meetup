package com.github.mikhailstepanov88.java_meetup.idea.matcher;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Primary
@Component
public class DefaultIdeaMatcher implements IdeaMatcher {
    /**
     * Verify that the request matched to the create idea operation.
     *
     * @param request request for check.
     * @return the request matched to the create idea operation or not.
     */
    @Override
    public boolean matchCreateIdea(ServerRequest request) {
        return POST("/idea")
                .and(accept(APPLICATION_JSON_UTF8))
                .and(contentType(APPLICATION_JSON_UTF8))
                .test(request);
    }

    /**
     * Verify that the request matched to the read idea by his identifier operation.
     *
     * @param request request for check.
     * @return the request matched to the read idea by his identifier operation or not.
     */
    @Override
    public boolean matchReadIdeaById(ServerRequest request) {
        return GET("/idea/{idea_id}")
                .and(accept(APPLICATION_JSON_UTF8))
                .and(contentType(APPLICATION_JSON_UTF8))
                .test(request);
    }

    /**
     * Verify that the request matched to the read ideas by pattern operation.
     *
     * @param request request for check.
     * @return the request matched to the read ideas by pattern operation or not.
     */
    @Override
    public boolean matchReadIdeasByPattern(ServerRequest request) {
        return GET("/idea")
                .and(queryParam("pattern", Objects::nonNull))
                .and(accept(APPLICATION_JSON_UTF8))
                .and(contentType(APPLICATION_JSON_UTF8))
                .test(request);
    }

    /**
     * Verify that the request matched to the read ideas operation.
     *
     * @param request request for check.
     * @return the request matched to the read ideas operation or not.
     */
    @Override
    public boolean matchReadIdeas(ServerRequest request) {
        return GET("/idea")
                .and(queryParam("pattern", Objects::isNull))
                .and(accept(APPLICATION_JSON_UTF8))
                .and(contentType(APPLICATION_JSON_UTF8))
                .test(request);
    }
}