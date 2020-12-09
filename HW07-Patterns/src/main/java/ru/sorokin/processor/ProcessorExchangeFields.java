package ru.sorokin.processor;

import ru.sorokin.model.Message;

public class ProcessorExchangeFields implements Processor {


    @Override
    public Message process(Message message) {
        return message.toBuilder().field4(message.getField10().toUpperCase()).build();
    }
}
