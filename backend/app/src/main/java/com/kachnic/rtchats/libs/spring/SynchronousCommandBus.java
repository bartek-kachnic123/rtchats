package com.kachnic.rtchats.libs.spring;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kachnic.rtchats.libs.application.Command;
import com.kachnic.rtchats.libs.application.CommandBus;
import com.kachnic.rtchats.libs.application.CommandHandler;

@Component
class SynchronousCommandBus implements CommandBus {

    private final Map<Class<? extends Command>, CommandHandler<? extends Command>> handlerMap;

    SynchronousCommandBus(final List<CommandHandler<? extends Command>> commandHandlers) {
        this.handlerMap = commandHandlers.stream()
                .collect(Collectors.toMap(CommandHandler::getCommandClass, Function.identity()));
    }

    @Override
    public <C extends Command> void execute(final C command) {
        @SuppressWarnings("unchecked")
        final CommandHandler<C> handler = (CommandHandler<C>) handlerMap.get(command.getClass());
        handler.handle(command);
    }
}
