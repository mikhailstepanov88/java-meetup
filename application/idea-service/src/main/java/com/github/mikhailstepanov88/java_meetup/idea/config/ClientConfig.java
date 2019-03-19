package com.github.mikhailstepanov88.java_meetup.idea.config;

import com.github.mikhailstepanov88.java_meetup.like.client.DefaultPoetLikeService;
import com.github.mikhailstepanov88.java_meetup.like.client.SpringLikeServiceClient;
import com.github.mikhailstepanov88.java_meetup.like.client.LikeServiceClient;
import com.github.mikhailstepanov88.java_meetup.like.client.RetrofitLikeServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.annotation.NonNull;

@Configuration
public class ClientConfig {
    /**
     * Get client for working with likes based on Retrofit.
     *
     * @return client for working with likes based on Retrofit.
     */
    @Bean
    @NonNull
    @Primary
    public LikeServiceClient retrofitLikeServiceClient() {
        return new RetrofitLikeServiceClient("http://localhost:8081");
    }

    /**
     * Get client for working with likes based on Spring.
     *
     * @param webClientBuilder builder of web client.
     * @return client for working with likes based on Spring.
     */
    @Bean
    @NonNull
    public LikeServiceClient springLikeServiceClient(@NonNull WebClient.Builder webClientBuilder) {
        return new SpringLikeServiceClient("http://localhost:8081", webClientBuilder);
    }

    /**
     * Get client for working with likes based on JavaPoet.
     *
     * @param webClientBuilder builder of web client.
     * @return client for working with likes based on JavaPoet.
     */
    @Bean
    @NonNull
    public LikeServiceClient poetLikeServiceClient(@NonNull WebClient.Builder webClientBuilder) {
        return new DefaultPoetLikeService("http://localhost:8081", webClientBuilder);
    }
}