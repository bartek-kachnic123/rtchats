package com.kachnic.rtchats.libs.application;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.kachnic.rtchats.libs.exceptions.InternalDomainException;

public class AsyncCommand<R> implements Command<R> {

    private final CompletableFuture<R> future = new CompletableFuture<>();

    protected AsyncCommand() {}

    public void complete(final R result) {
        future.complete(result);
    }

    @Override
    public R getResult() {
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new InternalDomainException(e);
        } catch (ExecutionException e) {
            throw new InternalDomainException(e);
        }
    }
}
