package com.kachnic.rtchats.modules.user.application;

import java.util.concurrent.Callable;

@FunctionalInterface
public interface TimeoutService {
    <T> T executeWithTimeout(Callable<T> task, TimeoutOperation operation);
}
