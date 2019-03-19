package com.github.mikhailstepanov88.java_meetup.like.client;

import com.github.mikhailstepanov88.java_meetup.like.client.annotation.*;
import com.github.mikhailstepanov88.java_meetup.like.data.dto.LikeDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

public interface PoetLikeService {
    /**
     * Create like.
     *
     * @param ideaId identifier of idea.
     * @param like   like for create.
     * @return created like.
     */
    @NonNull
    @Result(type = LikeDTO.class)
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
    @Result(type = LikeDTO.class)
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
    @Result(type = LikeDTO.class, multiple = true)
    Flux<LikeDTO> readLikesByIdeaId(@NonNull @Path("idea_id") String ideaId);

    /**
     * Delete like by his identifier.
     *
     * @param ideaId identifier of idea for delete.
     * @param likeId identifier of like for delete.
     * @return operation complete successfully or not.
     */
    @NonNull
    @Result(type = Boolean.class)
    @DELETE(path = "/idea/{idea_id}/like/{like_id}")
    Mono<Boolean> deleteLike(@NonNull @Path(value = "idea_id", index = 0) String ideaId,
                             @NonNull @Path(value = "like_id", index = 1) String likeId);
}