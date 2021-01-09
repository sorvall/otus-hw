package ru.sorokin.api.executor;

import ru.sorokin.CustomResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DbExecutor<T> {

    void executeInsertOrUpdate(Connection connection, String sql, List<Object> params) throws SQLException;
    Optional<T> executeSelect(Connection connection, String sql, Object id, CustomResultSetHandler<T> rsHandler) throws SQLException;
}
