package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.squareup.javapoet.FieldSpec;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.annotation.NonNull;

import javax.lang.model.element.Modifier;

public class DefaultPoetLikeServiceClientFieldGenerator implements PoetLikeServiceClientFieldGenerator {
    /**
     * Generate field specification.
     *
     * @return field specification.
     */
    @NonNull
    @Override
    public FieldSpec generate() {
        return FieldSpec.builder(WebClient.class, "webClient",
                Modifier.PRIVATE, Modifier.FINAL).build();
    }
}