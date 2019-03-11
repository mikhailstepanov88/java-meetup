package com.github.mikhailstepanov88.java_meetup.idea.config;

import com.github.mikhailstepanov88.java_meetup.like.client.LikeServiceClient;
import com.github.mikhailstepanov88.java_meetup.like.client.PoetLikeServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.annotation.NonNull;

@Configuration
public class ClientConfig {
    /**
     * Get client for working with likes.
     *
     * @param webClientBuilder builder of web client.
     * @return client for working with likes.
     */
    @Bean
    @NonNull
    public LikeServiceClient likeServiceClient(@NonNull WebClient.Builder webClientBuilder) {
        return new PoetLikeServiceClient("http://localhost:8081", webClientBuilder);
    }
}