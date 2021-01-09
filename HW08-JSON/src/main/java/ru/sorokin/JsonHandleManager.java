package ru.sorokin;

import ru.sorokin.handler.*;
import java.util.*;

public class JsonHandleManager {
    private final List<StringHandler> jsonHandlerList = new ArrayList<>(jSonHandlerListBuilder.createjSonHandlerList());

    public String getMe(String name, Object field) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (StringHandler selectedTypeHandler : jsonHandlerList) {
                if (selectedTypeHandler.isValidType(field)) {
                    stringBuilder.append(selectedTypeHandler.getStringForJson(name, field));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Недопустимый тип, " + field.getClass() + " " + e.getMessage());
        }
        return stringBuilder.toString();
    }

}
