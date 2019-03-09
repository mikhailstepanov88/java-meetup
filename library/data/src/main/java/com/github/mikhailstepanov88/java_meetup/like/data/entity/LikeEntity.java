package com.github.mikhailstepanov88.java_meetup.like.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

@Getter
@Document
@AllArgsConstructor(onConstructor = @__(@PersistenceConstructor))
public class LikeEntity {
    @Id
    @Nullable
    private final String id;
    @NonNull
    @Indexed
    private final String ideaId;
    @NonNull
    private final double weight;
    @Nullable
    private final String comment;
}