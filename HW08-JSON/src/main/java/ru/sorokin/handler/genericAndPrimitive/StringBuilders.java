package ru.sorokin.handler.genericAndPrimitive;

import ru.sorokin.handler.StringHandler;

public class StringBuilders implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        return "\"" + name + "\":" + "\"" + myType + "\"";
    }

    @Override
    public boolean isValidType(Object myType) {
        return myType instanceof String;
    }
}
