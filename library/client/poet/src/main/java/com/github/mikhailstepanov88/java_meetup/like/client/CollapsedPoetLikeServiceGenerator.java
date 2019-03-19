package com.github.mikhailstepanov88.java_meetup.like.client;

import com.github.mikhailstepanov88.java_meetup.like.client.generator.*;
import reactor.util.annotation.NonNull;

import java.nio.file.Paths;

public class CollapsedPoetLikeServiceGenerator {
    /**
     * Main method of class.
     *
     * @param args input arguments of main method.
     */
    public static void main(@NonNull final String... args) throws Exception {
        PoetLikeServiceClientFieldGenerator fieldGenerator =
                new DefaultPoetLikeServiceClientFieldGenerator();
        PoetLikeServiceClientConstructorGenerator constructorGenerator =
                new DefaultPoetLikeServiceClientConstructorGenerator();

        PoetLikeServiceClientMethodCodeGenerator methodCodeGenerator =
                new DefaultPoetLikeServiceClientMethodCodeGenerator();
        PoetLikeServiceClientParameterGenerator methodParameterGenerator =
                new DefaultPoetLikeServiceClientParameterGenerator();
        PoetLikeServiceClientMethodGenerator methodGenerator =
                new DefaultPoetLikeServiceClientMethodGenerator(
                        methodCodeGenerator, methodParameterGenerator);

        PoetLikeServiceClientClassGenerator classGenerator =
                new DefaultPoetLikeServiceClientClassGenerator(
                        fieldGenerator, methodGenerator, constructorGenerator);
        PoetLikeServiceClientFileGenerator fileGenerator =
                new DefaultPoetLikeServiceClientFileGenerator(classGenerator);

        fileGenerator.generate(CollapsedPoetLikeService.class).writeTo(Paths.get(
                "/Users/mikhailstepanov/Projects/java-meetup/library/client/collapsed-poet/src/main/java"));
    }
}