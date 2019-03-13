package com.github.mikhailstepanov88.java_meetup.like.client.generator.annotation;

import reactor.util.annotation.NonNull;

import java.lang.annotation.*;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Path {
    /**
     * Get name of replacement in a URL path segment.
     *
     * @return name of replacement in a URL path segment.
     */
    @NonNull String value();
}