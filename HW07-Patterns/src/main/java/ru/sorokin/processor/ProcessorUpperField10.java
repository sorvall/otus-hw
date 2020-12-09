package ru.sorokin.processor;

import ru.sorokin.model.Message;

public class ProcessorUpperField10 implements Processor {

    @Override
    public Message process(Message message) {
        String field11 = message.getField11();
        return message.toBuilder().field11(message.getField12()).field12(field11).build();
    }
}
