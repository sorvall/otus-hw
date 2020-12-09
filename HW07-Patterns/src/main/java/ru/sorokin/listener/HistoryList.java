package ru.sorokin.listener;

import ru.sorokin.model.Message;

import java.util.ArrayList;
import java.util.List;

public class HistoryList {
    private final List<MessagePair> history = new ArrayList<>();


    public void add(Message oldMessage, Message newMessage) {
        history.add(new MessagePair(oldMessage, newMessage));
    }

    public List<MessagePair> getHistory() {
        return history;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MessagePair mp:history) {
            sb.append(mp.toString()).append("\n");
        }
        return sb.toString();
    }
}
