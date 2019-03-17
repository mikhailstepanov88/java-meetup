package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.squareup.javapoet.ParameterSpec;
import reactor.util.annotation.NonNull;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

class DefaultPoetLikeServiceClientParameterGenerator {
    /**
     * Generate parameters specification.
     *
     * @param method method.
     * @return parameters specification.
     */
    @NonNull
    Collection<ParameterSpec> generate(@NonNull final Method method) {
        return Arrays.stream(method.getParameters())
                .map(this::generate)
                .collect(Collectors.toList());
    }

    /**
     * Generate parameter specification.
     *
     * @param parameter parameter of method.
     * @return parameter specification.
     */
    @NonNull
    ParameterSpec generate(@NonNull final Parameter parameter) {
        return ParameterSpec.builder(parameter.getType(), parameter.getName(), Modifier.FINAL)
                .addAnnotation(NonNull.class)
                .build();
    }
}