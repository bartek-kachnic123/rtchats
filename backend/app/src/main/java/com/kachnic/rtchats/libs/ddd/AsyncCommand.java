package com.kachnic.rtchats.libs.ddd;

import com.kachnic.rtchats.libs.exceptions.InternalServerException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
        } catch (InterruptedException | ExecutionException e) {
            throw new InternalServerException(e);
        }
    }
}
