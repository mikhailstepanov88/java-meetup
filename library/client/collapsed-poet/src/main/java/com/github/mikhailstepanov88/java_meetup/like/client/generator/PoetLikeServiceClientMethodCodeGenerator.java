package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.squareup.javapoet.CodeBlock;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import java.lang.reflect.Method;

interface PoetLikeServiceClientMethodCodeGenerator {
    /**
     * Generate code of method.
     *
     * @param method method.
     * @return code of method.
     */
    @Nullable
    CodeBlock generate(@NonNull final Method method);
}