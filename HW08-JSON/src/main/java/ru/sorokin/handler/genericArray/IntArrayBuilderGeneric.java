package ru.sorokin.handler.genericArray;

import ru.sorokin.handler.StringHandler;

public class IntArrayBuilderGeneric implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        return getJsonFromArray(name, (Integer[]) myType);
    }
    public boolean isValidType(Object myType) {
        return myType instanceof Integer[];
    }
}
