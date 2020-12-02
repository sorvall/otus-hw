package ru.sorokin;

import java.util.Map;

public class Order {
    private final Map<Nominal, Integer> order;

    public Map<Nominal, Integer> getOrder() {
        return order;
    }

    public Order(Map<Nominal, Integer> oder) {
        this.order = oder;
    }

    public void createOder(Nominal nominal) {
        Integer count = this.order.get(nominal);
        this.order.put(nominal, ++count);
    }

    public void getCashFromOrder() {
        for (Map.Entry<Nominal, Integer> pair : order.entrySet()) {
            if (pair.getValue() != 0) {
                System.out.println("Выдал " + pair.getKey().getValue() + " " + pair.getValue());
            }
        }
        clearOrder();
    }

    private void clearOrder() {
        for (Map.Entry<Nominal, Integer> pair : order.entrySet()) {
            this.order.put(pair.getKey(), 0);
        }
    }
}
