package ru.sorokin;

import java.lang.reflect.Field;

public class CreateJsonObject {

    public String stringObject(Object anyPeople) throws NoSuchFieldException, IllegalAccessException {
        StringBuilder myStringJson = new StringBuilder();
        JsonHandleManager myMap = new JsonHandleManager();
        Class<?> clazz2 = anyPeople.getClass();
        Field[] fieldsAll = clazz2.getDeclaredFields();
        Class<?> clazz = anyPeople.getClass();
        for (Field field : fieldsAll) {
            Field fields = clazz.getDeclaredField(field.getName());
            fields.setAccessible(true);
            Object f = fields.get(anyPeople);
            if (f != null) {
                myStringJson.append(myMap.getMe(field.getName(), f));
                myStringJson.append(",");
            }
        }
        myStringJson = new StringBuilder(myStringJson.substring(0, myStringJson.length() - 1));
        myStringJson = new StringBuilder("{" + myStringJson + "}");
        return myStringJson.toString();
    }
}
