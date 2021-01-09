package ru.sorokin;

import java.sql.ResultSet;

@FunctionalInterface
public interface CustomResultSetHandler<T> {
    T resultSetHandle(ResultSet rs);
}
