package ru.sorokin;

import com.google.gson.Gson;

import java.util.*;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        //Create object
        float[] floats = {1f, 2f, 5f, 6f, 7f};
        char[] chars = {'\u0066', '\u0065'};
        String[] strings = {"vaz", "gas"};
        double[] doubles = {333, 555};
        boolean[] booleans = {false, true};
        long[] longs = {1L, 2L};
        short[] shorts = {(short) 13, (short) 15};
        byte[] bytes = {(byte) 1, (byte) 5};
        Set<Float> set = new HashSet<>();
        set.add(10f);
        set.add(11f);
        List<Byte> list = new ArrayList<>();
        list.add((byte) 0b0111_1111);
        list.add((byte) 0b0101_1111);
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "246546");
        map.put(3, "5435345");
        Integer[] integers = {1, 2, 5};

        AnyPeople anyPeople = new AnyPeople(10, integers, "String", strings, 324d, doubles, false, booleans,
                15, floats, '\u0066', chars, 2L, longs, (short) 4, shorts, (byte) 2, bytes, null, map, list, set);
        Gson gson = new Gson();
        String json = gson.toJson(anyPeople);
        System.out.println("Эталоная строка");
        System.out.println(json);

        //объект для проверки
        AnyPeople anyPeople3 = new AnyPeople(11, integers, "String", strings, 324d, doubles, false, booleans,
                15, floats, '\u0066', chars, 2L, longs, (short) 4, shorts, (byte) 2, bytes, null, map, list, set);

        CreateJsonObject myJsonObject = new CreateJsonObject();
        String myStringJsonObject = myJsonObject.stringObject(anyPeople);
        System.out.println("____________");
        System.out.println("Моя строка");
        System.out.println(myStringJsonObject);
        AnyPeople anyPeople2 = gson.fromJson(myStringJsonObject, AnyPeople.class);

        System.out.println("____________");
        System.out.println(anyPeople.equals(anyPeople2));
    }
}
