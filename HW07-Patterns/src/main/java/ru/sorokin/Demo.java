package ru.sorokin;


import ru.sorokin.handler.ComplexProcessor;
import ru.sorokin.listener.ListenerPrinter;
import ru.sorokin.model.Message;
import ru.sorokin.processor.*;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        var processors = List.of(new ProcessorConcatFields(),
                new LoggerProcessor(new ProcessorUpperField10()), new ProcessorExchangeFields(), new ExceptionInEvenSeconds(new LocalDateTimeClass()));

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {});
        var listenerPrinter = new ListenerPrinter();
        complexProcessor.addListener(listenerPrinter);

        var message = new Message.Builder(1L)
                .field1("field1")
                .field2("field2")
                .field3("field3")
                .field6("field6")
                .field10("field10")
                .field11("field11")
                .field12("field12")
                .build();

        System.out.println("Start");
        var result = complexProcessor.handle(message);
        System.out.println("result:" + result);

        complexProcessor.removeListener(listenerPrinter);
    }
}
