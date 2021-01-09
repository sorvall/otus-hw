package ru.sorokin.handler.genericAndPrimitive;

import ru.sorokin.handler.StringHandler;

public class FloatBuilder implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        return "\"" + name + "\":" + myType;
    }

    public boolean isValidType(Object myType) {
        return myType instanceof Float;
    }
}
