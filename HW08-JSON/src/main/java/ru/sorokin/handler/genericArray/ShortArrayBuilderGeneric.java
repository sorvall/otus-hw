package ru.sorokin.handler.genericArray;

import ru.sorokin.handler.StringHandler;

public class ShortArrayBuilderGeneric implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        return getJsonFromArray(name, (Short[]) myType);
    }
    public boolean isValidType(Object myType) {
        return myType instanceof Short[];
    }

}
