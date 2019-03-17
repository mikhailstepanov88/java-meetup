package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.squareup.javapoet.FieldSpec;
import reactor.util.annotation.NonNull;

public interface PoetLikeServiceClientFieldGenerator {
    /**
     * Generate field specification.
     *
     * @return field specification.
     */
    @NonNull
    FieldSpec generate();
}