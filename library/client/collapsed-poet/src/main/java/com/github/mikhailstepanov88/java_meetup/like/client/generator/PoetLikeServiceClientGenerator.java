package com.github.mikhailstepanov88.java_meetup.like.client.generator;

import com.github.mikhailstepanov88.java_meetup.like.client.CollapsedPoetLikeService;
import com.github.mikhailstepanov88.java_meetup.like.client.generator.annotation.*;
import com.squareup.javapoet.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class PoetLikeServiceClientGenerator {
    /**
     * Main method of class.
     *
     * @param args input arguments of main method.
     */
    public static void main(@NonNull final String... args) throws IOException {
        new PoetLikeServiceClientGenerator().generate(CollapsedPoetLikeService.class, Paths.get(
                "/Users/mikhailstepanov/Projects/java-meetup/library/client/collapsed-poet/src/main/java"));
    }

    /**
     * Generate java file to destination folder.
     *
     * @param clazz       class.
     * @param destination destination folder.
     * @throws IOException if something goes wrong.
     */
    private void generate(@NonNull final Class clazz,
                          @NonNull final java.nio.file.Path destination) throws IOException {
        generateJavaFile(clazz).writeTo(destination);
    }

    /**
     * Generate java file.
     *
     * @param clazz class.
     * @return java file.
     */
    @NonNull
    private JavaFile generateJavaFile(@NonNull final Class clazz) {
        return JavaFile.builder(clazz.getPackage().getName(), generateTypeSpec(clazz))
                .addStaticImport(MediaType.class, "APPLICATION_JSON_UTF8")
                .addStaticImport(HttpStatus.class, "NO_CONTENT")
                .build();
    }

    /**
     * Generate type specification.
     *
     * @param clazz class.
     * @return type specification.
     */
    @NonNull
    private TypeSpec generateTypeSpec(@NonNull final Class clazz) {
        return TypeSpec.classBuilder("Default" + clazz.getSimpleName())
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(clazz)
                .addField(generateFieldSpec())
                .addMethod(generateConstructorSpec())
                .addMethods(generateMethodsSpec(clazz))
                .build();
    }

    /**
     * Generate field specification.
     *
     * @return field specification.
     */
    @NonNull
    private FieldSpec generateFieldSpec() {
        return FieldSpec.builder(WebClient.class, "webClient", Modifier.PRIVATE, Modifier.FINAL).build();
    }

    /**
     * Generate constructor specification.
     *
     * @return constructor specification.
     */
    @NonNull
    private MethodSpec generateConstructorSpec() {
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

    /**
     * Generate methods specification.
     *
     * @param clazz class.
     * @return methods specification.
     */
    @NonNull
    private Collection<MethodSpec> generateMethodsSpec(@NonNull final Class clazz) {
        return Arrays.stream(clazz.getMethods())
                .map(this::generateMethodSpec)
                .collect(Collectors.toList());
    }

    /**
     * Generate method specification.
     *
     * @param method method.
     * @return method specification.
     */
    @NonNull
    private MethodSpec generateMethodSpec(@NonNull final Method method) {
        Result methodResult = getMethodResult(method);
        ParameterizedTypeName returnType = methodResult.multiple() ?
                ParameterizedTypeName.get(Flux.class, methodResult.type()) :
                ParameterizedTypeName.get(Mono.class, methodResult.type());

        return MethodSpec.methodBuilder(method.getName())
                .addAnnotation(NonNull.class)
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(returnType)
                .addParameters(generateParametersSpec(method))
                .addCode(generateMethodCode(method))
                .build();
    }

    /**
     * Generate code of method.
     *
     * @param method method.
     * @return code of method.
     */
    @Nullable
    private CodeBlock generateMethodCode(@NonNull final Method method) {
        if (hasMethodAnnotation(method, POST.class))
            return generatePostMethodCode(method);
        if (hasMethodAnnotation(method, GET.class))
            return generateGetMethodCode(method);
        if (hasMethodAnnotation(method, DELETE.class))
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
        Result methodResult = getMethodResult(method);
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
        Result methodResult = getMethodResult(method);
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
     * Generate parameters specification.
     *
     * @param method method.
     * @return parameters specification.
     */
    @NonNull
    private Collection<ParameterSpec> generateParametersSpec(@NonNull final Method method) {
        return Arrays.stream(method.getParameters())
                .map(this::generateParameterSpec)
                .collect(Collectors.toList());
    }

    /**
     * Generate parameter specification.
     *
     * @param parameter parameter of method.
     * @return parameter specification.
     */
    @NonNull
    private ParameterSpec generateParameterSpec(@NonNull final Parameter parameter) {
        return ParameterSpec.builder(parameter.getType(), parameter.getName(), Modifier.FINAL)
                .addAnnotation(NonNull.class)
                .build();
    }

    /**
     * Get result of method.
     *
     * @param method method.
     * @return result of method.
     */
    @Nullable
    private Result getMethodResult(@NonNull final Method method) {
        return hasMethodAnnotation(method, Result.class) ?
                method.getAnnotation(Result.class) :
                null;
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
                .filter(parameter -> hasParameterAnnotation(parameter, Path.class))
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
                .filter(parameter -> hasParameterAnnotation(parameter, Body.class))
                .findAny().orElse(null);
    }

    /**
     * Verify that method has needed annotation.
     *
     * @param method          method for check.
     * @param annotationClass class of annotation for check.
     * @param <T>             type of annotation for check.
     * @return method has needed annotation or not.
     */
    private <T extends Annotation> boolean hasMethodAnnotation(@NonNull final Method method,
                                                               @NonNull final Class<T> annotationClass) {
        try {
            return method.getAnnotation(annotationClass) != null;
        } catch (final Throwable ex) {
            return false;
        }
    }

    /**
     * Verify that parameter has needed annotation.
     *
     * @param parameter       parameter for check.
     * @param annotationClass class of annotation for check.
     * @param <T>             type of annotation for check.
     * @return parameter has needed annotation or not.
     */
    private <T extends Annotation> boolean hasParameterAnnotation(@NonNull final Parameter parameter,
                                                                  @NonNull final Class<T> annotationClass) {
        try {
            return parameter.getAnnotation(annotationClass) != null;
        } catch (final Throwable ex) {
            return false;
        }
    }
}