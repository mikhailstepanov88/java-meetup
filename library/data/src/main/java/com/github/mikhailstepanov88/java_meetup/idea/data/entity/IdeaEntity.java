package com.github.mikhailstepanov88.java_meetup.idea.data.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import reactor.util.annotation.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdeaEntity {
    @Id
    @Nullable
    private String id;
    @NonNull
    @Indexed
    private String title;
    @NonNull
    @Indexed
    private String description;
}