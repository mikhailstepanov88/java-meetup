package com.github.mikhailstepanov88.java_meetup.like.service;

import com.github.mikhailstepanov88.java_meetup.like.data.dto.LikeDTO;
import com.github.mikhailstepanov88.java_meetup.like.data.entity.LikeEntity;
import com.github.mikhailstepanov88.java_meetup.like.repository.LikeRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

@Service
public class DefaultLikeService implements LikeService {
    private final LikeRepository likeRepository;
    private final Converter<LikeDTO, LikeEntity> likeDTOLikeEntityConverter;
    private final Converter<LikeEntity, LikeDTO> likeEntityLikeDTOConverter;

    /**
     * Constructor.
     *
     * @param likeRepository             repository for working with likes.
     * @param likeDTOLikeEntityConverter converter from like DTO to like entity.
     * @param likeEntityLikeDTOConverter converter from like entity to like DTO.
     */
    public DefaultLikeService(@NonNull final LikeRepository likeRepository,
                              @NonNull final Converter<LikeDTO, LikeEntity> likeDTOLikeEntityConverter,
                              @NonNull final Converter<LikeEntity, LikeDTO> likeEntityLikeDTOConverter) {
        this.likeRepository = likeRepository;
        this.likeDTOLikeEntityConverter = likeDTOLikeEntityConverter;
        this.likeEntityLikeDTOConverter = likeEntityLikeDTOConverter;
    }

    /**
     * Create like.
     *
     * @param ideaId identifier of idea.
     * @param like   like for create.
     * @return created like.
     */
    @NonNull
    @Override
    public Mono<LikeDTO> createLike(@NonNull final String ideaId,
                                    @NonNull final LikeDTO like) {
        LikeEntity likeEntity = likeDTOLikeEntityConverter.convert(like);
        likeEntity.setId(null);
        likeEntity.setIdeaId(ideaId);

        return likeRepository.createLike(likeEntity).map(likeEntityLikeDTOConverter::convert);
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
    public Mono<LikeDTO> readLikeById(@NonNull final String ideaId,
                                      @NonNull final String likeId) {
        return likeRepository.readLikeById(ideaId, likeId).map(likeEntityLikeDTOConverter::convert);
    }

    /**
     * Read likes by identifier of idea.
     *
     * @param ideaId identifier of idea for search.
     * @return likes by identifier of idea.
     */
    @NonNull
    @Override
    public Flux<LikeDTO> readLikesByIdeaId(@NonNull final String ideaId) {
        return likeRepository.readLikesByIdeaId(ideaId).map(likeEntityLikeDTOConverter::convert);
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
        return likeRepository.deleteLike(ideaId, likeId);
    }
}