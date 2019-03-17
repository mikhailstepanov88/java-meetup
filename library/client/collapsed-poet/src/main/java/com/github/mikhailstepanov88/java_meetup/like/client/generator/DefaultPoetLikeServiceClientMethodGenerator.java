package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.github.mikhailstepanov88.java_meetup.like.client.annotation.Result;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

class DefaultPoetLikeServiceClientMethodGenerator implements PoetLikeServiceClientMethodGenerator {
    private final PoetLikeServiceClientMethodCodeGenerator codeGenerator;
    private final PoetLikeServiceClientParameterGenerator parameterGenerator;

    /**
     * Constructor.
     *
     * @param codeGenerator      generator of method code.
     * @param parameterGenerator generator of method parameters.
     */
    DefaultPoetLikeServiceClientMethodGenerator(@NonNull final PoetLikeServiceClientMethodCodeGenerator codeGenerator,
                                                @NonNull final PoetLikeServiceClientParameterGenerator parameterGenerator) {
        this.codeGenerator = codeGenerator;
        this.parameterGenerator = parameterGenerator;
    }

    /**
     * Generate methods specification.
     *
     * @param clazz class.
     * @return methods specification.
     */
    @NonNull
    @Override
    public Collection<MethodSpec> generate(@NonNull final Class clazz) {
        return Arrays.stream(clazz.getMethods())
                .map(this::generate)
                .collect(Collectors.toList());
    }

    /**
     * Generate method specification.
     *
     * @param method method.
     * @return method specification.
     */
    @NonNull
    @Override
    public MethodSpec generate(@NonNull final Method method) {
        Result methodResult = method.getAnnotation(Result.class);
        ParameterizedTypeName returnType = methodResult.multiple() ?
                ParameterizedTypeName.get(Flux.class, methodResult.type()) :
                ParameterizedTypeName.get(Mono.class, methodResult.type());

        return MethodSpec.methodBuilder(method.getName())
                .addAnnotation(NonNull.class)
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(returnType)
                .addParameters(parameterGenerator.generate(method))
                .addCode(codeGenerator.generate(method))
                .build();
    }
}