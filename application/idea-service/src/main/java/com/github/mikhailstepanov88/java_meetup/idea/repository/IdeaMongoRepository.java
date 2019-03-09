package com.github.mikhailstepanov88.java_meetup.idea.repository;

import com.github.mikhailstepanov88.java_meetup.idea.data.entity.IdeaEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

import java.util.regex.Pattern;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class IdeaMongoRepository implements IdeaRepository {
    private final ReactiveMongoTemplate template;

    /**
     * Constructor.
     *
     * @param template template for working with MongoDB.
     */
    public IdeaMongoRepository(@NonNull final ReactiveMongoTemplate template) {
        this.template = template;
    }

    /**
     * Create idea.
     *
     * @param idea idea for create.
     * @return created idea.
     */
    @NonNull
    @Override
    public Mono<IdeaEntity> createIdea(@NonNull final IdeaEntity idea) {
        return template.save(idea);
    }

    /**
     * Read idea by his identifier.
     *
     * @param id identifier of idea for search.
     * @return idea by his identifier.
     */
    @NonNull
    @Override
    public Mono<IdeaEntity> readIdeaById(@NonNull final String id) {
        Query query = query(where("id").is(new ObjectId(id)));
        return template.findOne(query, IdeaEntity.class);
    }

    /**
     * Read ideas by pattern.
     *
     * @param pattern pattern for search.
     * @return ideas by pattern.
     */
    @NonNull
    @Override
    public Flux<IdeaEntity> readIdeasByPattern(@NonNull final String pattern) {
        Pattern regex = Pattern.compile(".*" + pattern + ".*");
        Query query = query(new Criteria().orOperator(
                where("title").regex(regex),
                where("description").regex(regex)
        ));
        return template.find(query, IdeaEntity.class);
    }

    /**
     * Read all ideas.
     *
     * @return all ideas.
     */
    @NonNull
    @Override
    public Flux<IdeaEntity> readIdeas() {
        return template.findAll(IdeaEntity.class);
    }
}