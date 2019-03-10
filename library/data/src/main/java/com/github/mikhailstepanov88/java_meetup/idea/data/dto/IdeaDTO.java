package com.github.mikhailstepanov88.java_meetup.idea.data.dto;

import com.github.mikhailstepanov88.java_meetup.like.data.dto.LikeDTO;
import lombok.*;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdeaDTO {
    @Nullable
    private String id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private List<LikeDTO> likes;
}