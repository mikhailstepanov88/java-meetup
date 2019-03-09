package com.github.mikhailstepanov88.java_meetup.idea.repository;

import com.github.mikhailstepanov88.java_meetup.idea.data.entity.IdeaEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

public interface IdeaRepository {
    /**
     * Create idea.
     *
     * @param idea idea for create.
     * @return created idea.
     */
    @NonNull
    Mono<IdeaEntity> createIdea(@NonNull IdeaEntity idea);

    /**
     * Read idea by his identifier.
     *
     * @param id identifier of idea for search.
     * @return idea by his identifier.
     */
    @NonNull
    Mono<IdeaEntity> readIdeaById(@NonNull String id);

    /**
     * Read ideas by pattern.
     *
     * @param pattern pattern for search.
     * @return ideas by pattern.
     */
    @NonNull
    Flux<IdeaEntity> readIdeasByPattern(@NonNull String pattern);

    /**
     * Read all ideas.
     *
     * @return all ideas.
     */
    @NonNull
    Flux<IdeaEntity> readIdeas();
}