package com.github.mikhailstepanov88.java_meetup.like.converter;

import com.github.mikhailstepanov88.java_meetup.like.data.dto.LikeDTO;
import com.github.mikhailstepanov88.java_meetup.like.data.entity.LikeEntity;
import org.springframework.core.convert.converter.Converter;
import reactor.util.annotation.NonNull;

public class LikeEntityLikeDTOConverter implements Converter<LikeEntity, LikeDTO> {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @NonNull
    @Override
    public LikeDTO convert(@NonNull final LikeEntity source) {
        return new LikeDTO(source.getId(), source.getIdeaId(), source.getWeight(), source.getComment());
    }
}