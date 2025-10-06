package com.kachnic.rtchats.libs.application;

@FunctionalInterface
public interface Command<R> {
    R getResult();
}
