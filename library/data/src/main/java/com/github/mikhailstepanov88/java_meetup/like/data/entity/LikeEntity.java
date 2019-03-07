package com.github.mikhailstepanov88.java_meetup.like.data.entity;

import org.immutables.value.Value;
import org.immutables.value.internal.$processor$.meta.$AnnotationInjections.InjectAnnotation;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import static org.immutables.value.internal.$processor$.meta.$AnnotationInjections.InjectAnnotation.Where.CONSTRUCTOR;
import static org.immutables.value.internal.$processor$.meta.$AnnotationInjections.InjectAnnotation.Where.FIELD;

@Document
@Value.Immutable
@InjectAnnotation(target = CONSTRUCTOR, type = PersistenceConstructor.class)
interface LikeEntity {
    @Id
    @Nullable
    String id();

    @NonNull
    @InjectAnnotation(target = FIELD, type = Indexed.class)
    String ideaId();

    @NonNull
    double weight();

    @Nullable
    String comment();
}