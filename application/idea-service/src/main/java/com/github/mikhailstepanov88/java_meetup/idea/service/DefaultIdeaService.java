package com.github.mikhailstepanov88.java_meetup.idea.service;

import com.github.mikhailstepanov88.java_meetup.idea.data.dto.IdeaDTO;
import com.github.mikhailstepanov88.java_meetup.idea.data.entity.IdeaEntity;
import com.github.mikhailstepanov88.java_meetup.idea.repository.IdeaRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

@Service
public class DefaultIdeaService implements IdeaService {
    private final IdeaRepository ideaRepository;
    private final Converter<IdeaDTO, IdeaEntity> ideaDTOIdeaEntityConverter;
    private final Converter<IdeaEntity, IdeaDTO> ideaEntityIdeaDTOConverter;

    /**
     * Constructor.
     *
     * @param ideaRepository             repository for working with ideas.
     * @param ideaDTOIdeaEntityConverter converter from idea DTO to idea entity.
     * @param ideaEntityIdeaDTOConverter converter from idea entity to idea DTO.
     */
    public DefaultIdeaService(@NonNull final IdeaRepository ideaRepository,
                              @NonNull final Converter<IdeaDTO, IdeaEntity> ideaDTOIdeaEntityConverter,
                              @NonNull final Converter<IdeaEntity, IdeaDTO> ideaEntityIdeaDTOConverter) {
        this.ideaRepository = ideaRepository;
        this.ideaDTOIdeaEntityConverter = ideaDTOIdeaEntityConverter;
        this.ideaEntityIdeaDTOConverter = ideaEntityIdeaDTOConverter;
    }

    /**
     * Create idea.
     *
     * @param idea idea for create.
     * @return created idea.
     */
    @NonNull
    @Override
    public Mono<IdeaDTO> createIdea(@NonNull final IdeaDTO idea) {
        IdeaEntity ideaEntity = ideaDTOIdeaEntityConverter.convert(idea);
        ideaEntity.setId(null);

        return ideaRepository.createIdea(ideaEntity).map(ideaEntityIdeaDTOConverter::convert);
    }

    /**
     * Read idea by his identifier.
     *
     * @param id identifier of idea for search.
     * @return idea by his identifier.
     */
    @NonNull
    @Override
    public Mono<IdeaDTO> readIdeaById(@NonNull final String id) {
        return ideaRepository.readIdeaById(id).map(ideaEntityIdeaDTOConverter::convert);
    }

    /**
     * Read ideas by pattern.
     *
     * @param pattern pattern for search.
     * @return ideas by pattern.
     */
    @NonNull
    @Override
    public Flux<IdeaDTO> readIdeasByPattern(@NonNull final String pattern) {
        return ideaRepository.readIdeasByPattern(pattern).map(ideaEntityIdeaDTOConverter::convert);
    }

    /**
     * Read all ideas.
     *
     * @return all ideas.
     */
    @NonNull
    @Override
    public Flux<IdeaDTO> readIdeas() {
        return ideaRepository.readIdeas().map(ideaEntityIdeaDTOConverter::convert);
    }
}