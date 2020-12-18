package ru.sorokin.handler.genericArray;

import ru.sorokin.handler.StringHandler;

public class CharacterArrayBuilderGeneric implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        return getJsonFromArray(name, (Character[]) myType);
    }

    public boolean isValidType(Object myType) {
        return myType instanceof Character[];
    }
}
