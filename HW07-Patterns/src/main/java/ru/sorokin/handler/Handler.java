package ru.sorokin.handler;


import ru.sorokin.listener.Listener;
import ru.sorokin.model.Message;

public interface Handler {
    Message handle(Message msg);

    void addListener(Listener listener);
    void removeListener(Listener listener);
}
