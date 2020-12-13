package ru.sorokin;

import java.util.*;

public class AnyPeople {
    private Integer integer;
    private Integer[] integers;

    private String string;
    private String[] strings;

    private double dADouble;
    private double [] doubles;

    private boolean bABoolean;
    private boolean [] booleans;

    private float aFloat;
    private float[] floats;

    private char aChar;
    private char[] chars;

    private long aLong;
    private long[] longs;

    private short aShort;
    private short[] shorts;

    private byte aByte;
    private byte[] bytes;

    private String nullInt;

    private Map<Integer, String> map;

    List <Byte> list;
    Set <Float> set;

    public AnyPeople(Integer integer, Integer[] integers, String string, String[] strings, double dADouble, double[] doubles, boolean bABoolean, boolean[] booleans, float aFloat, float[] floats, char aChar, char[] chars, long aLong, long[] longs, short aShort, short[] shorts, byte aByte, byte[] bytes, String nullInt, Map<Integer, String> map, List<Byte> list, Set<Float> set) {
        this.integer = integer;
        this.integers = integers;
        this.string = string;
        this.strings = strings;
        this.dADouble = dADouble;
        this.doubles = doubles;
        this.bABoolean = bABoolean;
        this.booleans = booleans;
        this.aFloat = aFloat;
        this.floats = floats;
        this.aChar = aChar;
        this.chars = chars;
        this.aLong = aLong;
        this.longs = longs;
        this.aShort = aShort;
        this.shorts = shorts;
        this.aByte = aByte;
        this.bytes = bytes;
        this.nullInt = nullInt;
        this.map = map;
        this.list = list;
        this.set = set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnyPeople anyPeople = (AnyPeople) o;
        return Double.compare(anyPeople.dADouble, dADouble) == 0 &&
                bABoolean == anyPeople.bABoolean &&
                Float.compare(anyPeople.aFloat, aFloat) == 0 &&
                aChar == anyPeople.aChar &&
                aLong == anyPeople.aLong &&
                aShort == anyPeople.aShort &&
                aByte == anyPeople.aByte &&
                Objects.equals(integer, anyPeople.integer) &&
                Arrays.equals(integers, anyPeople.integers) &&
                Objects.equals(string, anyPeople.string) &&
                Arrays.equals(strings, anyPeople.strings) &&
                Arrays.equals(doubles, anyPeople.doubles) &&
                Arrays.equals(booleans, anyPeople.booleans) &&
                Arrays.equals(floats, anyPeople.floats) &&
                Arrays.equals(chars, anyPeople.chars) &&
                Arrays.equals(longs, anyPeople.longs) &&
                Arrays.equals(shorts, anyPeople.shorts) &&
                Arrays.equals(bytes, anyPeople.bytes) &&
                Objects.equals(nullInt, anyPeople.nullInt) &&
                Objects.equals(map, anyPeople.map) &&
                Objects.equals(list, anyPeople.list) &&
                Objects.equals(set, anyPeople.set);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(integer, string, dADouble, bABoolean, aFloat, aChar, aLong, aShort, aByte, nullInt, map, list, set);
        result = 31 * result + Arrays.hashCode(integers);
        result = 31 * result + Arrays.hashCode(strings);
        result = 31 * result + Arrays.hashCode(doubles);
        result = 31 * result + Arrays.hashCode(booleans);
        result = 31 * result + Arrays.hashCode(floats);
        result = 31 * result + Arrays.hashCode(chars);
        result = 31 * result + Arrays.hashCode(longs);
        result = 31 * result + Arrays.hashCode(shorts);
        result = 31 * result + Arrays.hashCode(bytes);
        return result;
    }
}
