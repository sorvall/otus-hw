package ru.sorokin;


import java.util.Map;
//сервис по управлению банкоматом
public class UserService implements AtmUserService {
    private final HwAtm atm;

    public UserService(HwAtm atm) {
        this.atm = atm;
    }

    @Override
    public int getBalance() {
        int balance = 0;
        for (Cassette i : this.atm.getCassettes()) {
            balance = balance + i.getBalance();
        }
        return balance;
    }

    @Override
    public void add(Nominal nom, int count) {
        for (Cassette i : this.atm.getCassettes()) {
            if (i.getNom().getValue() == nom.getValue()) {
                i.addToBalance(count);
            }
        }
    }

    @Override
    public void getMoney(int getMoneyValue) {
        if (getMoneyValue < getBalance()) {
            for (int i = this.atm.getCassettes().size() - 1; i >= 0; i--) {
                if (this.atm.getCassettes().get(i).getCount() > 0) {
                    int c = this.atm.getCassettes().get(i).getCount();
                    for (int j = 0; j <= c; j++) {
                        if (getMoneyValue / this.atm.getCassettes().get(i).getNom().getValue() >= 1) {
                            getMoneyValue = getMoneyValue - this.atm.getCassettes().get(i).getNom().getValue();
                        } else {
                            break;
                        }
                        this.atm.getOrder().createOder(this.atm.getCassettes().get(i).getNom());
                    }

                }
            }

            // списание по ордеру
            for (Map.Entry<Nominal, Integer> pair : this.atm.getOrder().getOrder().entrySet()) {
                for (int cassette = 0; cassette <= this.atm.getCassettes().size() - 1; cassette++) {
                    if (this.atm.getCassettes().get(cassette).getNom().getValue() == pair.getKey().getValue()) {
                        this.atm.getCassettes().get(cassette).removeFromBalance(pair.getValue());
                    }
                }
            }
            this.atm.getOrder().getCashFromOrder();
        } else {
            System.out.println("Не хватает денег!");
        }


    }

}

