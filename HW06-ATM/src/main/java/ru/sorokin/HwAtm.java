package ru.sorokin;

import java.util.List;

public class HwAtm {
    private final Config config;
    private final Order order;

    public HwAtm(Config config) {
        this.config = config;
        this.order = new Order(CassetteConfigBuilder.getOrder(this.config));
    }

    public List<Cassette> getCassettes() {
        return this.config.getCassettes();
    }


    public Order getOrder() {
        return order;
    }
}
