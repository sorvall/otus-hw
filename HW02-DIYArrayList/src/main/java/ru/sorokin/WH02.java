package ru.sorokin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WH02 {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            list1.add(i);
        }
        List<Integer> list50 = new ArrayList<>();
        for (int i = 50; i <= 100; i++) {
            list50.add(i);
        }
        List<Integer> list100 = new ArrayList<>();
        for (int i = 100; i <= 150; i++) {
            list100.add(i);
        }

        List<Integer> dIYarrayList = new DIYarrayList<Integer>();
        System.out.println("-----print list1");
        System.out.println(list1);
        System.out.println("-----print list50");
        System.out.println(list50);
        System.out.println("-----print list100");
        System.out.println(list100);
        System.out.println();
        System.out.println("-----after addALL with list1 without index");
        dIYarrayList.addAll(list1);
        System.out.println(dIYarrayList);
        System.out.println();
        System.out.println("-----after addALL at list50 with index");
        dIYarrayList.addAll(10, list50);
        System.out.println(dIYarrayList);
        System.out.println();
        System.out.println("----before copy");
        System.out.println(dIYarrayList);
        Collections.copy(dIYarrayList, list100);
        System.out.println("----after copy with list100");
        System.out.println(dIYarrayList);
        System.out.println();
        System.out.println("----before sort");
        System.out.println(dIYarrayList);
        Collections.sort(dIYarrayList);
        System.out.println("----after sort");
        System.out.println(dIYarrayList);
    }
}

