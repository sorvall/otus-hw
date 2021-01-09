package ru.sorokin.impl.executor;

import ru.sorokin.CustomResultSetHandler;
import ru.sorokin.api.executor.DbExecutor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;
import java.util.Optional;

public class DbExecutorImpl<T> implements DbExecutor<T> {

    @Override
    public void executeInsertOrUpdate(Connection connection, String sql, List<Object> params) throws SQLException {
        Savepoint savePoint = connection.setSavepoint("savePointName");
        try (var pst = connection.prepareStatement(sql)) {
            for (int idx = 0; idx < params.size(); idx++) {
                pst.setObject(idx + 1, params.get(idx));
            }
            pst.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback(savePoint);
            throw ex;
        }
    }


    @Override
    public Optional<T> executeSelect(Connection connection, String sql, Object id,
                                     CustomResultSetHandler<T> rsHandler) throws SQLException {
        try (var pst = connection.prepareStatement(sql)) {
            pst.setObject(1, id);
            try (var rs = pst.executeQuery()) {
                return Optional.ofNullable(rsHandler.resultSetHandle(rs));
            }
        }
    }
}
