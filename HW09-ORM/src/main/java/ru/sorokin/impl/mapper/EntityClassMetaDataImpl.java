package ru.sorokin.impl.mapper;

import ru.sorokin.annotation.Id;
import ru.sorokin.api.mapper.EntityClassMetaData;
import ru.sorokin.exception.NotFoundIdClassFieldException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private final String name;
    private final Constructor<T> constructor;
    private Field idField;
    private final List<Field> allField = new ArrayList<>();
    private final List<Field> allFieldsWithoutId = new ArrayList<>();
    private final Class<T> entityType;


    public EntityClassMetaDataImpl(Class<T> entity) {
        name = entity.getName();
        constructor = initConstructor(entity);
        initClassFields(entity);
        entityType = entity;
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    private void initClassFields(Class<T> entity) {
        Field[] fields = entity.getDeclaredFields();
        for (Field field : fields) {
            allField.add(field);
            if (field.isAnnotationPresent(Id.class)) {
                idField = field;
            }
            else {
                allFieldsWithoutId.add(field);
            }
        }
        if (idField == null) {
            throw new NotFoundIdClassFieldException("Нет поля с аннотацией ID");
        }
    }

    private Constructor<T> initConstructor(Class<T> entity) {
        try {
            return entity.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Constructor<T> getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    @Override
    public List<Field> getAllFields() {
        return allField;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return allFieldsWithoutId;
    }


}
