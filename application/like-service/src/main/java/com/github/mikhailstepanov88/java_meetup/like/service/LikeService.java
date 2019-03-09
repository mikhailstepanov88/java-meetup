package com.github.mikhailstepanov88.java_meetup.like.service;

import com.github.mikhailstepanov88.java_meetup.like.data.dto.LikeDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

public interface LikeService {
    /**
     * Create like.
     *
     * @param like like for create.
     * @return created like.
     */
    @NonNull
    Mono<LikeDTO> createLike(@NonNull LikeDTO like);

    /**
     * Read likes by identifier of idea.
     *
     * @param ideaId identifier of idea for search.
     * @return likes by identifier of idea.
     */
    @NonNull
    Flux<LikeDTO> readLikesByIdeaId(@NonNull String ideaId);

    /**
     * Delete like by his identifier.
     *
     * @param likeId identifier of like for delete.
     * @return operation complete successfully or not.
     */
    @NonNull
    Mono<Boolean> deleteLike(@NonNull String likeId);
}