package com.github.mikhailstepanov88.java_meetup.like.client.annotation;

import reactor.util.annotation.NonNull;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GET {
    /**
     * Path to the endpoint.
     *
     * @return path to the endpoint.
     */
    @NonNull String path();
}