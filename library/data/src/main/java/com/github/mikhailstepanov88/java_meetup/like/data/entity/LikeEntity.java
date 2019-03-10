package com.github.mikhailstepanov88.java_meetup.like.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeEntity {
    @Id
    @Nullable
    private String id;
    @NonNull
    @Indexed
    private String ideaId;
    @NonNull
    private double weight;
    @Nullable
    private String comment;
}