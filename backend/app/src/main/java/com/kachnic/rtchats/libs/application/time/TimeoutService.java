package com.kachnic.rtchats.libs.application.time;

import java.util.concurrent.Callable;

@FunctionalInterface
public interface TimeoutService {
    <T> T executeWithTimeout(Callable<T> task, TimeoutOperation operation);
}
