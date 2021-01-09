package ru.sorokin.handler.genericArray;

import ru.sorokin.handler.StringHandler;

public class BooleanArrayBuilderGeneric implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        return getJsonFromArray(name, (Boolean[]) myType);
    }

    public boolean isValidType(Object myType) {
        return myType instanceof Boolean[];
    }
}
