package ru.sorokin.handler.primitiveArray;

import ru.sorokin.handler.StringHandler;

public class LongArrayBuilder implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        StringBuilder stringBuilder = new StringBuilder("\"" + name + "\":[");
        for (long i : (long[]) myType) {
            stringBuilder.append(i).append(",");
        }
        stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public boolean isValidType(Object myType) {
        return myType instanceof long[];
    }
}
