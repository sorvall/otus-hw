package ru.sorokin.handler.genericArray;

import ru.sorokin.handler.StringHandler;

public class FloatArrayBuilderGeneric implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        return getJsonFromArray(name, (Float[]) myType);
    }

    public boolean isValidType(Object myType) {
        return myType instanceof Float[];
    }
}
