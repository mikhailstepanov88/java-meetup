package com.github.mikhailstepanov88.java_meetup.idea.matcher;

import org.springframework.web.reactive.function.server.ServerRequest;

public interface IdeaMatcher {
    /**
     * Verify that the request matched to the create idea operation.
     * @param request request for check.
     * @return the request matched to the create idea operation or not.
     */
    boolean matchCreateIdea(ServerRequest request);

    /**
     * Verify that the request matched to the read idea by his identifier operation.
     * @param request request for check.
     * @return the request matched to the read idea by his identifier operation or not.
     */
    boolean matchReadIdeaById(ServerRequest request);

    /**
     * Verify that the request matched to the read ideas by pattern operation.
     * @param request request for check.
     * @return the request matched to the read ideas by pattern operation or not.
     */
    boolean matchReadIdeasByPattern(ServerRequest request);

    /**
     * Verify that the request matched to the read ideas operation.
     * @param request request for check.
     * @return the request matched to the read ideas operation or not.
     */
    boolean matchReadIdeas(ServerRequest request);
}