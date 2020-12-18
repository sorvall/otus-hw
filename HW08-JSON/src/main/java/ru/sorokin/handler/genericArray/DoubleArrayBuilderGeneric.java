package ru.sorokin.handler.genericArray;

import ru.sorokin.handler.StringHandler;

public class DoubleArrayBuilderGeneric implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        return getJsonFromArray(name, (Double[]) myType);
    }
    public boolean isValidType(Object myType) {
        return myType instanceof Double[];
    }
}
