package com.github.mikhailstepanov88.java_meetup.like.client.generator.annotation;

import reactor.util.annotation.NonNull;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Result {
    /**
     * Get type of result.
     *
     * @return type of result.
     */
    @NonNull Class type();

    /**
     * Check that result is multiple or not.
     *
     * @return result is multiple or not.
     */
    @NonNull boolean multiple() default false;
}