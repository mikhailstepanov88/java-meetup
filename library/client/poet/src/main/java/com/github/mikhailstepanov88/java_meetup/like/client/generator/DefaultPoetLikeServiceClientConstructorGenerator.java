package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.annotation.NonNull;

import javax.lang.model.element.Modifier;

public class DefaultPoetLikeServiceClientConstructorGenerator implements PoetLikeServiceClientConstructorGenerator {
    /**
     * Generate constructor specification.
     *
     * @return constructor specification.
     */
    @NonNull
    @Override
    public MethodSpec generate() {
        ParameterSpec baseUrl = ParameterSpec.builder(String.class, "baseUrl", Modifier.FINAL)
                .addAnnotation(NonNull.class)
                .build();
        ParameterSpec webClientBuilder = ParameterSpec.builder(WebClient.Builder.class, "webClientBuilder", Modifier.FINAL)
                .addAnnotation(NonNull.class)
                .build();

        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(baseUrl)
                .addParameter(webClientBuilder)
                .addStatement("this.webClient = webClientBuilder.baseUrl(baseUrl).build()")
                .build();
    }
}