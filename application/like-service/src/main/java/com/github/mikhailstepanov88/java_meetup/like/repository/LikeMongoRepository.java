package com.github.mikhailstepanov88.java_meetup.like.repository;

import com.github.mikhailstepanov88.java_meetup.like.data.entity.LikeEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class LikeMongoRepository implements LikeRepository {
    private final ReactiveMongoTemplate template;

    /**
     * Constructor.
     *
     * @param template template for working with MongoDB.
     */
    public LikeMongoRepository(@NonNull final ReactiveMongoTemplate template) {
        this.template = template;
    }

    /**
     * Create like.
     *
     * @param like like for create.
     * @return created like.
     */
    @NonNull
    @Override
    public Mono<LikeEntity> createLike(@NonNull final LikeEntity like) {
        return template.save(like);
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
    public Mono<LikeEntity> readLikeById(@NonNull final String ideaId,
                                         @NonNull final String likeId) {
        Query query = query(where("id").is(new ObjectId(likeId))
                .and("ideaId").is(ideaId));
        return template.findOne(query, LikeEntity.class);
    }

    /**
     * Read likes by identifier of idea.
     *
     * @param ideaId identifier of idea for search.
     * @return likes by identifier of idea.
     */
    @NonNull
    @Override
    public Flux<LikeEntity> readLikesByIdeaId(@NonNull final String ideaId) {
        Query query = query(where("ideaId").is(ideaId));
        return template.find(query, LikeEntity.class);
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
        Query query = query(where("id").is(new ObjectId(likeId))
                .and("ideaId").is(ideaId));
        return template.remove(query, LikeEntity.class)
                .map(result -> result.getDeletedCount() > 0);
    }
}