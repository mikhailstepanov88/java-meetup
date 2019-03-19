package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.squareup.javapoet.MethodSpec;
import reactor.util.annotation.NonNull;

public interface PoetLikeServiceClientConstructorGenerator {
    /**
     * Generate constructor specification.
     *
     * @return constructor specification.
     */
    @NonNull
    MethodSpec generate();
}