package com.github.mikhailstepanov88.java_meetup.like.repository;

import com.github.mikhailstepanov88.java_meetup.like.data.entity.LikeEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

public interface LikeRepository {
    /**
     * Create like.
     *
     * @param like like for create.
     * @return created like.
     */
    @NonNull
    Mono<LikeEntity> createLike(@NonNull LikeEntity like);

    /**
     * Read like by his identifier.
     *
     * @param ideaId identifier of idea for search.
     * @param likeId identifier of like for search.
     * @return like by his identifier.
     */
    @NonNull
    Mono<LikeEntity> readLikeById(@NonNull String ideaId, @NonNull String likeId);

    /**
     * Read likes by identifier of idea.
     *
     * @param ideaId identifier of idea for search.
     * @return likes by identifier of idea.
     */
    @NonNull
    Flux<LikeEntity> readLikesByIdeaId(@NonNull String ideaId);

    /**
     * Delete like by his identifier.
     *
     * @param ideaId identifier of idea for delete.
     * @param likeId identifier of like for delete.
     * @return operation complete successfully or not.
     */
    @NonNull
    Mono<Boolean> deleteLike(@NonNull String ideaId, @NonNull String likeId);
}