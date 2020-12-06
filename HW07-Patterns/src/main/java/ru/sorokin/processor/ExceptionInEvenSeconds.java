package ru.sorokin.processor;

import ru.sorokin.model.Message;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class ExceptionInEvenSeconds implements Processor {
    private final LocalDateTimeClass localDateTimeClass;

    public ExceptionInEvenSeconds(LocalDateTimeClass localDateTimeClass) {
        this.localDateTimeClass = localDateTimeClass;
    }

    @Override
    public Message process(Message message) {
        int localDateTimeSecond = localDateTimeClass.getLocalDateTimeSecond();
        boolean isEvenCurrentSecond = booleanEvenCurrentTime(localDateTimeSecond);
        if (isEvenCurrentSecond) {
            throw new DateTimeException("Exception! second" + " " + localDateTimeSecond + " is " + "even True");
        }
        return message;
    }

    private boolean booleanEvenCurrentTime(int localSecondTimeSecond) {
        return localSecondTimeSecond % 2 == 0;
    }

}
