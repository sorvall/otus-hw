package ru.sorokin.handler.genericArray;

import ru.sorokin.handler.StringHandler;

public class StringArrayBuilderGeneric implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        return getJsonFromArray(name, (String[]) myType);
    }

    public boolean isValidType(Object myType) {
        return myType instanceof String[];
    }
}
