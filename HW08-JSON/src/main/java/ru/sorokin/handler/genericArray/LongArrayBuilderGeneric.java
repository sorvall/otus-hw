package ru.sorokin.handler.genericArray;

import ru.sorokin.handler.StringHandler;

public class LongArrayBuilderGeneric implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        return getJsonFromArray(name, (Long[]) myType);
    }

    public boolean isValidType(Object myType) {
        return myType instanceof Long[];
    }
}
