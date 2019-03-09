package com.github.mikhailstepanov88.java_meetup.idea.converter;

import com.github.mikhailstepanov88.java_meetup.idea.data.dto.IdeaDTO;
import com.github.mikhailstepanov88.java_meetup.idea.data.entity.IdeaEntity;
import org.springframework.core.convert.converter.Converter;
import reactor.util.annotation.NonNull;

public class IdeaDTOIdeaEntityConverter implements Converter<IdeaDTO, IdeaEntity> {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @NonNull
    @Override
    public IdeaEntity convert(@NonNull final IdeaDTO source) {
        return new IdeaEntity(
                source.getId(),
                source.getTitle(),
                source.getDescription()
        );
    }
}