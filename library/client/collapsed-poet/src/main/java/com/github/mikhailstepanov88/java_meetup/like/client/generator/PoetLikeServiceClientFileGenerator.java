package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.squareup.javapoet.JavaFile;
import reactor.util.annotation.NonNull;

interface PoetLikeServiceClientFileGenerator {
    /**
     * Generate file.
     *
     * @param clazz class.
     * @return file.
     */
    @NonNull
    JavaFile generate(@NonNull Class clazz);
}