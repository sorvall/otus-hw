package ru.sorokin.processor;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.sorokin.model.Message;

import java.time.DateTimeException;


class ExceptionInEvenSecondsTest {

    private LocalDateTimeClass localDateTimeClass;
    private ExceptionInEvenSeconds exceptionInEvenSeconds;

    @BeforeEach
    void setUp() {
        localDateTimeClass = Mockito.mock(LocalDateTimeClass.class);
        exceptionInEvenSeconds = new ExceptionInEvenSeconds(localDateTimeClass);

    }

    @Test
    void processTest_DateTimeException() {

        Mockito.when(localDateTimeClass.getLocalDateTimeSecond()).thenReturn(22);
        Assertions.assertThrows(DateTimeException.class, () ->
            exceptionInEvenSeconds.process(new Message.Builder(1L).build())
        );
    }

    @Test
    void processTest_Success() {
        Mockito.when(localDateTimeClass.getLocalDateTimeSecond()).thenReturn(21);
        exceptionInEvenSeconds.process(new Message.Builder(1L).build());
    }


}