package ru.sorokin.listener;

import ru.sorokin.model.Message;

public class MessagePair {
    private final Message oldMessage;
    private final Message newMessage;

    public MessagePair(Message oldMessage, Message newMessage) {
        this.oldMessage = oldMessage;
        this.newMessage = newMessage;
    }

    public Message getOldMessage() {
        return oldMessage;
    }

    public Message getNewMessage() {
        return newMessage;
    }

    @Override
    public String toString() {
        return "MessagePair{" +
                "oldMessage=" + oldMessage +
                ", newMessage=" + newMessage +
                '}';
    }
}
