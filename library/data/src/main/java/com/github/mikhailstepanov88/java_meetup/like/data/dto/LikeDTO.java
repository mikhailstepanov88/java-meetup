package com.github.mikhailstepanov88.java_meetup.like.data.dto;

import lombok.*;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {
    @Nullable
    private String id;
    @NonNull
    private String ideaId;
    @NonNull
    private Double weight;
    @Nullable
    private String comment;
}