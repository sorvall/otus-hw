package ru.sorokin;

import ru.sorokin.handler.ComplexProcessor;
import ru.sorokin.listener.HistoryList;
import ru.sorokin.listener.ListenerHistory;
import ru.sorokin.listener.ListenerPrinter;
import ru.sorokin.model.Message;
import ru.sorokin.processor.ExceptionInEvenSeconds;
import ru.sorokin.processor.LocalDateTimeClass;
import ru.sorokin.processor.ProcessorExchangeFields;

import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
       4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
     */

    public static void main(String[] args) {
        var processors = List.of(new ProcessorExchangeFields(), new ExceptionInEvenSeconds(new LocalDateTimeClass()));

        var complexProcessor = new ComplexProcessor(processors, e -> System.out.println("We catch that exception: " + e.getMessage()));

        var listenerPrinter = new ListenerPrinter();
        complexProcessor.addListener(listenerPrinter);
        var historyList = new HistoryList();
        var listenerHistory = new ListenerHistory(historyList);
        complexProcessor.addListener(listenerHistory);

        var message = new Message.Builder(1L)
                .field1("field1")
                .field2("field2")
                .field3("field3")
                .field6("field6")
                .field10("field10")
                .field11("field11")
                .field12("field12")
                .build();

        var result = complexProcessor.handle(message);
        System.out.println("result:" + result);

        System.out.println("---------------------------");

        var message1 = new Message.Builder(2L)
                .field1("field11")
                .field2("field22")
                .field3("field33")
                .field6("field66")
                .field10("field100")
                .field11("field111")
                .field12("field12")
                .build();


        var result2 = complexProcessor.handle(message1);
        System.out.println("result:" + result2);
        complexProcessor.removeListener(listenerPrinter);
        System.out.println("---------------------------");
        System.out.println("Print message history:");
        System.out.println(historyList);
    }
}
