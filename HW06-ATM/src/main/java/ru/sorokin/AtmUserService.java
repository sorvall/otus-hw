package ru.sorokin;

public interface AtmUserService {
    //Пользовательский сервис управление банкоматом
    int getBalance();

    void add(Nominal nom, int count);

    void getMoney(int getMoneyValue);
}
