package ru.sorokin.demoClassTest;

import ru.sorokin.annotations.After;
import ru.sorokin.annotations.Before;
import ru.sorokin.annotations.Test;
import ru.sorokin.hw03.DemoClass;

public class DemoClassTest {
    @Test
    public void test1() {
        DemoClass demoClass = new DemoClass("Ann", false, false);
        System.out.println("Запустился метод test1:");
        demoClass.printInfo2();
    }

    @Test
    public void test2() {
        DemoClass demoClass = new DemoClass("Jon", true, true);
        System.out.println("Запустился метод test2:");
        throw new RuntimeException();
    }

    @Before
    public void before1() {
        System.out.println("Запустился метод before1:");

    }

    @Before
    public void before2() {
        System.out.println("Запустился метод before2:");
        throw new RuntimeException();

    }

    @After
    public void after1() {
        System.out.println("Запустился метод after1:");
        throw new RuntimeException();

    }

    @After
    public void after2() {
        System.out.println("Запустился метод after2:");
    }


}


