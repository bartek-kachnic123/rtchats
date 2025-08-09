package com.kachnic.rtchats.libs.ddd;

@FunctionalInterface
public interface Command<R> {
    R getResult();
}
