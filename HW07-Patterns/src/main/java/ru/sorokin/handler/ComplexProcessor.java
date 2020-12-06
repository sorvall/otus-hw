package ru.sorokin.handler;


import ru.sorokin.MyConsumer;
import ru.sorokin.listener.Listener;
import ru.sorokin.model.Message;
import ru.sorokin.processor.Processor;

import java.util.ArrayList;
import java.util.List;

public class ComplexProcessor implements Handler {

    private final List<Listener> listeners = new ArrayList<>();
    private final List<Processor> processors;
    private final MyConsumer<Exception> errorHandler;

    public ComplexProcessor(List<Processor> processors, MyConsumer<Exception> errorHandler) {
        this.processors = processors;
        this.errorHandler = errorHandler;
    }

    @Override
    public Message handle(Message msg) {
        Message newMsg = msg;
        for (Processor pros : processors) {
            try {
                newMsg = pros.process(newMsg);
            } catch (Exception ex) {
                errorHandler.handle(ex);
            }
        }
        notify(msg, newMsg);
        return newMsg;
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private void notify(Message oldMsg, Message newMsg) {
        listeners.forEach(listener -> {
            try {
                listener.onUpdated(oldMsg, newMsg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
