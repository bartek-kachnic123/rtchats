package com.kachnic.rtchats.modules.user.infrastructure;

import com.kachnic.rtchats.libs.exceptions.DomainException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomTimed {
    int minMs() default 50;

    int maxMs() default 100;

    Class<? extends DomainException>[] delayOn() default {};
}
