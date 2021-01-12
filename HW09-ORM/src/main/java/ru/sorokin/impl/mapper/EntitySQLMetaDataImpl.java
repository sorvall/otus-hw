package ru.sorokin.impl.mapper;

import ru.sorokin.api.mapper.EntityClassMetaData;
import ru.sorokin.api.mapper.EntitySQLMetaData;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData<T> {
    private final String idName;
    private final List<String> fieldNames;
    private final String tableName;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> metaData) {
        idName = initIdName(metaData);
        fieldNames = initFieldNames(metaData);
        tableName = initTableName(metaData);
    }

    private static <T> String initIdName(EntityClassMetaData<T> metaData) {
        return metaData.getIdField().getName();
    }

    private static <T> List<String> initFieldNames(EntityClassMetaData<T> metaData) {
        return metaData.getAllFields().stream().map(Field::getName).collect(Collectors.toList());
    }

    private static <T> String initTableName(EntityClassMetaData<T> metaData) {
        return metaData.getEntityType().getSimpleName().toLowerCase();
    }

    @Override
    public String getSelectAllSql() {
        return null;
    }

    @Override
    public String getSelectByIdSql() {
        return "SELECT * FROM public." +
                tableName +
                " WHERE " +
                idName +
                "= ?";
    }

    @Override
    public String getInsertSql() {
        return "INSERT INTO public." +
                tableName +
                "(" +
                String.join(", ", fieldNames) +
                ")" +
                " " +
                "VALUES" +
                "(" +
                getFieldReplacementForInsert() +
                ")";
    }

    @Override
    public String getUpdateSql() {
        return "UPDATE public." +
                tableName +
                " SET " +
                getFieldReplacementForUpdate() +
                " WHERE " +
                idName +
                "=?";
    }

    private String getFieldReplacementForInsert() {
        return fieldNames.stream().map(field -> "?").collect(Collectors.joining(","));
    }

    private String getFieldReplacementForUpdate() {
        return fieldNames.stream().map(field -> field + "=?").collect(Collectors.joining(","));
    }
}
