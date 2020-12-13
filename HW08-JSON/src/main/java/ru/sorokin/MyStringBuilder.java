package ru.sorokin;

import java.util.*;

public class MyStringBuilder {
    public static String jsBuilder(String name, Object myType) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (myType instanceof String) {
                stringBuilder = new StringBuilder("\"" + name + "\":" + "\"" + myType + "\"");
            } else if (myType instanceof Integer || myType instanceof Long || myType instanceof Double || myType instanceof Float || myType instanceof Byte || myType instanceof Boolean || myType instanceof Character || myType instanceof Short) {
                stringBuilder = new StringBuilder("\"" + name + "\":" + myType);
            } else if (myType instanceof Collection) {
                stringBuilder = new StringBuilder("\"" + name + "\":" + myType);
            } else if (myType instanceof Map) {
                stringBuilder = new StringBuilder("\"" + name + "\":{");
                for (Map.Entry<?, ?> pair : ((Map<?, ?>) myType).entrySet()) {
                    stringBuilder.append("\"").append(pair.getKey()).append("\":").append("\"").append(pair.getValue()).append("\"").append(",");
                }
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
                stringBuilder.append("}");
            } else if (myType instanceof Integer[]) {
                stringBuilder = new StringBuilder("\"" + name + "\":[");
                for (Integer i : (Integer[]) myType) {
                    stringBuilder.append(i).append(",");
                }
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
                stringBuilder.append("]");
            } else if (myType instanceof Double[]) {
                stringBuilder = new StringBuilder("\"" + name + "\":[");
                for (Double i : (Double[]) myType) {
                    stringBuilder.append(i).append(",");
                }
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
                stringBuilder.append("]");
            } else if (myType instanceof double[]) {
                stringBuilder = new StringBuilder("\"" + name + "\":[");
                for (double i : (double[]) myType) {
                    stringBuilder.append(i).append(",");
                }
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
                stringBuilder.append("]");
            } else if (myType instanceof boolean[]) {
                stringBuilder = new StringBuilder("\"" + name + "\":[");
                for (boolean i : (boolean[]) myType) {
                    stringBuilder.append(i).append(",");
                }
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
                stringBuilder.append("]");
            } else if (myType instanceof char[]) {
                stringBuilder = new StringBuilder("\"" + name + "\":[");
                for (char i : (char[]) myType) {
                    stringBuilder.append(i).append(",");
                }
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
                stringBuilder.append("]");
            } else if (myType instanceof long[]) {
                stringBuilder = new StringBuilder("\"" + name + "\":[");
                for (long i : (long[]) myType) {
                    stringBuilder.append(i).append(",");
                }
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
                stringBuilder.append("]");
            } else if (myType instanceof float[]) {
                stringBuilder = new StringBuilder("\"" + name + "\":[");
                for (float i : (float[]) myType) {
                    stringBuilder.append(i).append(",");
                }
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
                stringBuilder.append("]");
            } else if (myType instanceof byte[]) {
                stringBuilder = new StringBuilder("\"" + name + "\":[");
                for (byte i : (byte[]) myType) {
                    stringBuilder.append(i).append(",");
                }
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
                stringBuilder.append("]");
            } else if (myType instanceof short[]) {
                stringBuilder = new StringBuilder("\"" + name + "\":[");
                for (short i : (short[]) myType) {
                    stringBuilder.append(i).append(",");
                }
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
                stringBuilder.append("]");
            } else if (myType instanceof int[]) {
                stringBuilder = new StringBuilder("\"" + name + "\":[");
                for (int i : (int[]) myType) {
                    stringBuilder.append(i).append(",");
                }
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
                stringBuilder.append("]");
            } else if (myType instanceof String[]) {
                String[] ts = (String[]) myType;
                stringBuilder = new StringBuilder("\"" + name + "\":[");
                for (int i = 0; i <= ts.length - 1; i++) {
                    stringBuilder = new StringBuilder(stringBuilder.append("\"").append(ts[i]).append("\"").append(","));
                }
                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
                stringBuilder.append("]");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return stringBuilder.toString();


    }

}
