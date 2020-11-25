package ru.sorokin;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService(new HwAtm(CassetteConfigBuilder.getDefaultConfig()));
        int giveMeCash;
        Nominal addCash;
        int addCashCount;

        //one
        giveMeCash = 7060;
        addCash = Nominal.FIVE_HUNDRED;
        addCashCount = 7;

        System.out.println("<Баланс>");
        System.out.println(userService.getBalance());
        System.out.println("<Внес " + addCashCount + " купюр номиналом " + addCash.getValue() + " р.>");
        userService.add(addCash, addCashCount);
        System.out.println("<Баланс>");
        System.out.println(userService.getBalance());
        System.out.println("--------------");
        System.out.println("<Выдал " + giveMeCash + " р.>");
        userService.getMoney(giveMeCash);
        System.out.println("<Баланс>");
        System.out.println(userService.getBalance());

        //two
        System.out.println("--------------");
        giveMeCash = 7060;
        addCash = Nominal.FIFTY;
        addCashCount = 7;

        System.out.println("<Баланс>");
        System.out.println(userService.getBalance());
        System.out.println("<Внес " + addCashCount + " купюр номиналом " + addCash.getValue() + " р.>");
        userService.add(addCash, addCashCount);
        System.out.println("<Баланс>");
        System.out.println(userService.getBalance());
        System.out.println();
        System.out.println("<Выдал " + giveMeCash + " р.>");
        userService.getMoney(giveMeCash);
        System.out.println("<Баланс>");
        System.out.println(userService.getBalance());


    }
}
