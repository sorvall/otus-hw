package ru.sorokin;

import java.util.List;

public class Config {
    private final List<Cassette> cassettes;


    public Config(List<Cassette> cassettes) {
        this.cassettes = cassettes;
    }

    public List<Cassette> getCassettes() {
        return cassettes;
    }
}
