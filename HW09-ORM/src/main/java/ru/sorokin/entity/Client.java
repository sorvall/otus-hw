package ru.sorokin.entity;

import ru.sorokin.annotation.Id;

public class Client {
    @Id
    private long id;
    private String name;
    private int age;

    public Client() {

    }

    public Client(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
