package ru.sorokin.handler.map;

import ru.sorokin.handler.StringHandler;

import java.util.Map;

public class MapBuilder implements StringHandler {
    @Override
    public String getStringForJson(String name, Object myType) {
        StringBuilder stringBuilder = new StringBuilder("\"" + name + "\":{");
        for (Map.Entry<?, ?> pair : ((Map<?, ?>) myType).entrySet()) {
            stringBuilder.append("\"").append(pair.getKey()).append("\":").append("\"").append(pair.getValue()).append("\"").append(",");
        }
        stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean isValidType(Object myType) {
        return myType instanceof Map;
    }
}
