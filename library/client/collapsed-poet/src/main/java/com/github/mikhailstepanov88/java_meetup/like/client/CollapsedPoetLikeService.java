package com.github.mikhailstepanov88.java_meetup.like.client;

import com.github.mikhailstepanov88.java_meetup.like.client.generator.annotation.*;
import com.github.mikhailstepanov88.java_meetup.like.data.dto.LikeDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

public interface CollapsedPoetLikeService {
    /**
     * Create like.
     *
     * @param ideaId identifier of idea.
     * @param like   like for create.
     * @return created like.
     */
    @NonNull
    @POST(path = "/idea/{idea_id}/like")
    Mono<LikeDTO> createLike(@NonNull @Path("idea_id") String ideaId,
                             @NonNull @Body LikeDTO like);

    /**
     * Read like by his identifier.
     *
     * @param ideaId identifier of idea for search.
     * @param likeId identifier of like for search.
     * @return like by his identifier.
     */
    @NonNull
    @GET(path = "/idea/{idea_id}/like/{like_id}")
    Mono<LikeDTO> readLikeById(@NonNull @Path(value = "idea_id", index = 0) String ideaId,
                               @NonNull @Path(value = "like_id", index = 1) String likeId);

    /**
     * Read likes by identifier of idea.
     *
     * @param ideaId identifier of idea for search.
     * @return likes by identifier of idea.
     */
    @NonNull
    @GET(path = "/idea/{idea_id}/like")
    Flux<LikeDTO> readLikesByIdeaId(@NonNull @Path("idea_id") String ideaId);

    /**
     * Delete like by his identifier.
     *
     * @param ideaId identifier of idea for delete.
     * @param likeId identifier of like for delete.
     * @return operation complete successfully or not.
     */
    @NonNull
    @DELETE(path = "/idea/{idea_id}/like/{like_id}")
    Mono<Boolean> deleteLike(@NonNull @Path(value = "idea_id", index = 0) String ideaId,
                             @NonNull @Path(value = "like_id", index = 1) String likeId);
}