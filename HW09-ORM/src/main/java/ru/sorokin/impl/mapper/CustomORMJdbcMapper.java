package ru.sorokin.impl.mapper;

import ru.sorokin.api.executor.DbExecutor;
import ru.sorokin.api.mapper.EntityClassMetaData;
import ru.sorokin.api.mapper.EntitySQLMetaData;
import ru.sorokin.api.mapper.JdbcMapper;
import ru.sorokin.exception.CustomOrmRuntimeException;
import ru.sorokin.exception.NotFoundIdClassFieldException;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomORMJdbcMapper<T> implements JdbcMapper<T> {
    private final EntityClassMetaData<T> metaData;
    private final EntitySQLMetaData<T> sqlMetaData;
    private final DbExecutor<T> dbExecutor;
    private final DataSource dataSource;


    public CustomORMJdbcMapper(EntityClassMetaData<T> metaData,
                               EntitySQLMetaData<T> sqlMetaData,
                               DbExecutor<T> dbExecutor,
                               DataSource dataSource) {
        this.metaData = metaData;
        this.sqlMetaData = sqlMetaData;
        this.dbExecutor = dbExecutor;
        this.dataSource = dataSource;
    }

    @Override
    public void insert(T objectData) {
        String sql = sqlMetaData.getInsertSql();
        try (Connection connection = dataSource.getConnection()) {
            dbExecutor.executeInsertOrUpdate(connection, sql, getFieldValueForInsertList(objectData));
        } catch (Exception ex) {
            throw new CustomOrmRuntimeException("Не удалось вставить объект: " + objectData, ex);
        }

    }

    @Override
    public void update(T objectData) {
        String sql = sqlMetaData.getUpdateSql();
        try (Connection connection = dataSource.getConnection()) {
            dbExecutor.executeInsertOrUpdate(connection, sql, getFieldValueForUpdateList(objectData));
        } catch (Exception ex) {
            throw new CustomOrmRuntimeException("Не удалось обновить запись", ex);
        }
    }

    @Override
    public void insertOrUpdate(T objectData) {
        Object id = getIdValue(objectData);
        T savedObject = findById(id, metaData.getEntityType());
        if (savedObject == null) {
            insert(objectData);
        } else {
            update(objectData);
        }

    }

    @Override
    public T findById(Object id, Class<T> clazz) {
        String sql = sqlMetaData.getSelectByIdSql();
        try (Connection connection = dataSource.getConnection()) {
            Optional<T> object = dbExecutor.executeSelect(connection, sql, id, this::mapEntityInstance);
            if (object.isPresent()) {
                return object.get();
            }
        } catch (Exception ex) {
            throw new CustomOrmRuntimeException("Не удалось получить объект по ID", ex);
        }
        return null;
    }

    private List<Object> getFieldValueForInsertList(T objectData) throws IllegalAccessException {
        List<Field> fields = metaData.getAllFields();
        return getFieldValueList(objectData, fields);
    }

    private List<Object> getFieldValueForUpdateList(T objectData) throws IllegalAccessException {
        List<Field> fields = metaData.getAllFields();
        fields.add(metaData.getIdField());
        return getFieldValueList(objectData, fields);
    }


    private List<Object> getFieldValueList(T objectData, List<Field> fields) throws IllegalAccessException {
        List<Object> values = new ArrayList<>();
        for (var field : fields) {
            field.setAccessible(true);
            values.add(field.get(objectData));
        }
        return values;
    }


    private Object getIdValue(T objectData) {
        try {
            Field idField = metaData.getIdField();
            idField.setAccessible(true);
            return idField.get(objectData);
        } catch (Exception ex) {
            throw new NotFoundIdClassFieldException(ex.getMessage());
        }
    }


    private T mapEntityInstance(ResultSet rs) {
        try {
            if (rs.next()) {
                T instance = metaData.getConstructor().newInstance();
                List<Field> fields = metaData.getAllFields();
                for (Field field : fields) {
                    Object value = rs.getObject(field.getName());
                    field.setAccessible(true);
                    field.set(instance, value);
                }
                return instance;
            }
        } catch (Exception ex) {
            throw new CustomOrmRuntimeException("Ошибка при обработке ResultSet", ex);
        }
        return null;
    }
}
