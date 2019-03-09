package com.github.mikhailstepanov88.java_meetup.idea.service;

import com.github.mikhailstepanov88.java_meetup.idea.data.dto.IdeaDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

public interface IdeaService {
    /**
     * Create idea.
     *
     * @param idea idea for create.
     * @return created idea.
     */
    @NonNull
    Mono<IdeaDTO> createIdea(@NonNull IdeaDTO idea);

    /**
     * Read idea by his identifier.
     *
     * @param id identifier of idea for search.
     * @return idea by his identifier.
     */
    @NonNull
    Mono<IdeaDTO> readIdeaById(@NonNull String id);

    /**
     * Read ideas by pattern.
     *
     * @param pattern pattern for search.
     * @return ideas by pattern.
     */
    @NonNull
    Flux<IdeaDTO> readIdeasByPattern(@NonNull String pattern);

    /**
     * Read all ideas.
     *
     * @return all ideas.
     */
    @NonNull
    Flux<IdeaDTO> readIdeas();
}