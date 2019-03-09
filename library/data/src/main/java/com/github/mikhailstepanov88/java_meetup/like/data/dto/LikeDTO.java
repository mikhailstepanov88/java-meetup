package com.github.mikhailstepanov88.java_meetup.like.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

@Getter
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class LikeDTO {
    @Nullable
    private final String id;
    @NonNull
    private final String ideaId;
    @NonNull
    private final Double weight;
    @Nullable
    private final String comment;
}