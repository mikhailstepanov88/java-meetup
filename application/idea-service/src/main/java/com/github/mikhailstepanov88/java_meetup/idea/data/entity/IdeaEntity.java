package com.github.mikhailstepanov88.java_meetup.idea.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.util.annotation.Nullable;

@Getter
@Document
@AllArgsConstructor(onConstructor = @__(@PersistenceConstructor))
public class IdeaEntity {
    @Id
    @Nullable
    private final String id;
    @NonNull
    @Indexed
    private final String title;
    @NonNull
    @Indexed
    private final String description;
}