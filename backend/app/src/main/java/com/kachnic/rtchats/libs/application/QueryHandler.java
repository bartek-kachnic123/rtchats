package com.kachnic.rtchats.libs.application;

@FunctionalInterface
public interface QueryHandler<R, Q extends Query> {
    R execute(Q query);
}
