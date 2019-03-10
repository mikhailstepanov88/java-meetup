package com.github.mikhailstepanov88.java_meetup.idea.config;

import com.github.mikhailstepanov88.java_meetup.idea.handler.Handler;
import com.github.mikhailstepanov88.java_meetup.idea.matcher.Matcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.util.annotation.NonNull;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutingConfig {
    /**
     * Get function for routing.
     *
     * @param matcher matcher of all operations.
     * @param handler handler of all operations.
     * @return function for routing.
     */
    @Bean
    public RouterFunction<ServerResponse> routerFunction(@NonNull final Matcher matcher,
                                                         @NonNull final Handler handler) {
        return route(matcher::matchCreateIdea, handler::handleCreateIdea)
                .andRoute(matcher::matchReadIdeaById, handler::handleReadIdeaById)
                .andRoute(matcher::matchReadIdeasByPattern, handler::handleReadIdeasByPattern)
                .andRoute(matcher::matchReadIdeas, handler::handleReadIdeas);
    }
}