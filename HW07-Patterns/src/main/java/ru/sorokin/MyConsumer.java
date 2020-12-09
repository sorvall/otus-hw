package ru.sorokin;

public interface MyConsumer<T> {
    void handle (T t);
}
