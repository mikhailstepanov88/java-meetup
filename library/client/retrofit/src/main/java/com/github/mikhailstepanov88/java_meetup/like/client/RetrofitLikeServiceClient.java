package com.github.mikhailstepanov88.java_meetup.like.client;

import com.github.mikhailstepanov88.java_meetup.like.data.dto.LikeDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class RetrofitLikeServiceClient implements LikeServiceClient {
    private final RetrofitLikeServiceApi api;

    /**
     * Constructor.
     *
     * @param baseUrl base url of like service.
     */
    public RetrofitLikeServiceClient(@NonNull final String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        this.api = retrofit.create(RetrofitLikeServiceApi.class);
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
        try {
            LikeDTO result = api.createLike(ideaId, like).execute().body();
            return Mono.justOrEmpty(result);
        } catch (final IOException ex) {
            throw new RuntimeException("Something goes wrong", ex);
        }
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
        try {
            LikeDTO result = api.readLikeById(ideaId, likeId).execute().body();
            return Mono.justOrEmpty(result);
        } catch (final IOException ex) {
            throw new RuntimeException("Something goes wrong", ex);
        }
    }

    /**
     * Read likes by identifier of idea.
     *
     * @param ideaId identifier of idea for search.
     * @return likes by identifier of i
     * }dea.
     */
    @NonNull
    @Override
    public Flux<LikeDTO> readLikesByIdeaId(@NonNull final String ideaId) {
        try {
            List<LikeDTO> result = api.readLikesByIdeaId(ideaId).execute().body();
            return Flux.fromIterable(result);
        } catch (final IOException ex) {
            throw new RuntimeException("Something goes wrong", ex);
        }
    }

    /**
     * Delete like by his identifier.
     *
     * @param ideaId identifier of idea for delete.
     * @param likeId identifier of like for delete.
     * @return operation complete successfully or
     * }not.
     */
    @NonNull
    @Override
    public Mono<Boolean> deleteLike(@NonNull final String ideaId,
                                    @NonNull final String likeId) {
        try {
            boolean result = api.deleteLike(ideaId, likeId).execute().code() == 204;
            return Mono.just(result);
        } catch (final IOException ex) {
            throw new RuntimeException("Something goes wrong", ex);
        }
    }
}