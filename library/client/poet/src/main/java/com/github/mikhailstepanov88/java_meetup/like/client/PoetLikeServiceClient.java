package com.github.mikhailstepanov88.java_meetup.like.client;

import com.github.mikhailstepanov88.java_meetup.like.data.dto.LikeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

public class PoetLikeServiceClient implements LikeServiceClient {
    private final WebClient webClient;

    /**
     * Constructor.
     *
     * @param webClient http client.
     */
    public PoetLikeServiceClient(@NonNull final WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Create like.
     *
     * @param ideaId identifier of idea.
     * @param like   like for create.
     * @return created like.
     */
    @NonNull
    @Override
    public Mono<LikeDTO> createLike(@NonNull final String ideaId,
                                    @NonNull final LikeDTO like) {
        return webClient.post()
                .uri("/idea/{idea_id}/like", ideaId)
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8)
                .syncBody(like)
                .retrieve()
                .bodyToMono(LikeDTO.class);
    }

    /**
     * Read like by his identifier.
     *
     * @param ideaId identifier of idea for search.
     * @param likeId identifier of like for search.
     * @return like by his identifier.
     */
    @NonNull
    @Override
    public Mono<LikeDTO> readLikeById(@NonNull final String ideaId,
                                      @NonNull final String likeId) {
        return webClient.get()
                .uri("/idea/{idea_id}/like/{like_id}", ideaId, likeId)
                .accept(APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToMono(LikeDTO.class);
    }

    /**
     * Read likes by identifier of idea.
     *
     * @param ideaId identifier of idea for search.
     * @return likes by identifier of idea.
     */
    @NonNull
    @Override
    public Flux<LikeDTO> readLikesByIdeaId(@NonNull final String ideaId) {
        return webClient.get()
                .uri("/idea/{idea_id}/like", ideaId)
                .accept(APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToFlux(LikeDTO.class);
    }

    /**
     * Delete like by his identifier.
     *
     * @param ideaId identifier of idea for delete.
     * @param likeId identifier of like for delete.
     * @return operation complete successfully or not.
     */
    @NonNull
    @Override
    public Mono<Boolean> deleteLike(@NonNull final String ideaId,
                                    @NonNull final String likeId) {
        return webClient.delete()
                .uri("/idea/{idea_id}/like/{like_id}", ideaId, likeId)
                .accept(APPLICATION_JSON_UTF8)
                .exchange()
                .map(response -> response.statusCode().equals(HttpStatus.NO_CONTENT));
    }
}