package com.kachnic.rtchats.modules.user.application;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.kachnic.rtchats.libs.exceptions.ExceptionBase;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomTimed {
    long minMillis() default 50L;

    long maxMillis() default 100L;

    Class<? extends ExceptionBase>[] delayOn() default {};
}
