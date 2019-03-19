package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.github.mikhailstepanov88.java_meetup.like.client.LikeServiceClient;
import com.squareup.javapoet.TypeSpec;
import reactor.util.annotation.NonNull;

import javax.lang.model.element.Modifier;

public class DefaultPoetLikeServiceClientClassGenerator implements PoetLikeServiceClientClassGenerator {
    private final PoetLikeServiceClientFieldGenerator fieldGenerator;
    private final PoetLikeServiceClientMethodGenerator methodGenerator;
    private final PoetLikeServiceClientConstructorGenerator constructorGenerator;

    /**
     * Constructor.
     *
     * @param fieldGenerator       generator of class fields.
     * @param methodGenerator      generator of class methods.
     * @param constructorGenerator generator of class constructors.
     */
    public DefaultPoetLikeServiceClientClassGenerator(@NonNull final PoetLikeServiceClientFieldGenerator fieldGenerator,
                                                      @NonNull final PoetLikeServiceClientMethodGenerator methodGenerator,
                                                      @NonNull final PoetLikeServiceClientConstructorGenerator constructorGenerator) {
        this.fieldGenerator = fieldGenerator;
        this.methodGenerator = methodGenerator;
        this.constructorGenerator = constructorGenerator;
    }

    /**
     * Generate class specification.
     *
     * @param clazz class.
     * @return class specification.
     */
    @NonNull
    @Override
    public TypeSpec generate(@NonNull final Class clazz) {
        return TypeSpec.classBuilder("Default" + clazz.getSimpleName())
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(clazz)
                .addSuperinterface(LikeServiceClient.class)
                .addField(fieldGenerator.generate())
                .addMethod(constructorGenerator.generate())
                .addMethods(methodGenerator.generate(clazz))
                .build();
    }
}