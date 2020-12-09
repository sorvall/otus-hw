package ru.sorokin.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectForMessage {
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        List <String> ls = new ArrayList<>(data);
        this.data = ls;
    }
}
