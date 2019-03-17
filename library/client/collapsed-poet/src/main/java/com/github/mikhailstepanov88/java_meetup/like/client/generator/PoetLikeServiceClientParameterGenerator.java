package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.squareup.javapoet.ParameterSpec;
import reactor.util.annotation.NonNull;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;

interface PoetLikeServiceClientParameterGenerator {
    /**
     * Generate parameters specification.
     *
     * @param method method.
     * @return parameters specification.
     */
    @NonNull
    Collection<ParameterSpec> generate(@NonNull Method method);

    /**
     * Generate parameter specification.
     *
     * @param parameter parameter of method.
     * @return parameter specification.
     */
    @NonNull
    ParameterSpec generate(@NonNull Parameter parameter);
}