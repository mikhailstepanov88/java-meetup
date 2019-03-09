package com.github.mikhailstepanov88.java_meetup.idea.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.mikhailstepanov88.java_meetup.like.data.dto.LikeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import java.util.List;

@Getter
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class IdeaDTO {
    @Nullable
    private final String id;
    @NonNull
    private final String title;
    @NonNull
    private final String description;
    @NonNull
    private final List<LikeDTO> likes;
}