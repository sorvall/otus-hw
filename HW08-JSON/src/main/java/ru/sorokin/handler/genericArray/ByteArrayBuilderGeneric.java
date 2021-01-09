package ru.sorokin.handler.genericArray;

import ru.sorokin.handler.StringHandler;

public class ByteArrayBuilderGeneric implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        return getJsonFromArray(name, (Byte[]) myType);
    }

    public boolean isValidType(Object myType) {
        return myType instanceof Byte[];
    }
}
