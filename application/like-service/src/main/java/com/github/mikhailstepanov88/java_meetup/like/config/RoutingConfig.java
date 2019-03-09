package com.github.mikhailstepanov88.java_meetup.like.config;

import com.github.mikhailstepanov88.java_meetup.like.handler.Handler;
import com.github.mikhailstepanov88.java_meetup.like.matcher.Matcher;
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
        return route(matcher::matchCreateLike, handler::handleCreateLike)
                .andRoute(matcher::matchReadLikeById, handler::handleReadLikeById)
                .andRoute(matcher::matchReadLikesByIdeaId, handler::handleReadLikesByIdeaId)
                .andRoute(matcher::matchDeleteLike, handler::handleDeleteLike);
    }
}