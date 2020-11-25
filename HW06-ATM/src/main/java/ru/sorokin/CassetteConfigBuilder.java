package ru.sorokin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CassetteConfigBuilder {

    public static Config getDefaultConfig() {
        List<Cassette> defaultCassettes = new ArrayList<>() {
            {
                add(new Cassette(1, Nominal.TEN, 5));
                add(new Cassette(2, Nominal.FIFTY, 1));
                add(new Cassette(3, Nominal.HUNDRED, 0));
                add(new Cassette(4, Nominal.FIVE_HUNDRED, 0));
                add(new Cassette(5, Nominal.THOUSAND, 0));
                add(new Cassette(6, Nominal.FIVE_THOUSAND, 2));
            }
        };
        return new Config(defaultCassettes);
    }

    public static Config getUserConfig(Map<Nominal, Integer> nominals) {
        int i = 1;
        List<Cassette> userCassettes = new ArrayList<>();
        for (Map.Entry<Nominal, Integer> pair : nominals.entrySet()) {
            userCassettes.add(new Cassette(i++, pair.getKey(), pair.getValue()));
        }
        return new Config(userCassettes);
    }


    public static Map<Nominal, Integer> getOrder(Config nominals) {

        Map<Nominal, Integer> order = new HashMap<>();
        for (Cassette c : nominals.getCassettes()) {
            order.put(c.getNom(), 0);
        }
        return order;
    }


}
