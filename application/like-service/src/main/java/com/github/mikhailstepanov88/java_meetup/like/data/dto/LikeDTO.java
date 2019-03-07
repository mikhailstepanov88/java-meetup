package com.github.mikhailstepanov88.java_meetup.like.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.immutables.value.internal.$processor$.meta.$AnnotationInjections.InjectAnnotation;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import static org.immutables.value.internal.$processor$.meta.$AnnotationInjections.InjectAnnotation.Where.CONSTRUCTOR;

@InjectAnnotation(target = CONSTRUCTOR, type = JsonCreator.class)
interface LikeDTO {
    @Nullable String id();
    @NonNull String ideaId();
    @NonNull Double weight();
    @Nullable String comment();
}