package ru.sorokin.handler.genericAndPrimitive;

import ru.sorokin.handler.StringHandler;

public class BooleanBuilder implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        return "\"" + name + "\":" + myType;
    }

    public boolean isValidType(Object myType) {
        return myType instanceof Boolean;
    }
}
