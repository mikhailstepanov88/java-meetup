package com.github.mikhailstepanov88.java_meetup.like.client;

import com.github.mikhailstepanov88.java_meetup.like.data.dto.LikeDTO;
import reactor.util.annotation.NonNull;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RetrofitLikeServiceApi {
    /**
     * Create like.
     *
     * @param ideaId identifier of idea.
     * @param like   like for create.
     * @return created like.
     */
    @NonNull
    @POST("/idea/{idea_id}/like")
    Call<LikeDTO> createLike(@NonNull @Path("idea_id") String ideaId,
                             @NonNull @Body LikeDTO like);

    /**
     * Read like by his identifier.
     *
     * @param ideaId identifier of idea for search.
     * @param likeId identifier of like for search.
     * @return like by his identifier.
     */
    @NonNull
    @GET("/idea/{idea_id}/like/{like_id}")
    Call<LikeDTO> readLikeById(@NonNull @Path("idea_id") String ideaId,
                               @NonNull @Path("like_id") String likeId);

    /**
     * Read likes by identifier of idea.
     *
     * @param ideaId identifier of idea for search.
     * @return likes by identifier of idea.
     */
    @NonNull
    @GET("/idea/{idea_id}/like")
    Call<List<LikeDTO>> readLikesByIdeaId(@NonNull @Path("idea_id") String ideaId);

    /**
     * Delete like by his identifier.
     *
     * @param ideaId identifier of idea for delete.
     * @param likeId identifier of like for delete.
     * @return operation complete successfully or not.
     */
    @NonNull
    @DELETE("/idea/{idea_id}/like/{like_id}")
    Call<Boolean> deleteLike(@NonNull @Path("idea_id") String ideaId,
                             @NonNull @Path("like_id") String likeId);
}