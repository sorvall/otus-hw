package ru.sorokin;

import ru.sorokin.annotation.Log;

public class MyClassImpl implements MyClassInterface {

    @Override
    public void secureAccess(String param) {
        System.out.println("void secureAccess start " + param);
    }

    @Log
    @Override
    public void secure1() {
        System.out.println("void secure1 start ");

    }

    @Log
    @Override
    public void secure2(String param, int age) {
        System.out.println("void secure2 start "+ param + " " + age);

    }

    @Override
    public String toString() {
        return "MyClassImpl{}";
    }
}

