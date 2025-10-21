package com.kachnic.rtchats.libs.spring;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kachnic.rtchats.libs.application.Command;
import com.kachnic.rtchats.libs.application.CommandBus;
import com.kachnic.rtchats.libs.application.CommandHandler;
import com.kachnic.rtchats.libs.exceptions.CommandHandlerNotFoundException;

@Component
@SuppressWarnings({"unchecked", "rawtypes"})
class SynchronousCommandBus implements CommandBus {

    private final Map<Class<? extends Command>, CommandHandler> handlerMap;

    SynchronousCommandBus(final List<CommandHandler> commandHandlers) {
        this.handlerMap = commandHandlers.stream()
                .collect(Collectors.toMap(CommandHandler::getCommandClass, Function.identity()));
    }

    @Override
    public void execute(final Command command) {
        final CommandHandler handler = handlerMap.get(command.getClass());
        if (handler == null) {
            throw new CommandHandlerNotFoundException(
                    "No handler found for command: " + command.getClass().getSimpleName());
        }
        handler.handle(command);
    }
}
