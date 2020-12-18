package ru.sorokin.handler;

public interface StringHandler {
    String getStringForJson(String name, Object myType);

    boolean isValidType(Object myType);

    default <T> String getJsonFromArray(String name, T[] myType) {
        StringBuilder stringBuilder = new StringBuilder("\"" + name + "\":[");
        for (T i : myType) {
            stringBuilder.append(i).append(",");
        }
        stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
