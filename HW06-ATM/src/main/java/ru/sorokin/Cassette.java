package ru.sorokin;

public class Cassette {
    int number;
    Nominal nom;
    int count;

    public Cassette(int number, Nominal nom, int count) {
        this.number = number;
        this.nom = nom;
        this.count = count;
    }


    public int getBalance() {
        return nom.getValue() * count;
    }

    public Nominal getNom() {
        return nom;
    }

    public int getCount() {
        return count;
    }

    public void addToBalance(int count) {
        this.count = this.count + count;
    }

    public void removeFromBalance(int takeOffCount) {
        this.count = this.count - takeOffCount;
    }


}
