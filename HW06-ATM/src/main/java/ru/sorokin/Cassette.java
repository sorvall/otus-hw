package ru.sorokin;

public class Cassette {
    private final Nominal nom;
    private int count;

    protected Cassette(int number, Nominal nom, int count) {
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
        if (this.count >= takeOffCount) {
            this.count = this.count - takeOffCount;
        } else {
            System.out.println("Недостаточно средств");
        }
    }


}
