package ru.sorokin.processor;

import java.time.LocalDateTime;

public class LocalDateTimeClass {
    int getLocalDateTimeSecond() {
        return LocalDateTime.now().getSecond();
    }
}
