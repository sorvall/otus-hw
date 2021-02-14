package ru.sorokin;

public class Main {
    public static void main(String[] args) {
        MyCounter counter = new MyCounter();
        new Thread(() -> {
            try {
                counter.run("Second");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                counter.run("First");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
