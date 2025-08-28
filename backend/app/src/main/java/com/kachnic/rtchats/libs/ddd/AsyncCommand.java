package com.kachnic.rtchats.libs.ddd;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.kachnic.rtchats.libs.exceptions.InternalServerException;

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
            throw new InternalServerException(e);
        } catch (ExecutionException e) {
            throw new InternalServerException(e);
        }
    }
}
