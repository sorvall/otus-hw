package ru.sorokin.MyFramework;


import ru.sorokin.annotations.After;
import ru.sorokin.annotations.Before;
import ru.sorokin.annotations.Test;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;


public class MyFramework {
    public void start(String name) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(name);
        System.out.println("Запускаю " + clazz.getSimpleName());
        Method[] methodsAll = clazz.getDeclaredMethods();
        List<Method> listBefore = new ArrayList<>();
        List<Method> listAfter = new ArrayList<>();
        List<Method> listTest = new ArrayList<>();
        for (
                Method all : methodsAll) {
            if (all.isAnnotationPresent(Before.class)) {
                listBefore.add(all);
            }
            if (all.isAnnotationPresent(Test.class)) {
                listTest.add(all);
            }
            if (all.isAnnotationPresent(After.class)) {
                listAfter.add(all);
            }
        }

        System.out.println("----------------------");
        StartTest st = new StartTest();
        for (Method i : listTest) {
            try {
                Object object = clazz.getDeclaredConstructor().newInstance();
                st.startBeforeOrAfterTest(object, clazz, listBefore);
                st.startCurrentTest(object, clazz, i);
                st.startBeforeOrAfterTest(object, clazz, listAfter);
                System.out.println("----------------------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Всего было тестов " + st.getAllTest() + " успешно " + st.getTestCompleted() + " неуспешно " + st.getTestFailed());


    }
}