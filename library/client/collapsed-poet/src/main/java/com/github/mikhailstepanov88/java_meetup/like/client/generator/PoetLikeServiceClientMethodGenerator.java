package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.squareup.javapoet.MethodSpec;
import reactor.util.annotation.NonNull;

import java.lang.reflect.Method;
import java.util.Collection;

public interface PoetLikeServiceClientMethodGenerator {
    /**
     * Generate methods specification.
     *
     * @param clazz class.
     * @return methods specification.
     */
    @NonNull
    Collection<MethodSpec> generate(@NonNull Class clazz);

    /**
     * Generate method specification.
     *
     * @param method method.
     * @return method specification.
     */
    @NonNull
    MethodSpec generate(@NonNull Method method);
}