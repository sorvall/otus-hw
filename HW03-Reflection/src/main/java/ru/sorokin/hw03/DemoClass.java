package ru.sorokin.hw03;

import java.lang.annotation.Documented;

public class DemoClass {
    private String name;
    private int age;
    private boolean sex;
    private boolean partner;

    public DemoClass(String name, boolean sex, boolean partner) {
        this.name = name;
        this.sex = sex;
        this.partner = partner;
    }

    public String getName() {
        return name;
    }

    public void printInfo3() {
        System.out.println("You name is " + name + " and you have partner " + ((partner) ? "Yes" : "No"));
    }

    public void printInfo2() {
        System.out.println("You name is " + name + " and you sex is " + ((sex) ? "male" : "female"));
    }

    public void printInfo1() {
        System.out.println("You name is " + name + " and you age is " + age);
    }


}
