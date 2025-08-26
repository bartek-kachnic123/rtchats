package com.kachnic.rtchats.modules.user.infrastructure;

import com.kachnic.rtchats.libs.exceptions.DomainException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomTimed {
    long minMs() default 50L;

    long maxMs() default 100L;

    Class<? extends DomainException>[] delayOn() default {};
}
