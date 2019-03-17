package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.github.mikhailstepanov88.java_meetup.like.client.annotation.*;
import com.squareup.javapoet.CodeBlock;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

class PoetLikeServiceClientMethodCodeGenerator {
    /**
     * Generate code of method.
     *
     * @param method method.
     * @return code of method.
     */
    @Nullable
    CodeBlock generate(@NonNull final Method method) {
        if (nonNull(method.getAnnotation(POST.class)))
            return generatePostMethodCode(method);
        if (nonNull(method.getAnnotation(GET.class)))
            return generateGetMethodCode(method);
        if (nonNull(method.getAnnotation(DELETE.class)))
            return generateDeleteMethodCode(method);
        return null;
    }

    /**
     * Generate code of post method.
     *
     * @param method method.
     * @return code of post method.
     */
    private CodeBlock generatePostMethodCode(@NonNull final Method method) {
        Result methodResult = method.getAnnotation(Result.class);
        String terminalMethod = methodResult.multiple() ?
                ".bodyToFlux($T.class)" :
                ".bodyToMono($T.class)";

        String bodyParameterName = getBodyParameter(method).getName();
        Collection<String> pathParameterNames = getPathParameters(method).stream()
                .map(Parameter::getName)
                .collect(Collectors.toList());

        return CodeBlock.builder()
                .addStatement(
                        "return webClient.post()" +
                                ".uri($S, $L)" +
                                ".contentType(APPLICATION_JSON_UTF8)" +
                                ".accept(APPLICATION_JSON_UTF8)" +
                                ".syncBody($L)" +
                                ".retrieve()" +
                                terminalMethod,
                        method.getAnnotation(POST.class).path(),
                        String.join(", ", pathParameterNames),
                        bodyParameterName,
                        methodResult.type()
                ).build();
    }

    /**
     * Generate code of get method.
     *
     * @param method method.
     * @return code of delete method.
     */
    @NonNull
    private CodeBlock generateGetMethodCode(@NonNull final Method method) {
        Result methodResult = method.getAnnotation(Result.class);
        String terminalMethod = methodResult.multiple() ?
                ".bodyToFlux($T.class)" :
                ".bodyToMono($T.class)";

        Collection<String> pathParameterNames = getPathParameters(method).stream()
                .map(Parameter::getName)
                .collect(Collectors.toList());

        return CodeBlock.builder()
                .addStatement(
                        "return webClient.get()" +
                                ".uri($S, $L)" +
                                ".accept(APPLICATION_JSON_UTF8)" +
                                ".retrieve()" +
                                terminalMethod,
                        method.getAnnotation(GET.class).path(),
                        String.join(", ", pathParameterNames),
                        methodResult.type()
                ).build();
    }

    /**
     * Generate code of delete method.
     *
     * @param method method.
     * @return code of delete method.
     */
    @NonNull
    private CodeBlock generateDeleteMethodCode(@NonNull final Method method) {
        Collection<String> pathParameterNames = getPathParameters(method).stream()
                .map(Parameter::getName)
                .collect(Collectors.toList());

        return CodeBlock.builder()
                .addStatement(
                        "return webClient.delete()" +
                                ".uri($S, $L)" +
                                ".accept(APPLICATION_JSON_UTF8)" +
                                ".exchange()" +
                                ".map(response -> response.statusCode().equals(NO_CONTENT))",
                        method.getAnnotation(DELETE.class).path(),
                        String.join(", ", pathParameterNames)
                ).build();
    }

    /**
     * Get path parameters ordered by indexes.
     *
     * @param method method.
     * @return path parameters ordered by indexes.
     */
    @NonNull
    private Collection<Parameter> getPathParameters(@NonNull final Method method) {
        return Arrays.stream(method.getParameters())
                .filter(parameter -> nonNull(parameter.getAnnotation(Path.class)))
                .sorted((first, second) -> {
                    int firstIndex = first.getAnnotation(Path.class).index();
                    int secondIndex = second.getAnnotation(Path.class).index();
                    return Integer.compare(firstIndex, secondIndex);
                })
                .collect(Collectors.toList());
    }

    /**
     * Get body parameters.
     *
     * @param method method.
     * @return body parameters.
     */
    @Nullable
    private Parameter getBodyParameter(@NonNull final Method method) {
        return Arrays.stream(method.getParameters())
                .filter(parameter -> nonNull(parameter.getAnnotation(Body.class)))
                .findAny().orElse(null);
    }
}