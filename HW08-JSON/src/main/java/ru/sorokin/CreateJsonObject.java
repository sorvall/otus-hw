package ru.sorokin;

import java.lang.reflect.Field;

public class CreateJsonObject {

    public String stringObject(Object anyPeople) throws NoSuchFieldException, IllegalAccessException {
        String myStringJson = "";
        Class<?> clazz2 = anyPeople.getClass();
        Field[] fieldsAll = clazz2.getDeclaredFields();
        Class<?> clazz = anyPeople.getClass();
        for (Field field : fieldsAll) {
            Field fields = clazz.getDeclaredField(field.getName());
            fields.setAccessible(true);
            Object f = fields.get(anyPeople);
            if (f != null) {
                myStringJson = myStringJson + MyStringBuilder.jsBuilder(field.getName(), f);
                myStringJson = myStringJson + ",";
            }
        }
        myStringJson = myStringJson.substring(0, myStringJson.length() - 1);
        myStringJson = "{" + myStringJson + "}";
        return myStringJson;
    }
}
