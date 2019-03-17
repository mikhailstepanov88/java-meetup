package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.squareup.javapoet.JavaFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import reactor.util.annotation.NonNull;

public class DefaultPoetLikeServiceClientFileGenerator implements PoetLikeServiceClientFileGenerator {
    private final PoetLikeServiceClientClassGenerator classGenerator;

    /**
     * Constructor.
     *
     * @param classGenerator generator of classes.
     */
    public DefaultPoetLikeServiceClientFileGenerator(@NonNull final PoetLikeServiceClientClassGenerator classGenerator) {
        this.classGenerator = classGenerator;
    }

    /**
     * Generate file.
     *
     * @param clazz class.
     * @return file.
     */
    @NonNull
    @Override
    public JavaFile generate(@NonNull final Class clazz) {
        return JavaFile.builder(clazz.getPackage().getName(), classGenerator.generate(clazz))
                .addStaticImport(MediaType.class, "APPLICATION_JSON_UTF8")
                .addStaticImport(HttpStatus.class, "NO_CONTENT")
                .build();
    }
}