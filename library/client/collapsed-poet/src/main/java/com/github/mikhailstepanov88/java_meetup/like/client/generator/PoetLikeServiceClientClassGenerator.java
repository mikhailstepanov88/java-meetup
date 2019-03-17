package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.squareup.javapoet.TypeSpec;
import reactor.util.annotation.NonNull;

interface PoetLikeServiceClientClassGenerator {
    /**
     * Generate class specification.
     *
     * @param clazz class.
     * @return class specification.
     */
    @NonNull
    TypeSpec generate(@NonNull Class clazz);
}