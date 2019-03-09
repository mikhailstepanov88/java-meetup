package com.github.mikhailstepanov88.java_meetup.idea.config;

import com.github.mikhailstepanov88.java_meetup.idea.converter.IdeaDTOIdeaEntityConverter;
import com.github.mikhailstepanov88.java_meetup.idea.converter.IdeaEntityIdeaDTOConverter;
import com.github.mikhailstepanov88.java_meetup.idea.data.dto.IdeaDTO;
import com.github.mikhailstepanov88.java_meetup.idea.data.entity.IdeaEntity;
import com.github.mikhailstepanov88.java_meetup.like.converter.LikeDTOLikeEntityConverter;
import com.github.mikhailstepanov88.java_meetup.like.converter.LikeEntityLikeDTOConverter;
import com.github.mikhailstepanov88.java_meetup.like.data.dto.LikeDTO;
import com.github.mikhailstepanov88.java_meetup.like.data.entity.LikeEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ConverterConfig {
    /**
     * Get converter from idea DTO to idea entity.
     *
     * @return converter from idea DTO to idea entity.
     */
    @Bean
    public Converter<IdeaDTO, IdeaEntity> ideaDTOIdeaEntityConverter() {
        return new IdeaDTOIdeaEntityConverter();
    }

    /**
     * Get converter from idea entity to idea DTO.
     *
     * @return converter from idea entity to idea DTO.
     */
    @Bean
    public Converter<IdeaEntity, IdeaDTO> ideaEntityIdeaDTOConverter() {
        return new IdeaEntityIdeaDTOConverter();
    }

    /**
     * Get converter from like DTO to like entity.
     *
     * @return converter from like DTO to like entity.
     */
    @Bean
    public Converter<LikeDTO, LikeEntity> likeDTOLikeEntityConverter() {
        return new LikeDTOLikeEntityConverter();
    }

    /**
     * Get converter from like entity to like DTO.
     *
     * @return converter from like entity to like DTO.
     */
    @Bean
    public Converter<LikeEntity, LikeDTO> likeEntityLikeDTOConverter() {
        return new LikeEntityLikeDTOConverter();
    }
}