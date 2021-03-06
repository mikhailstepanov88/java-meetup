package com.github.mikhailstepanov88.java_meetup.idea.matcher;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.util.annotation.NonNull;

@Component
public class DefaultMatcher implements Matcher {
    private final IdeaMatcher ideaMatcher;

    /**
     * Constructor.
     *
     * @param ideaMatcher matcher of idea requests.
     */
    public DefaultMatcher(@NonNull final IdeaMatcher ideaMatcher) {
        this.ideaMatcher = ideaMatcher;
    }

    /**
     * Verify that the request matched to the create idea operation.
     *
     * @param request request for check.
     * @return the request matched to the create idea operation or not.
     */
    @Override
    public boolean matchCreateIdea(@NonNull final ServerRequest request) {
        return ideaMatcher.matchCreateIdea(request);
    }

    /**
     * Verify that the request matched to the read idea by his identifier operation.
     *
     * @param request request for check.
     * @return the request matched to the read idea by his identifier operation or not.
     */
    @Override
    public boolean matchReadIdeaById(@NonNull final ServerRequest request) {
        return ideaMatcher.matchReadIdeaById(request);
    }

    /**
     * Verify that the request matched to the read ideas by pattern operation.
     *
     * @param request request for check.
     * @return the request matched to the read ideas by pattern operation or not.
     */
    @Override
    public boolean matchReadIdeasByPattern(@NonNull final ServerRequest request) {
        return ideaMatcher.matchReadIdeasByPattern(request);
    }

    /**
     * Verify that the request matched to the read ideas operation.
     *
     * @param request request for check.
     * @return the request matched to the read ideas operation or not.
     */
    @Override
    public boolean matchReadIdeas(@NonNull final ServerRequest request) {
        return ideaMatcher.matchReadIdeas(request);
    }
}