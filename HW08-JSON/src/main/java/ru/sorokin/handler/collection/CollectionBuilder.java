package ru.sorokin.handler.collection;

import ru.sorokin.handler.StringHandler;

import java.util.Collection;

public class CollectionBuilder implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        return "\"" + name + "\":" + myType;
    }

    public boolean isValidType(Object myType) {
        return myType instanceof Collection;
    }
}
